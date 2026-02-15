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
		
		setSprite(SpriteList.SPR_START_BUTTON);
	}
	
	public void onActivate() {
		getParent().requestState(nextState);
	}
}
