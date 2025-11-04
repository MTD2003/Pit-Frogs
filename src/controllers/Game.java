package controllers;
import utilities.InputMirror;
import utilities.SpriteList;
import view.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class Game implements Runnable {
    private BufferedImage[][] spriteSheet;
    private State stateObj;
    private GameView window;
    private GamePanel panel;
    
    public static final int FPS = 60;
    public static final int UPS = 120;
    
    public Game() {
        window = new GameView();
        panel = new GamePanel();
        stateObj = new GridState(this);
        
        window.addComponent(panel);
        loadSprites();
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
    			draw();
    			nanoFrameLast = System.nanoTime();
    		}
    	}
    }
    
    private void step() {
    	InputMirror inputs = panel.getInput();
    	stateObj.step();
    	
    	inputs.clearClick();
    	inputs.clearKey();
    }
    
    // TODO: Include border elements, centered scaling.
    private void draw() {
    	Graphics buffer = panel.getImage().getGraphics(); // Gets the image buffer, then takes the graphics from there.
    	
    	stateObj.draw(buffer);
    	panel.repaint();
    }
    
    // Loads all sprites provided in the SpriteList enum.
    private void loadSprites() {
        InputStream curStream;
        spriteSheet = new BufferedImage[SpriteList.size() + 1][]; // SPR_BLANK is at the end of the enum.
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
    
    // Return functions for the state.
    public InputMirror getInputMirror() {
    	return panel.getInput();
    }
    
    public int getWindowHeight() {
    	return window.getHeight();
    }
    
    public int getWindowWidth() {
    	return window.getWidth();
    }
    
    public BufferedImage getSprite(int spriteIndex, int imageIndex) {
    	return spriteSheet[spriteIndex][imageIndex];
    }
}