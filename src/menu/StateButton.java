package menu;

import controllers.StartState;
import view.Interactable;

import java.awt.Font;

// TODO: Change to a real button with a real sprite.
// Performs special functions depending on the MenuState.
public class StateButton extends MenuText implements Interactable {
	private StartState parent;
	private Font fontBase;
	
	public StateButton(StartState parent, String text, int x, int y, float sizeBase, Font fontBase) {
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
		parent.startRound();
	}
}
