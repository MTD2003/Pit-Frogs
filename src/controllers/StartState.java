package controllers;

import menu.StateButton;
import utilities.SpriteList;

public class StartState extends MenuState {
	public StartState(Game gameObj) {
		super(gameObj);
		
		setScale(3);
		loadButtons();
	}
	
	private void loadButtons() {
		int posScale = getScale() * SpriteList.SPRITE_DIMENSIONS;
		int startScale = getScale() * SpriteList.SPR_START_BUTTON.width() / 2;
		int x = Game.SCREEN_WIDTH / 2 - startScale;
		int y = (Game.SCREEN_HEIGHT - posScale) / 2;
		
		addButton(new StateButton(this, x, y - posScale * 2, StateIndex.GRID));
		addButton(new StateButton(this, x + posScale, y, StateIndex.OPTIONS));
		/*
		int x, y, xOffset, yOffset, modifier;
		int posScale = getScale() * SpriteList.SPRITE_DIMENSIONS;
		for(int i = 0; i < 6; i++) {
			xOffset = (posScale * 2) * (i / 2);
			yOffset = (posScale * 2) * (i % 2);
			modifier = 1 - 2 * (i % 2);
			if(i >= TIMER)
				modifier *= 3;
			
			addButton(new ChangeButton(this, x + xOffset, y + yOffset, i, modifier));
		}
		*/
	}
	
	public void updateText() {
		// Nothing to update.
	}
	
	public void updateSprites() {
		// Also nothing to update.
	}
}