package menu;

import controllers.MenuState;
import view.Interactable;

import java.awt.Font;

// Performs special functions depending on the MenuState.
public class SpecialButton extends MenuText implements Interactable {
	private MenuState parent;
	private Font fontBase;
	
	public SpecialButton(MenuState parent, String text, int x, int y, float sizeBase, Font fontBase) {
		super(text, x, y, sizeBase, fontBase);
		this.parent = parent;
		this.fontBase = fontBase;
	}
	
	public int getTrueY() {
		return (int)(getY() - getSize() * 0.8);
	}
	
	public void onNothing() {
		setFont(fontBase);
	}
	
	public void onHover() {
		setFont(fontBase.deriveFont(Font.BOLD));
	}
	
	public void onPress() {
		setFont(fontBase.deriveFont(Font.ITALIC));
	}
	
	public void onActivate() {
		parent.menuSpecialFunction();
	}
}
