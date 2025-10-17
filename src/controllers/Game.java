package controllers;

import elements.Grid;
import elements.Entity;
import java.util.ArrayList;
import utilities.SpriteList;
import view.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class Game {
	//private int scale;
    //private int keyInput;
    private BufferedImage[][] spriteSheet;
    private Grid gameGrid;
    private GameView window;
    private GamePanel panel;
    
    public Game() {
        gameGrid = new Grid(7);
        
        window = new GameView();
        panel = new GamePanel();
        window.addComponent(panel);

        
        loadSprites();
        draw();
        //panel.getGraphics().drawImage(spriteSheet[5][0], 0, 0, 128, 128, null);
        
        /* Diagonal Movement Code -> Slightly more elegant than manual code.
        int move[] = {-1, -1};
        for(int xy = 0; xy < 4; xy++) {
            griddy[xy % 2] *= -1;
            System.out.println(griddy[0] + ", " + griddy[1]);
            makeClicker(move);
        }
        */
        
        /*
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
    
    private void step() {
    	
    }
    
    private void draw() {
    	ArrayList<Entity> drawList = gameGrid.getDrawList();
    	Graphics g = panel.getGraphics();
    	
    	for(Entity e : drawList) {
    		// TODO: Implement proper scaling.
    		int x = e.getX() * 56;
    		int y = e.getY() * 48;
    		g.drawImage(spriteSheet[e.getSprite()][e.getFrame()], x, y, 48, 48, null);
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