package controllers;

import utilities.GridConsts;
import utilities.InputMirror;
import view.Interactable;
import view.InteractBox;

import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import menu.MenuText;

public class MenuState implements State {
	private Game gameObj;
	private Interactable specialButton;
	private Interactable[] myButtons;
	private ArrayList<InteractBox> hitboxes;
	private MenuText[] menuValues;
	
	// Not my favorite way to do this, but it is a way.
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
		updateText();
	}
	
	private int checkMinMax(int value, int minVal, int maxVal) {
		return Math.min(maxVal, Math.max(value, minVal));
	}
	
	private void loadText() {
		int fontSize = 48;
		int height = Game.SCREEN_HEIGHT / 2 + fontSize;
		int width = (Game.SCREEN_WIDTH - fontSize) / 4;
		Font fontBasic = new Font("Consolas", Font.PLAIN, fontSize);

		menuValues = new MenuText[3];
		menuValues[PLAYERS] = new MenuText("", width, height, fontSize, fontBasic);
		menuValues[SPACES] = new MenuText("", width * 2, height, fontSize, fontBasic);
		menuValues[TIMER] = new MenuText("", width * 3, height, fontSize, fontBasic);
	}
	
	private void updateText() {
		menuValues[PLAYERS].setText(argGrid[PLAYERS]);
		menuValues[SPACES].setText(argGrid[SPACES]);
		menuValues[TIMER].setText(argGrid[TIMER]);
	}
	
	/*
	private void inputProcessing() {
		InputMirror inputs = gameObj.getInputMirror();
		int heldKey = inputs.getHeld(), lastKey = inputs.getKey();
    	int mouseX = inputs.getMouseX(), mouseY = inputs.getMouseY();
    	int mouseState = inputs.getMouseState();
    	int usedKey = (heldKey == InputMirror.KEY_EMPTY ? lastKey : InputMirror.KEY_EMPTY); // Only register a used key if held is empty.
    	
    	hoverCheck(mouseX, mouseY, heldKey, mouseState);
    	inputCheck(mouseX, mouseY, usedKey, mouseState);
	}
	*/

	public void step() {
		
	}

	public void draw(Graphics g) {
		updateText();
		
		for(MenuText text : menuValues)
			text.selfDraw(g);
	}
	
	public void changePlanters(int index, int modifier) {
		if(index < argGrid.length) {
			argGrid[index] += modifier;
			
			argGrid[PLAYERS] = checkMinMax(argGrid[PLAYERS], GridConsts.MIN_PLAYERS, GridConsts.MAX_PLAYERS);
			argGrid[SPACES] = checkMinMax(argGrid[PLAYERS], GridConsts.MIN_SIZE, GridConsts.MAX_SIZE);
			argGrid[TIMER] = checkMinMax(argGrid[PLAYERS], GridConsts.MIN_TIMER, GridConsts.MAX_TIMER);
		}
	}
	
	public void menuSpecialFunction() {
		GridState stateNew = new GridState(gameObj, argGrid[PLAYERS], argGrid[SPACES], argGrid[TIMER]);
		gameObj.swapState(stateNew);
	}
}