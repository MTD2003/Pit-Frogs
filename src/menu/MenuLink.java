package menu;

import controllers.Game;
import controllers.State;
import view.Interactable;

public class MenuLink implements Interactable {
	private State parent;
	private MenuLayout layout;
	private int x, y;
	
	public MenuLink(State parent) {
		this.parent = parent;
	}

	@Override
	public void onNothing() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onHover() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPress() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onActivate() {
		// TODO Auto-generated method stub
		
	}
}
