package controllers;

import elements.Grid;
import elements.Entity;
import utilities.SpriteList;
import utilities.GridConsts;
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
    
    private static final int FPS = 60;
    private static final int UPS = 120;
    private static final int MIN_PLAYERS = 2;
    
    public Game() {
        gameGrid = new Grid(4, 7);
        
        window = new GameView();
        panel = new GamePanel(this);
        window.addComponent(panel);
        
        loadSprites();
        findGridScale();
        
        /* Diagonal Movement Code -> Slightly more elegant than manual code.
        int move[] = {-1, -1};
        for(int xy = 0; xy < 4; xy++) {
            griddy[xy % 2] *= -1;
            System.out.println(griddy[0] + ", " + griddy[1]);
            makeClicker(move);
        }
        */
    }
    
    // Calculates gridScale based on Window and Grid size.
    public void findGridScale() {
    	int gridSize = gameGrid.getSize();
    	int screenSize = window.getMinSize();
    	gridScale = (screenSize - SpriteList.SPRITE_DIMENSIONS / 2) / gridSize;
    }
    
    public void run() {
    	long nanoSeconds = 1000 * 1000 * 1000; // One nanosecond = 1 * 10^-9 seconds.
    	long nanoFrameGap = nanoSeconds / FPS;
    	long nanoUpdateGap = nanoSeconds / UPS;
    	
    	long nanoCurrent;
    	long nanoUpdateLast = 0, nanoFrameLast = 0;
    	
    	// Basic game loop.
    	while(true) {
    		nanoCurrent = System.nanoTime();
    		
    		if((nanoCurrent - nanoUpdateLast) > nanoUpdateGap) {
    			step();
    			nanoUpdateLast = System.nanoTime();
    		}
    		
    		if((nanoCurrent - nanoFrameLast) > nanoFrameGap) {
    			findGridScale();
    			draw();
    			nanoFrameLast = System.nanoTime();
    		}
    	}
    }
    
    private void step() {
    	// TODO: Turn timer.
    	// TODO: Turn loop.
    	int lastKey = panel.popKey();
    	if(lastKey != -1) {
    		System.out.println("OUT");
    		gameGrid.generateMoves(0, GridConsts.ORTHOGONAL);
    	}
    	/* Placeholder block for running tests.
        Scanner scans = new Scanner(System.in);
        int i = 0;
        int input;
        do {
            System.out.print(gameGrid);
            System.out.println("Player: " + (i + 1));
            input = scans.nextInt();
            gameGrid.playerMove(i, input, false);
            System.out.print(gameGrid);
            input = scans.nextInt();
            gameGrid.playerMove(i, input, true);
            i = (i + 1) % 2;
            
        } while(input != 5);
        
        scans.close();
        */
    }
    
    // TODO: Fix the flicker on the draw function. This will likely require a significant change.
    // TODO: Include border elements, centered scaling.
    private void draw() {
    	ArrayList<Entity> drawList = gameGrid.getDrawList();
    	Graphics g = panel.getGraphics();
    	
    	for(Entity e : drawList) {
    		int x = e.getX() * gridScale;
    		int y = e.getY() * gridScale;
    		g.drawImage(spriteSheet[e.getSprite()][e.getFrame()], x, y, gridScale, gridScale, null);
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
            		System.out.println("Read frame " + j + " of sprite at " + spriteIndex.path());
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