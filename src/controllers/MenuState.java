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
	private MenuText[] menuLabels;
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
		argGrid[SPACES] = GridConsts.MIN_SIZE + 1;
		argGrid[TIMER] = GridConsts.MAX_TIMER / 2;
		
		loadText();
		loadButtons();
		updateText();
	}
	
	private void loadText() {
		int xCur, yCur;
		int fontSize = 48;
		int y = Game.SCREEN_HEIGHT / 2 + fontSize * 2;
		int x = (Game.SCREEN_WIDTH - fontSize) / 6;
		Font fontBasic = new Font("Consolas", Font.PLAIN, fontSize);
		menuLabels = new MenuText[6];
		
		xCur = x + 12;
		yCur = y + fontSize * 3;
		menuLabels[PLAYERS] = new MenuText("", xCur, y, fontSize, fontBasic);
		menuLabels[PLAYERS + 3] = new MenuText("Players", xCur - 32, y + fontSize * 3, fontSize / 2, fontBasic);
		
		xCur = (int)(x * 2.8);
		menuLabels[SPACES] = new MenuText("", xCur, y, fontSize, fontBasic);
		menuLabels[SPACES + 3] = new MenuText("Grid", xCur + 10, yCur, fontSize / 2, fontBasic);
		
		xCur = (int)(x * 4.9);
		menuLabels[TIMER] = new MenuText("", xCur, y, fontSize, fontBasic);
		menuLabels[TIMER + 3] = new MenuText("Timer", xCur - 6, yCur, fontSize / 2, fontBasic);
		
		// Special button is a version of MenuText... may change later.
		btnSpec = new SpecialButton(this, "START GAME", x - fontSize / 5, y / 2, (int)(fontSize * 1.5), fontBasic);
	}
	
	private void updateText() {
		menuLabels[PLAYERS].setText(argGrid[PLAYERS]);
		menuLabels[SPACES].setText(argGrid[SPACES] + "x" + argGrid[SPACES]);
		String timerText = String.format("%02d", argGrid[TIMER]);
		menuLabels[TIMER].setText(timerText);
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
		
		InteractBox special = new InteractBox(btnSpec, btnSpec.getX(), btnSpec.getTrueY(), btnSpec.deriveWidth(), (int)btnSpec.getSize());
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
		
		for(MenuText text : menuLabels)
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