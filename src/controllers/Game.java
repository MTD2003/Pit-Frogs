package controllers;

import elements.Grid;
import elements.Entity;
import elements.Selection;
import utilities.SpriteList;
import view.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import javax.imageio.ImageIO;
import java.util.ArrayList;

public class Game implements Runnable {
    private BufferedImage[][] spriteSheet;
    private Grid gameGrid;
    private GameView window;
    private GamePanel panel;
    private int gridScale; // Used for drawing and hitboxes.
    private int lastScale; // Used in rescaling hitboxes.
    
    private static final int FPS = 60;
    private static final int UPS = 120;
    
    private ArrayList<InteractBox> hitboxes;
    private int turn;
    private int movesLeft;
    private int timer;
    private final int maxTime;
    
    public Game() {
        gameGrid = new Grid(2, 7);
        
        window = new GameView();
        panel = new GamePanel(this);
        window.addComponent(panel);
        gridScale = 1;
        
        loadSprites();
        findGridScale();
        
        turn = 0;
        maxTime = 1;
        timer = 0;
        movesLeft = 2;
        hitboxes = new ArrayList<InteractBox>();
    }
    
    // Calculates gridScale based on Window and Grid size.
    public void findGridScale() {
    	int gridSize = gameGrid.getSize();
    	int screenSize = window.getMinSize();
    	lastScale = gridScale;
    	gridScale = (screenSize - SpriteList.SPRITE_DIMENSIONS / 2) / gridSize;
    }
    
    public void run() {
    	long nanoSeconds = 1000 * 1000 * 1000; // One nanosecond = 1 * 10^-9 seconds.
    	long nanoFrameGap = nanoSeconds / FPS;
    	long nanoUpdateGap = nanoSeconds / UPS;
    	
    	long nanoCurrent;
    	long nanoUpdateLast = 0, nanoFrameLast = 0;
    	
    	panel.requestFocusInWindow();
    	
    	while(true) { // Basic game loop.
    		nanoCurrent = System.nanoTime();
    		
    		if((nanoCurrent - nanoUpdateLast) > nanoUpdateGap) {
    			step();
    			nanoUpdateLast = System.nanoTime();
    		}
    		
    		if((nanoCurrent - nanoFrameLast) > nanoFrameGap) {
    			findGridScale();
    			draw();
    			
    			updateHitboxes();
    			nanoFrameLast = System.nanoTime();
    		}
    	}
    }
    
    private void step() {
    	int timeLimit = maxTime * UPS;
    	timer++;
    	
    	if((!gameGrid.getPlayerStatusAt(turn))) {
    		movesLeft = 0;
    	}
    	
    	if(gameGrid.getMovesNum() == 0) {
    		if(movesLeft > 0 && gameGrid.getPlayerStatusAt(turn)) {
	    		gameGrid.generateMoves(turn, movesLeft % 2);
	    		generateHitboxes();
	    		timer = 0;
	    		movesLeft--;
    	
    		} else {
	    		turn = gameGrid.getNextAlive(turn);
	    		movesLeft = 2;
	    		
	    		tryWinCondition();
    		}
    	}
    	
    	hoverCheck();
    	if(inputCheck()) {
    		hitboxes.clear();
    	} 
    	/*
    	else if(timeLimit <= timer) { // Timer check.
    		randomMove();
    		hitboxes.clear();
    	}
    	*/
    }
    
    // Checks the wincondition and then transitions to the winscreen state if it suceeds.
    private void tryWinCondition() {
    	if(turn == gameGrid.getNextAlive(turn)) {
    		System.out.println("Player " + (turn + 1) + " wins!");
			System.exit(0);
    	}
    }
    
    // TODO: Include border elements, centered scaling.
    private void draw() {
    	ArrayList<Entity> drawList = gameGrid.getDrawList();
    	Graphics buffer = panel.getImage().getGraphics(); // Gets the image buffer, then takes the graphics from there.
    	
    	for(Entity e : drawList) {
    		int x = e.getX() * gridScale;
    		int y = e.getY() * gridScale;
    		buffer.drawImage(spriteSheet[e.getSprite()][e.getFrame()], x, y, gridScale, gridScale, null);
    	}
    	panel.repaint();
    }
    
    // TODO: Figure out what bug is causing this functions to get double-called and always result in index 0.
    private void randomMove() {
    	double myRandom = Math.random();
    	int index = (int)myRandom * hitboxes.size();
    	System.out.println("Index results from : " + myRandom + " and " + hitboxes.size());
    	if(hitboxes.size() > 0) {
    		hitboxes.get(index).forceActivate();
    	}
    }
    
    private void hoverCheck() {
    	int heldKey = panel.getHeld();
    	int mouseX = panel.getMouseX();
    	int mouseY = panel.getMouseY();
    	
    	for(InteractBox ib : hitboxes) {
    		ib.checkHover(mouseX, mouseY, heldKey);
    	}
    }
    
    private boolean inputCheck() {
    	int lastKey = panel.popKey();
    	int mouseState = panel.popMouseState();
    	int mouseX = panel.getMouseX();
    	int mouseY = panel.getMouseY();
    	
    	if(lastKey != GamePanel.KEY_EMPTY || mouseState != GamePanel.MOUSE_IDLE) {
    		for(InteractBox ib : hitboxes) {
    			if(ib.checkHit(mouseX, mouseY)) {
    				return true;
    			}
    			if(ib.checkBind(lastKey)) {
    				return true;
    			}
    		}
    	}
    	
    	return false;
    }
    
    // Generates InteractBoxes for each Interactable.
    private void generateHitboxes() {
    	int x, y;
    	int totalMoves = gameGrid.getMovesNum();
    	
    	for(int i = 0; i < totalMoves; i++) {
    		Selection move = gameGrid.getMoveAt(i);
    		x = move.getX() * gridScale;
    		y = move.getY() * gridScale;
    		hitboxes.add(new InteractBox(move, x, y, gridScale, gridScale, move.calcKeyBind()));
    	}
    }
    
    // Updates with changes in screenscale.
    private void updateHitboxes() {
    	for(InteractBox ib : hitboxes) {
    		ib.updateScale(lastScale, gridScale);
    	}
    }

    // Loads all sprites provided in the SpriteList enum.
    private void loadSprites() {
        InputStream curStream;
        spriteSheet = new BufferedImage[SpriteList.SPR_BLANK.ordinal() + 1][]; // SPR_BLANK is at the end of the enum.
        for(SpriteList spriteIndex : SpriteList.values()) {
            curStream = getClass().getResourceAsStream(spriteIndex.path());
            try {
            	BufferedImage tempImg = ImageIO.read(curStream);
            	int i = spriteIndex.ordinal();
            	int fHeight = tempImg.getHeight(); // Spritesheets are horizontal, making height constant.
            	int fWidth = tempImg.getWidth() / spriteIndex.frames();
            	
            	spriteSheet[i] = new BufferedImage[spriteIndex.frames()];
            	for(int j = 0; j < spriteIndex.frames(); j++) {
            		spriteSheet[i][j] = tempImg.getSubimage(fWidth * j, 0, fWidth, fHeight);
            		//System.out.println("Read frame " + j + " of sprite at " + spriteIndex.path());
            	}
            	
            } catch(IOException e) {
                System.out.println(e);
            } finally {
                try {
                    curStream.close();
                } catch(IOException e) {
                    System.out.println(e);
                }
            }
        }
    }
}