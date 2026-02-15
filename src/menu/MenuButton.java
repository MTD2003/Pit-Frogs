package menu;

import controllers.MenuState;
import elements.Entity;
import view.Interactable;

// Abstract class that combines elements from Entity and Interactable for polymorphism.
public abstract class MenuButton extends Entity implements Interactable {
	private MenuState parent;
	
	public MenuButton(MenuState parent, int x, int y) {
		super(x, y);
		this.parent = parent;
	}
	
	public void onNothing() {
		setFrame(0);
	}
	
	public void onHover() {
		setFrame(1);
	}
	
	public void onPress() {
		setFrame(2); // All menu buttons should have precisely 3 frames.
	}
	
	public abstract void onActivate(); // Undefined for this class.
	
	public MenuState getParent() {
		return parent;
	}
}