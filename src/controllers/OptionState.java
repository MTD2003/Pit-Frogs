package controllers;

//import controllers.MenuState.StateIndex;
import controllers.GridState;
import elements.Entity;
import menu.ChangeButton;
import menu.MenuText;
import menu.StateButton;
import utilities.GridConsts;
import utilities.SpriteList;

import java.awt.Font;

public class OptionState extends MenuState {
	/*
	private static final int PLAYERS = 0;
	private static final int SPACES = PLAYERS + 2;
	private static final int TIMER = SPACES + 2;
	*/
	public OptionState(Game gameObj) {
		super(gameObj);
		
		setScale(3);
		loadText();
		loadButtons();
		updateText();
	}
	
	private void loadText() {
		int xOffset = 0, yOffset = 0;
		int sFontSize = 16, mFontSize = 24;
		int posScale = getScale() * SpriteList.SPRITE_DIMENSIONS;
		int y = posScale * 3;
		int x = (Game.SCREEN_WIDTH - sFontSize) / 7;
		Font fontBasic = new Font("Consolas", Font.PLAIN, sFontSize);
		
		yOffset = posScale * 2;
		for(int i = 0; i < GridConsts.MAX_PLAYERS; i++) {
			String key = "AI_" + i;
			xOffset = (i * posScale * 3 - sFontSize - mFontSize);
			addText(key, new MenuText("Human", x + xOffset, y + yOffset, sFontSize, fontBasic));
		}
		
		yOffset = (Game.SCREEN_HEIGHT / 4) + (posScale * 2);
		xOffset = posScale;
		addText("PLAY_PLANT", new MenuText("3", x + xOffset, y + yOffset, mFontSize, fontBasic));
		xOffset += sFontSize * 8;
		addText("GRID_PLANT", new MenuText("6x6", x + xOffset, y + yOffset, mFontSize, fontBasic));
		xOffset += sFontSize * 8 + mFontSize;
		addText("TIME_PLANT", new MenuText("12", x + xOffset, y + yOffset, mFontSize, fontBasic));
		
		yOffset += posScale * 2;
		xOffset = mFontSize - sFontSize;
		addText("PLAYERS", new MenuText("Players", x + xOffset, y + yOffset, mFontSize, fontBasic));
		xOffset += sFontSize * 10;
		addText("GRID", new MenuText("Grid", x + xOffset, y + yOffset, mFontSize, fontBasic));
		xOffset += sFontSize * 10 - mFontSize;
		addText("TIMER", new MenuText("Timer", x + xOffset, y + yOffset, mFontSize, fontBasic));
		
		addText("MENU_LABEL_1", new MenuText("Player and AI Settings", x, y, sFontSize * 2, fontBasic));
		addText("MENU_LABEL_2", new MenuText("Grid Settings", x * 2, y + sFontSize * 10, sFontSize * 2, fontBasic));
	}
	
	// Loads menu buttons and additional sprites.
	private void loadButtons() {
		int modifier;
		int xOffset = 0, yOffset = 0;
		int posScale = getScale() * SpriteList.SPRITE_DIMENSIONS;
		int startScale = getScale() * SpriteList.SPR_START_BUTTON.width() / 2;
		int x = Game.SCREEN_WIDTH / 2 - startScale;
		int y = (Game.SCREEN_HEIGHT - posScale) / 3;
		
		addButton(new StateButton(this, x, y / 4, StateIndex.GRID));
		addButton(new StateButton(this, x + posScale, (y * 2) + (posScale * 3), StateIndex.MENU));
		
		ChangeButton curButton;
		for(int i = 0; i < GridConsts.MAX_PLAYERS; i++) {
			String key = "PLAYER_" + i;
			xOffset = (i * posScale * 3 - startScale);
			yOffset = (posScale / -2);
			
			addSprite(key, new Entity(SpriteList.playerSpriteAtIndex(i), x + xOffset, y + yOffset));
			
			yOffset += posScale;
			curButton = new ChangeButton(this, x + xOffset, y + yOffset, i, 1);
			curButton.setSprite(SpriteList.SPR_SIDE_ARROW);
			curButton.setScaleX(-1);
			addButton(curButton);
			
			xOffset += posScale;
			curButton = new ChangeButton(this, x + xOffset, y + yOffset, i, 2);
			curButton.setSprite(SpriteList.SPR_SIDE_ARROW);
			addButton(curButton);
			
			// TODO: Include an option for changing sprites.
		}
		
		x = (Game.SCREEN_WIDTH - startScale * 2) / 3;
		y = Game.SCREEN_HEIGHT / 2;
		for(int j = 0; j < 6; j++) {
			xOffset = (posScale * 3) * (j / 2);
			yOffset = (posScale * 2) * (j % 2);
			
			modifier = 1 - 2 * (j % 2);
			//if(i >= TIMER)
				//modifier *= 3;
			
			addButton(new ChangeButton(this, x + xOffset, y + yOffset, j, modifier));
		}
		
		updatePlayerSprites();
	}

	private void updateText() {
		/*
		int settingP = GridState.getPlayerP();
		int settingS = GridState.getSizeP();
		int settingT = GridState.getTimeP();
		
		String timerText = String.format("%02d", settingT);
		String settingText = settingS + "x" + settingS;
		
		updateText(PLAYERS, settingP);
		updateText(SPACES, settingText);
		updateText(TIMER, timerText);
		*/
	}
	
	// Updates all Player Sprites
	private void updatePlayerSprites() {
		for(int i = 0; i < GridConsts.MAX_PLAYERS; i++) {
			if(GridState.getPlayerP() <= i)
				updateSpriteAt(i, -1);
			else
				updateSpriteAt(i, i);
		}
	}
	
	public void updateSpriteAt(int index, int playerID) {
		updateSpriteAt("PLAYER_" + index, SpriteList.playerSpriteAtIndex(playerID));
	}
	
	public void changePlanters() {
		/*
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
		*/
	}
}
