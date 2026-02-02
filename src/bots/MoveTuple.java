package bots;

// Primarily used to simplify the move making process for agents.
public class MoveTuple {
	private int[] firstPos = {0, 0}; // Always a length of 2.
	private int[] finalPos = {0, 0};
	
	public MoveTuple(int x1, int y1, int x2, int y2) {
		firstPos[0] = x1;
		firstPos[1] = y1;
		finalPos[0] = x2;
		finalPos[1] = y2;
	}
	
	public int getFirstX() {
		return firstPos[0];
	}
	
	public int getFirstY() {
		return firstPos[1];
	}
	
	public int getFinalX() {
		return finalPos[0];
	}
	
	public int getFinalY() {
		return finalPos[1];
	}
}
