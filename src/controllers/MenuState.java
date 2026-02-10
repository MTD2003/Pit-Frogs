package controllers;
import menu.ChangeButton;
import menu.MenuText;
import utilities.InputMirror;
import view.InteractBox;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

// Generic MenuState that StartState and OptionState build on.
public abstract class MenuState {
	private Game gameObj;
	private ArrayList<ChangeButton> buttons; // Change into generic button.
	private ArrayList<InteractBox> hitboxes;
	private ArrayList<MenuText> menuLabels;
	
	private int scale;
	
	public enum StateIndex { START, OPTIONS, GRID }
	
	public MenuState(Game gameObj) {
		this.gameObj = gameObj;
		// scale = ???
		buttons = new ArrayList<ChangeButton>();
		hitboxes = new ArrayList<InteractBox>();
		menuLabels = new ArrayList<MenuText>();
	}
	
	public void step() {
		inputProcessing();
	}
	
	public void draw(Graphics g) {
		// updateText(); // Find another way to do necessary updates?
		for(ChangeButton btn : buttons) {
			BufferedImage sprite = gameObj.getSprite(btn.getSprite(), btn.getFrame());
			g.drawImage(sprite, btn.getX(), btn.getY(), scale, scale, null);
		}
		
		for(MenuText text : menuLabels)
			text.selfDraw(g);
	}
	
	// Generic inputProcessing thing...
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
	
	// Causes the Game object to switch to an alternate state.
	public void requestState(StateIndex stateNum) {
		State nextState;
		switch(stateNum) {
			case START:
				nextState = new StartState(gameObj);
				gameObj.swapState(nextState);
				break;
			case OPTIONS:
				nextState = new OptionState(gameObj);
				gameObj.swapState(nextState);
				break;
			case GRID:
				nextState = new GridState(gameObj);
				gameObj.swapState(nextState);
				break;
		}
	}
	
	public void clearScreen() {
		buttons.clear();
		hitboxes.clear();
		menuLabels.clear();
	}
	
	public void addButton(ChangeButton btn) {
		buttons.add(btn);
		// updateHitboxes();
	}
	
	public void addText(MenuText txt) {
		menuLabels.add(txt);
	}
	
	public Game getGame() {
		return gameObj;
	}
	
	public int getScale() {
		return scale;
	}
	
	public void setGame(Game gameObj) {
		this.gameObj = gameObj;
	}
	
	public void setScale(int scale) {
		this.scale = scale;
	}
}
