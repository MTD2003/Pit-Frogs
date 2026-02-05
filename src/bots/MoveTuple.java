package bots;

// Easier to read and more type safe than int[]
// TODO: Go back through the Grid move generation and design to accomodate.
public class MoveTuple {
	private int x, y, eval;
	
	private MoveTuple(int x, int y) {
		this.x = x;
		this.y = y;
		eval = 0;
	}
	
	// Returns a movement vector based on given integer.
	// This is lazy but also probably the most efficient way.
	public static MoveTuple genMove(int dir) {
		switch(dir) {
			case 0:
				return new MoveTuple(0, -1); // North
			case 1:
				return new MoveTuple(1, -1); // North-west
			case 2:
				return new MoveTuple(1, 0); // West, etc...
			case 3:
				return new MoveTuple(1, 1);
			case 4:
				return new MoveTuple(0, 1);
			case 5:
				return new MoveTuple(-1, 1);
			case 6:
				return new MoveTuple(-1, 0);
			case 7:
				return new MoveTuple(-1, -1);
			default:
				return new MoveTuple(0, 0); // The "blank"/invalid move.
		}
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int[] getVector() {
		int[] newInt = {x, y};
		return newInt;
	}
	
	public int getEval() {
		return eval;
	}
	
	public void setEval(int eval) {
		this.eval = eval;
	}
	
	public String toString() {
		return (x + ", " + y);
	}
}
