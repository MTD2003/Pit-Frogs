package menu;

import controllers.MenuState;
import controllers.MenuState.StateIndex;
import utilities.SpriteList;

// Performs special functions depending on the MenuState.
public class StateButton extends MenuButton {
	private StateIndex nextState;
	
	public StateButton(MenuState parent, int x, int y, StateIndex nextState) {
		super(parent, x, y);
		this.nextState = nextState;
		
		spriteCheck();
	}
	
	private void spriteCheck() {
		switch(nextState) {
			case MENU:
				setSprite(SpriteList.SPR_MENU_BUTTON);
				break;
			case OPTIONS:
				setSprite(SpriteList.SPR_OPTION_BUTTON);
				break;
			case GRID:
				setSprite(SpriteList.SPR_START_BUTTON);
				break;
		}
	}
	
	public void onActivate() {
		getParent().requestState(nextState);
	}
}
