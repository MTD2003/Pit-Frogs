package menu;

import controllers.MenuState;
import view.Interactable;

import java.awt.Font;

// Performs special functions depending on the MenuState.
public class SpecialButton extends MenuText implements Interactable {
	private MenuState parent;
	private Font fontBase;
	
	public SpecialButton(MenuState parent, String text, int x, int y, float textSize, Font fontBase) {
		super(text, x, y, textSize, fontBase);
		this.parent = parent;
		this.fontBase = fontBase;
	}
	
	public void onNothing() {
		setFont(fontBase);
	}
	
	public void onHover() {
		setFont(fontBase.deriveFont(Font.BOLD));
	}
	
	public void onPress() {
		
	}
	
	public void onActivate() {
		parent.menuSpecialFunction();
	}
	
	
}
