package controllers;

import menu.ChangeButton;
import menu.MenuText;
import menu.SpecialButton;
import utilities.GridConsts;
import utilities.InputMirror;
import utilities.SpriteList;
import view.InteractBox;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class MenuState implements State {
	private Game gameObj;
	private SpecialButton btnSpec;
	private ChangeButton[] buttons;
	private MenuText[] menuValues;
	private ArrayList<InteractBox> hitboxes;
	
	// Not my favorite way to do this, but it is a way.
	private int scale;
	private int[] argGrid; 
	private static final int PLAYERS = 0;
	private static final int SPACES = 1;
	private static final int TIMER = 2;
	
	public MenuState(Game gameObj) {
		this.gameObj = gameObj;
		
		argGrid = new int[3];
		argGrid[PLAYERS] = GridConsts.MIN_PLAYERS;
		argGrid[SPACES] = GridConsts.MIN_SIZE;
		argGrid[TIMER] = GridConsts.MAX_TIMER / 2;
		
		loadText();
		loadButtons();
		updateText();
	}
	
	private void loadText() {
		int fontSize = 48;
		int height = Game.SCREEN_HEIGHT / 2 + fontSize * 2;
		int width = (Game.SCREEN_WIDTH - fontSize) / 6;
		Font fontBasic = new Font("Consolas", Font.PLAIN, fontSize);
		
		menuValues = new MenuText[3];
		menuValues[PLAYERS] = new MenuText("", width, height, fontSize, fontBasic);
		menuValues[SPACES] = new MenuText("", width * 3, height, fontSize, fontBasic);
		menuValues[TIMER] = new MenuText("", width * 5, height, fontSize, fontBasic);
		// Special button is a version of MenuText... may change later.
		btnSpec = new SpecialButton(this, "START GAME", width - fontSize / 5, height / 2, (int)(fontSize * 1.5), fontBasic);
	}
	
	private void updateText() {
		menuValues[PLAYERS].setText(argGrid[PLAYERS]);
		menuValues[SPACES].setText(argGrid[SPACES]);
		menuValues[TIMER].setText(argGrid[TIMER]);
	}
	
	private void loadButtons() {
		int x, y, xOffset, yOffset, calc;
		scale = SpriteList.SPRITE_DIMENSIONS * 5;
		buttons = new ChangeButton[TIMER * 3];
		
		x = (Game.SCREEN_WIDTH - scale) / 7;
		y = (Game.SCREEN_HEIGHT - scale) / 2;
		for(int i = 0; i < TIMER * 3; i++) {
			xOffset = (scale * 2) * (i / 2);
			yOffset = (scale * 2) * (i % 2);
			calc = 1 - 2 * (i % 2);
			if(i >= (TIMER * 2))
				calc *= 3;
			
			buttons[i] = new ChangeButton(this, x + xOffset, y + yOffset, i / 2, calc);
		}
				
		loadHitboxes();
	}
	
	private void loadHitboxes() {
		hitboxes = new ArrayList<InteractBox>();
		for(ChangeButton btn : buttons) {
			InteractBox ib = new InteractBox(btn, btn.getX(), btn.getY(), scale, scale);
			hitboxes.add(ib);
		}
		InteractBox special = new InteractBox(btnSpec, btnSpec.getX(), btnSpec.getY(), btnSpec.deriveWidth(), (int)btnSpec.getSize());
		hitboxes.add(special);
	}
	
	private void updateHitboxes() {
		// Blank for now.
	}
	
	private int checkMinMax(int value, int minVal, int maxVal) {
		return Math.min(maxVal, Math.max(value, minVal));
	}
	
	// Near carbon copy of GridState logic, should be moved to Game for reusability(?)
	private void inputProcessing() {
		InputMirror inputs = gameObj.getInputMirror();
		int heldKey = inputs.getHeld(), lastKey = inputs.getKey();
    	int mouseX = inputs.getMouseX(), mouseY = inputs.getMouseY();
    	int mouseState = inputs.getMouseState();
    	int usedKey = (heldKey == InputMirror.KEY_EMPTY ? lastKey : InputMirror.KEY_EMPTY); // Only register a used key if held is empty.
    	
    	hoverCheck(mouseX, mouseY, heldKey, mouseState);
    	inputCheck(mouseX, mouseY, usedKey, mouseState);
	}
	
	private void hoverCheck(int mouseX, int mouseY, int heldKey, int mouseState) {
		boolean mouseHeld = (mouseState == InputMirror.MOUSE_HELD);
    	for(InteractBox ib : hitboxes)
    		ib.checkHold(mouseX, mouseY, heldKey, mouseHeld);
    }
	
	private void inputCheck(int mouseX, int mouseY, int usedKey, int mouseState) {
    	if(mouseState == InputMirror.MOUSE_CLICK) {
    		for(InteractBox ib : hitboxes)
    			ib.checkHit(mouseX, mouseY);
    	} else if(usedKey != InputMirror.KEY_EMPTY) {
    		for(InteractBox ib : hitboxes)
    			ib.checkBind(usedKey);
    	}
    }

	public void step() {
		inputProcessing();
	}

	public void draw(Graphics g) {
		updateText();
		for(ChangeButton btn : buttons) {
			BufferedImage sprite = gameObj.getSprite(btn.getSprite(), btn.getFrame());
			g.drawImage(sprite, btn.getX(), btn.getY(), scale, scale, null);
		}
		
		for(MenuText text : menuValues)
			text.selfDraw(g);
		
		btnSpec.selfDraw(g);
	}
	
	public void changePlanters(int index, int modifier) {
		if(index < argGrid.length) {
			argGrid[index] += modifier;
			
			argGrid[PLAYERS] = checkMinMax(argGrid[PLAYERS], GridConsts.MIN_PLAYERS, GridConsts.MAX_PLAYERS);
			argGrid[SPACES] = checkMinMax(argGrid[SPACES], GridConsts.MIN_SIZE, GridConsts.MAX_SIZE);
			argGrid[TIMER] = checkMinMax(argGrid[TIMER], GridConsts.MIN_TIMER, GridConsts.MAX_TIMER);
		}
	}
	
	public void menuSpecialFunction() {
		GridState stateNew = new GridState(gameObj, argGrid[PLAYERS], argGrid[SPACES], argGrid[TIMER]);
		gameObj.swapState(stateNew);
	}
}