package bots;
import view.InteractBox;

import java.util.ArrayList;

public class NaiveAgent {
	private int x, y;
	private BitGrid position;
	private MoveTuple bestMove;
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
		MoveTuple curMove;
		// Move generation ???
		// Doesn't matter what order we generate / search these moves.
		// 16 total moves, divisible by four. Matching values are never found together.
		// 12 unique ending positions...
		for(int x = -2; x <= 2; x++) {
			for(int y = -2; y <= 2; y++) {
				curMove = new MoveTuple(-1, -1, -1, -1);
			}
		}
		/* End Positions. Need to account for movement paths.
		 * [ , ]   [-1,2]  [ , ]   [1,2]   [ , ]
		 * [-2,1]  [ , ]   [0,1]   [ , ]   [2,1]
		 * [ , ]   [-1,0]  [ , ]   [1,0]   [ , ]
		 * [-2,-1] [ , ]   [0,-1]  [ , ]   [2,-1]
		 * [ , ]   [-1,-2] [ , ]   [1,-2]  [ , ]
		 */
		
		/* Ideas and Notes
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
		*/
		
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
