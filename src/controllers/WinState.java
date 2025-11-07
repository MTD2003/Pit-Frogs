package controllers;

import elements.Entity;
import elements.Grid;
import menu.MenuText;
import utilities.InputMirror;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

public class WinState implements State {
	private Game gameObj;
	private Grid gridObj;
	private MenuText winMessage;
	private MenuText exitMessage;
	private int scale;
	private int windex;
	
	public WinState(Game gameObj, Grid gridObj, int scale, int windex) {
		this.gameObj = gameObj;
		this.gridObj = gridObj;
		this.scale = scale;
		this.windex = windex;
		
		loadText();
	}
	
	private void loadText() {
		String winString;
		String exitString = "Press ESC key to return to main menu";
		if(windex == -1)
			winString = "NOBODY WINS!";
		else
			winString = "PLAYER " + (windex + 1) + " WINS!";
		
		int fontSize = 48;
		int x = Game.SCREEN_WIDTH / 7 + 8;
		int y = Game.SCREEN_HEIGHT - fontSize;
		Font fontBasic = new Font("Consolas", Font.PLAIN, fontSize);
		winMessage = new MenuText(winString, x, y, fontSize, fontBasic);
		exitMessage = new MenuText(exitString, x + 12, y + fontSize / 2, fontSize / 3, fontBasic);
	}
	
	private void inputProcessing() {
		InputMirror inputs = gameObj.getInputMirror();
		int lastKey = inputs.getKey();
		
		if(lastKey == KeyEvent.VK_ESCAPE) {
			MenuState menuscreen = new MenuState(gameObj);
			gameObj.swapState(menuscreen);
		}
	}
	
	public void step() {
		inputProcessing();
	}

	public void draw(Graphics g) {
		ArrayList<Entity> winlist = gridObj.getPlayerSpaces();
		
		for(Entity e : winlist) {
    		BufferedImage sprite = gameObj.getSprite(e.getSprite(), e.getFrame());
    		int x = e.getX() * scale;
    		int y = e.getY() * scale;
    		
    		g.drawImage(sprite, x, y, scale, scale, null);
		}
		
		winMessage.selfDraw(g);
		exitMessage.selfDraw(g);
	}
	
}
