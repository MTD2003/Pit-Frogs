package bots;
import view.InteractBox;

import java.util.ArrayList;

public class NaiveAgent {
	private int x, y;
	private int move, nextMove;
	private int evalRecord; // Keeps track of the evaluation record for the search.
	private BitGrid position;
	private final int maxDepth; // Not a bad idea to change into nodes...
	
	public NaiveAgent(int x, int y, int maxDepth) {
		this.x = x;
		this.y = y;
		this.maxDepth = maxDepth;
	}
	
	// TODO: Return the move(s) it selects.
	public void startSearch() {
		int index = position.convertPos(x, y);
		search(position, index, 0);
	}
	
	private int search(BitGrid sPosition, int index, int depth) {
		int newMoveIndex
		// Move generation ???
		
		// Ideas and Notes
		depth += 1;
		if(true) {
			if(depth >= maxDepth * 2) { // Pit Frogs is a 2-move per turn game.
				
			} else {
				if(depth % 2 == 0) {
					BitGrid nPosition = BitGrid(sPosition);
					sPosition.setBit(true, sX, sY);
				}
				return search(sPosition, sX, sY, depth, maxDepth) + 1;
			}
		}
		
		return -1;
	}
	
	public void setPosition(BitGrid position) {
		this.position = position;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
}
