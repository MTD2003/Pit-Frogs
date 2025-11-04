package menu;

import controllers.State;
import view.Interactable;

public class MenuLink extends MenuText implements Interactable {
	private String text;
	private State parent;
	
	public MenuLink(State parent, String text, int x, int y, int width, int height) {
		super(text, x, y, width, height);
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
