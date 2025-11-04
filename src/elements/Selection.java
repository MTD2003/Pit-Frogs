package elements;
import utilities.SpriteList;
import view.Interactable;

import java.awt.event.KeyEvent;

public class Selection extends Entity implements Interactable {
	//private final int[] move;
	private Grid grid;
	private final int playerIndex; // Remove if logic doesn't need it.
	
	private static final int SPR_IDLE = 0;
	private static final int SPR_HOVER = 1;
	private static final int SPR_HOLD = 2;
	
	
	public Selection(Grid grid, int playerIndex, int x, int y) {
		super(x, y);
		this.grid = grid;
		this.playerIndex = playerIndex;
		
		setSprite(SpriteList.SPR_GRID_CURSOR);
	}
	
	// Returns the numpad keybind based on the given X and Y direction.
	public int calcKeyBind() {
		Player player = grid.getPlayerAt(playerIndex);
		int xDirection = getX() - player.getX();
		int yDirection = getY() - player.getY();
		int numpad = 5;
		
		numpad += Math.signum(xDirection);
		numpad -= Math.signum(yDirection) * 3;
		return numpad + KeyEvent.VK_NUMPAD0;
	}
	
	public void onNothing() {
		setFrame(SPR_IDLE);
	}
	
	public void onHover() {
		setFrame(SPR_HOVER);
	}
	
	public void onPress() {
		setFrame(SPR_HOLD);
	}
	
	public void onActivate() {
		grid.playerMove(playerIndex, getX(), getY());
	}
}