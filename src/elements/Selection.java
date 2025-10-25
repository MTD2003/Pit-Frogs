package elements;

import utilities.SpriteList;
import view.Interactable;

public class Selection extends Entity implements Interactable {
	//private final int[] move;
	private Player player; // Remove if logic doesn't need it.
	
	public Selection(Player player, int x, int y) {
		super(x, y);
		this.player = player;
		
		setSprite(SpriteList.SPR_GRID_CURSOR);
	}
	
	// TODO: Create sprites for Hover.
	public void onHover() {
		
	}
	
	// TODO: Force move on Grid.
	public void onActivate() {
		
	}
}