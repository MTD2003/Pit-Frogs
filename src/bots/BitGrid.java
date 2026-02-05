package bots;
import java.util.BitSet;

public class BitGrid {
	private int dimensions;
	private BitSet gridBits;
	private BitSet playerBits;
	
	public BitGrid(int dimensions) {
		this.dimensions = dimensions;
		gridBits = new BitSet(length());
		playerBits  = new BitSet(length());
	}
	
	/* Haven't found a use for this one yet.
	public BitGrid(int dimensions, BitSet gridBits) {
		this.dimensions = dimensions;
		this.gridBits = gridBits;
		playerBits  = new BitSet(length());
	}
	*/
	
	public BitGrid(BitGrid bitGrid) {
		this.dimensions = bitGrid.getDimensions();
		gridBits = new BitSet(length()); 
		playerBits = new BitSet(length());
		
		gridBits.or(bitGrid.getBits()); // Creates a deep clone of the BitSets.
		playerBits.or(bitGrid.getPlayers());
	}
	
	public int convertPos(int x, int y) {
		return x + y * (dimensions);
	}
	
	/* Function for reversing index into an x and y.
	public GridPos convertPos(int index) {
		return (new GridPos(index % dimensions, index / dimensions));
	}
	*/
	
	// Returns a new grid after applying a MoveTuple.
	public BitGrid doMove(MoveTuple move, int lastIndex, boolean dropPit) {
		BitGrid nextGrid = new BitGrid(this);
		int playerIndex = lastIndex + convertPos(move.getX(), move.getY()); // Add the movement vector to last move's end position.
		
		if(!nextGrid.getBits().get(playerIndex));
			nextGrid.setPlayer(true, playerIndex);
		nextGrid.setPlayer(false, lastIndex);
		
		if(dropPit)
			nextGrid.setBit(true, lastIndex);
		
		return nextGrid;
	}
	
	// Returns true if a move would be illegal (player moving to player).
	// These moves are impossible to make and thus never evaluated.
	public boolean isLegal(MoveTuple move, int moverIndex) {
		int trueX = (moverIndex % dimensions) + move.getX();
		int trueY = (moverIndex / dimensions) + move.getY();
		int index = moverIndex + convertPos(move.getX(), move.getY());
		
		if(trueX < 0 || trueX >= dimensions || trueY < 0 || trueY >= dimensions) // Check if its within bounds.
			return false;
		
		// System.out.println("Index: " + index + " vs. " + playerBits);
		return !(playerBits.get(index));
	}
	
	// Returns true if a move would result in player death.
	// Halts further search, causes a non-positive evaluation.
	public boolean isDead(MoveTuple move, int moverIndex) {
		int index = moverIndex + convertPos(move.getX(), move.getY());
		return gridBits.get(index);
	}
	
	// Compares the gridBits and playerBits with a newly generated positional BitSet.
	/* -1 if move is illegal (player-to-player), 0 if move is loss (into pit), 1 if move is survivable.
	public int evalResult(MoveTuple move, int moverIndex) {
		BitSet moveBits = new BitSet(dimensions * dimensions);
		int firstIndex = moverIndex + convertPos(move.getFirstX(), move.getFirstY());
		int endIndex = firstIndex + convertPos(move.getFinalX(), move.getFinalY());
		int eval = 1;
		
		moveBits.set(firstIndex);
		moveBits.set(endIndex);
		
		if(moveBits.intersects(gridBits)) // eval = 0 if intersects a pit.
			eval--;
		
		// Remember to "kill" the player bit when they fall into a pit, as that does not count as a block.
		// This works for the naive agent but will need to  be changed for the smart agent.
		if(moveBits.intersects(playerBits)) // eval = -1 if intersects player bit.
			eval--;
		
		System.out.println(eval);
		
		return eval;
	}
	*/
	
	public int length() {
		return dimensions * dimensions;
	}
	
	public int getDimensions() {
		return dimensions;
	}
	
	public BitSet getBits() {
		return gridBits;
	}
	
	public BitSet getPlayers() {
		return playerBits;
	}
	
	public void setBit(boolean value, int x, int y) {
		setBit(value, convertPos(x, y));
	}
	
	public void setBit(boolean value, int index) {
		gridBits.set(index, value);
	}
	
	public void setPlayer(boolean value, int x, int y) {
		setPlayer(value, convertPos(x, y));
	}
	
	public void setPlayer(boolean value, int index) {
		playerBits.set(index, value);
		// System.out.println(index + ": " + playerBits.get(index));
	}
	
	public String toString() {
		String myGridBits = "";
		for(int i = 0; i < length(); i++) {
			if(i % dimensions == 0)
				myGridBits += "\n";
			if(gridBits.get(i)) {
				myGridBits += "X ";
			} else {
				myGridBits += "_ ";
			}
		}
		return myGridBits;
	}
}