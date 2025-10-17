package controllers;

import elements.Grid;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import javax.imageio.ImageIO;
import utilities.SpriteList;
import view.*;

public class Game {
    //private int scale;
    //private int keyInput;
    private BufferedImage[] spriteSheet;
    
    public Game() {
        Grid gameGrid = new Grid(7);
        Scanner scans = new Scanner(System.in);
        GameView window = new GameView();
        GamePanel panel = new GamePanel();
        window.addComponent(panel);

        int i = 0;
        int input;
        loadSprites();
        /* Diagonal Movement Code -> Slightly more elegant than manual code.
        int move[] = {-1, -1};
        for(int xy = 0; xy < 4; xy++) {
            griddy[xy % 2] *= -1;
            System.out.println(griddy[0] + ", " + griddy[1]);
            makeClicker(move);
        }
        */
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
    }

    private void loadSprites() {
        InputStream curStream;
        spriteSheet = new BufferedImage[SpriteList.SPR_BLANK.ordinal() + 1];
        for(SpriteList spriteIndex : SpriteList.values()) {
            curStream = getClass().getResourceAsStream(spriteIndex.path());
            System.out.println(spriteIndex.path());
            try {
                spriteSheet[spriteIndex.ordinal()] = ImageIO.read(curStream);
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