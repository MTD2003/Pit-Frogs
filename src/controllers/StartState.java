package controllers;
import menu.ChangeButton;
import menu.MenuText;
import menu.StateButton;
import utilities.InputMirror;
import utilities.SpriteList;
import view.InteractBox;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class StartState implements State {
	private Game gameObj;
	private StateButton btnSpec;
	private ChangeButton[] buttons;
	private MenuText[] menuLabels;
	private ArrayList<InteractBox> hitboxes;
	
	private int scale;
	private static final int PLAYERS = 0;
	private static final int SPACES = 1;
	private static final int TIMER = 2;
	
	public StartState(Game gameObj) {
		this.gameObj = gameObj;
		
		loadText();
		loadButtons();
		updateText();
	}
	
	private void loadText() {
		int xCur, yCur;
		int fontSize = 48;
		int y = Game.SCREEN_HEIGHT / 2;
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
		btnSpec = new StateButton(this, "START GAME", x - fontSize / 5, y / 2, (int)(fontSize * 1.5), fontBasic);
	}
	
	private void updateText() {
		int settingP = GridState.getPlayerP();
		int settingS = GridState.getSizeP();
		int settingT = GridState.getTimeP();
		
		String timerText = String.format("%02d", settingT);
		
		menuLabels[PLAYERS].setText(settingP);
		menuLabels[SPACES].setText(settingS + "x" + settingS);
		menuLabels[TIMER].setText(timerText);
	}
	
	private void loadButtons() {
		int x, y, xOffset, yOffset, calc;
		scale = SpriteList.SPRITE_DIMENSIONS * 5;
		buttons = new ChangeButton[TIMER * 3];
		
		x = (Game.SCREEN_WIDTH - scale) / 7;
		y = (Game.SCREEN_HEIGHT - scale) / 3;
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
	
	// Near carbon copy of GridState logic, could be moved to Game for reusability(?)
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
		int origVal;
		switch(index) {
			case PLAYERS:
				origVal = GridState.getPlayerP();
				GridState.setPlayerP(origVal + modifier);
				break;
			case SPACES:
				origVal = GridState.getSizeP();
				GridState.setSizeP(origVal + modifier);
				break;
			case TIMER:
				origVal = GridState.getTimeP();
				GridState.setTimeP(origVal + modifier);
				break;
		}
	}
	
	public void startRound() {
		GridState stateNew = new GridState(gameObj);
		gameObj.swapState(stateNew);
	}
}