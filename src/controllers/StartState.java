package controllers;
import menu.ChangeButton;
import menu.MenuText;
import menu.StateButton;
import utilities.SpriteList;

import java.awt.Font;

public class StartState extends MenuState {
	// Indexes for the Menu Text
	private static final int PLAYERS = 0;
	private static final int SPACES = PLAYERS + 2;
	private static final int TIMER = SPACES + 2;
	
	public StartState(Game gameObj) {
		super(gameObj);
		
		setScale(5);
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
		
		xCur = x + 12;
		yCur = y + fontSize * 3;
		addText(new MenuText("", xCur, y, fontSize, fontBasic));
		addText(new MenuText("Players", xCur - 32, y + fontSize * 3, fontSize / 2, fontBasic));
		
		xCur = (int)(x * 2.8);
		addText(new MenuText("", xCur, y, fontSize, fontBasic));
		addText(new MenuText("Grid", xCur + 10, yCur, fontSize / 2, fontBasic));
		
		xCur = (int)(x * 4.9);
		addText(new MenuText("", xCur, y, fontSize, fontBasic));
		addText(new MenuText("Timer", xCur - 6, yCur, fontSize / 2, fontBasic));
	}
	
	private void updateText() {
		int settingP = GridState.getPlayerP();
		int settingS = GridState.getSizeP();
		int settingT = GridState.getTimeP();
		
		String timerText = String.format("%02d", settingT);
		String settingText = settingS + "x" + settingS;
		
		updateText(PLAYERS, settingP);
		updateText(SPACES, settingText);
		updateText(TIMER, timerText);
	}
	
	private void loadButtons() {
		int x, y, xOffset, yOffset, calc;
		int posScale = getScale() * SpriteList.SPRITE_DIMENSIONS;

		x = (Game.SCREEN_WIDTH - posScale) / 7;
		y = (Game.SCREEN_HEIGHT - posScale) / 3;
		for(int i = 0; i < 6; i++) {
			xOffset = (posScale * 2) * (i / 2);
			yOffset = (posScale * 2) * (i % 2);
			calc = 1 - 2 * (i % 2);
			if(i >= TIMER)
				calc *= 3;
			
			addButton(new ChangeButton(this, x + xOffset, y + yOffset, i / 2, calc));
		}
		addButton(new StateButton(this, x, y / 2, StateIndex.GRID));
	}
	
	// TODO: Change(?) or put in another class.
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
}