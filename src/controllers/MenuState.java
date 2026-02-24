package controllers;
import elements.Entity;
import menu.MenuButton;
import menu.MenuText;
import utilities.InputMirror;
import view.InteractBox;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

// Generic MenuState that StartState and OptionState build on.
public abstract class MenuState implements State {
	private Game gameObj;
	private ArrayList<MenuButton> buttons;
	private ArrayList<InteractBox> hitboxes;
	private ArrayList<MenuText> menuLabels;
	private ArrayList<Entity> specialSprites;
	
	private int scale;
	
	public enum StateIndex { MENU, OPTIONS, GRID }
	
	public MenuState(Game gameObj) {
		this.gameObj = gameObj;
		
		buttons = new ArrayList<MenuButton>();
		hitboxes = new ArrayList<InteractBox>();
		menuLabels = new ArrayList<MenuText>();
		specialSprites = new ArrayList<Entity>();
		
		scale = 1; // Setting this for safety.
	}
	
	public void step() {
		inputProcessing();
	}
	
	public void draw(Graphics g) {
		for(MenuButton btn : buttons) {
			BufferedImage sprite = gameObj.getSprite(btn.getSprite(), btn.getFrame());
			int width = btn.getWidth() * scale * btn.getScaleX();
			int height = btn.getHeight() * scale * btn.getScaleY();
			g.drawImage(sprite, btn.getX(), btn.getY(), width, height, null);
		}
		
		for(Entity e : specialSprites) {
			BufferedImage sprite = gameObj.getSprite(e.getSprite(), e.getFrame());
			int width = e.getWidth() * scale * e.getScaleX();
			int height = e.getHeight() * scale * e.getScaleY();
			g.drawImage(sprite, e.getX(), e.getY(), width, height, null);
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
			case MENU:
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
	
	// Don't know if I'll use this function
	public void clearScreen() {
		buttons.clear();
		hitboxes.clear();
		menuLabels.clear();
	}
	
	public void addSprite(Entity spr) {
		specialSprites.add(spr);
	}
	
	public void addButton(MenuButton btn) {
		buttons.add(btn);
		loadHitbox(btn);
		// updateHitboxes();
	}
	
	public void addText(MenuText txt) {
		menuLabels.add(txt);
	}
	
	public void updateTextAt(int index, int newText) {
		menuLabels.get(index).setText(newText);
	}
	
	public void updateTextAt(int index, String newText) {
		menuLabels.get(index).setText(newText);
	}
	
	public void loadHitbox(MenuButton btn) {
		int width = btn.getWidth() * scale * btn.getScaleX();
		int height = btn.getHeight() * scale * btn.getScaleY();
		InteractBox ib = new InteractBox(btn, btn.getX(), btn.getY(), width, height);
		hitboxes.add(ib);
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
