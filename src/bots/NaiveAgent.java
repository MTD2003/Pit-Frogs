package bots;

//import java.util.ArrayList;

// TODO: Cleanup logic after UI implementation.
// TODO: Implement / test in base game.
public class NaiveAgent {
	private BitGrid position;
	private MoveTuple bestMove, nextMove;
	private int bestEval;
	private int maxDepth; // Not a bad idea to change into nodes...
	// private boolean searching; // Maybe will used this for multithreading later...
	
	public NaiveAgent(int maxDepth) {
		this.maxDepth = maxDepth;
	}
	
	public void startSearch(int x, int y) {
		int index = position.convertPos(x, y);
		
		bestEval = -1;
		search(position, index, 0);
	}
	
	private int search(BitGrid sPosition, int index, int depth) {
		// depth % 2 = 0 -> orthogonal
		for(int i = depth % 2; i <= 7; i += 2) {
			int sEval = 0;
			MoveTuple move = MoveTuple.genMove(i);
			
			if(!sPosition.isLegal(move, index)) // Don't evaluate illegal moves.
				continue;
			
			if(!sPosition.isDead(move, index)) {
				if(depth < maxDepth) {
					int nextIndex = index + sPosition.convertPos(move.getX(), move.getY());
					boolean dropPit = (depth % 2 == 1);
					BitGrid nextPosition = sPosition.doMove(move, index, dropPit);
					// System.out.println(nextPosition);
					
					sEval = 1 + search(nextPosition, nextIndex, (depth + 1));
				
				} else {
					sEval = 1;
				}
			}
			
			if(sEval > bestEval) {
				bestEval = sEval;
				System.out.println("DEPTH: " + depth + " BEST: " + bestEval);
				setMove(move);
			}
		}
		
		return bestEval; // Base case: Returns -1 if no legal moves can be made, 0 if the move would kill the player, 1 if it wouldn't.
	}
	
	public MoveTuple popMove() {
		MoveTuple oldMove = bestMove;
		bestMove = nextMove;
		return oldMove;
	}
	
	public void setMove(MoveTuple newMove) {
		nextMove = bestMove;
		bestMove = newMove;
	}
	
	public void setPosition(BitGrid position) {
		this.position = position;
	}
}
