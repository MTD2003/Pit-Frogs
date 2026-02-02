package bots;
import java.util.BitSet;

public class BitGrid {
	private int dimensions;
	private BitSet gridBits;
	private BitSet playerBits;
	
	public BitGrid(int dimensions) {
		this.dimensions = dimensions;
		gridBits = new BitSet(dimensions * dimensions);
		playerBits  = new BitSet(dimensions * dimensions);
	}
	
	public BitGrid(int dimensions, BitSet gridBits) {
		this.dimensions = dimensions;
		this.gridBits = gridBits;
		playerBits  = new BitSet(dimensions * dimensions);
	}
	
	public BitGrid(BitGrid bitGrid) {
		this.dimensions = bitGrid.getDimensions();
		gridBits = bitGrid.getBits();
		playerBits = bitGrid.getPlayers();
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
	public BitGrid doMove(MoveTuple move, int moverIndex) {
		BitGrid nextGrid = new BitGrid(this);
		int pitIndex = moverIndex + convertPos(move.getFirstX(), move.getFirstY()); // Add the movement vector to original index.
		int playerIndex = pitIndex + convertPos(move.getFinalX(), move.getFinalY()); // Add the movement vector to last move's end position.
		
		nextGrid.setPlayer(false, moverIndex);
		nextGrid.setBit(false, moverIndex);
		nextGrid.setBit(true, pitIndex);
		nextGrid.setBit(true, playerIndex);
		nextGrid.setPlayer(true, playerIndex);
		
		System.out.println(nextGrid);
		return nextGrid;
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
		if(value) {
			gridBits.set(index);
		} else {
			gridBits.clear(index);
		}
		
		System.out.println(index + ": " + gridBits.get(index));
	}
	
	public void setPlayer(boolean value, int x, int y) {
		setPlayer(value, convertPos(x, y));
	}
	
	public void setPlayer(boolean value,  int index) {
		if(value) {
			playerBits.set(index);
		} else {
			playerBits.clear();
		}
	}
	
	
	public String toString() {
		return gridBits.toString();
	}
}