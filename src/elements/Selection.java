package elements;

import utilities.SpriteList;

public class Selection extends Entity {
	private final int[] move;
	private Player player; // Remove if logic doesn't need it.
	
	public Selection(Player player, int x, int y, int[] move) {
		super(x, y);
		this.player = player;
		this.move = move;
		
		setSprite(SpriteList.SPR_GRID_CURSOR);
	}
	
	public int[] getMove() {
		return move;
	}
}