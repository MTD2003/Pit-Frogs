package bots;

// TODO: Cleanup logic after UI implementation.
public class NaiveAgent {
	private BitGrid position;
	private MoveTuple[] moveQueue; // Keeps track of the best moves for each position.
	private final int maxDepth; // Not a bad idea to change into nodes...
	// private boolean searching; // Maybe will used this for multithreading later...
	
	public NaiveAgent(int maxDepth) {
		this.maxDepth = maxDepth;
		moveQueue = new MoveTuple[maxDepth + 1];
		clearQueue();
	}
	
	public void startSearch(int x, int y) {
		int index = position.convertPos(x, y);
		search(position, index, 0);
	}
	
	private int search(BitGrid sPosition, int index, int depth) {
		// depth % 2 = 0 -> orthogonal
		for(int i = depth % 2; i <= 7; i += 2) {
			int curEval = -1;
			MoveTuple move = MoveTuple.genMove(i);
			
			if(!sPosition.isLegal(move, index)) // Don't evaluate illegal moves.
				continue;
			
			curEval = 1;
			if(!sPosition.isDead(move, index)) {
				if(depth < maxDepth) {
					int nextIndex = index + sPosition.convertPos(move.getX(), move.getY());
					boolean dropPit = (depth % 2 == 1);
					BitGrid nextPosition = sPosition.doMove(move, index, dropPit);
					// System.out.println(nextPosition);
					// System.out.println(nextPosition);
					curEval = 2 + search(nextPosition, nextIndex, (depth + 1));
				
				} else {
					curEval = 2; // When maxDepth is reached, we "peak" forward and assign a 1 eval to safe moves.
				}
			}
			
			if(curEval > moveQueue[depth].getEval()) {
				move.setEval(curEval);
				//System.out.println("DEPTH: " + depth + " BEST: " + bestEval);
				moveQueue[depth] = move;
			}
		}
		
		return moveQueue[depth].getEval(); // Base case: Returns -1 if no legal moves can be made.
	}
	
	// Pops the move in slot 1, then places the next move at the front of the moveQueue.
	public MoveTuple popMove() {
		MoveTuple oldMove = moveQueue[0];
		for(int i = 0; i < maxDepth; i++) {
			moveQueue[i] = moveQueue[i + 1];
			moveQueue[i + 1] = MoveTuple.genMove(-1);
		}
		
		//System.out.println(oldMove);
		return oldMove;
	}
	
	// Clears the moveQueue following a round.
	public void clearQueue() {
		for(int i = 0; i < moveQueue.length; i++)
			moveQueue[i] = MoveTuple.genMove(-1);
	}
	
	public void setPosition(BitGrid position) {
		this.position = position;
	}
}
