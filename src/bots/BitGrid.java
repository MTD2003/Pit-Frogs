package bots;
import java.util.BitSet;

public class BitGrid {
	private int dimensions;
	private BitSet gridBits;
	
	public BitGrid(int dimensions) {
		this.dimensions = dimensions;
		gridBits = new BitSet(dimensions * dimensions);
	}
	
	public BitGrid(int dimensions, BitSet gridBits) {
		this.dimensions = dimensions;
		this.gridBits = gridBits;
	}
	
	private int convertPos(int x, int y) {
		return x + y * (dimensions);
	}
	
	public int getDimensions() {
		return dimensions;
	}
	
	public BitSet getBits() {
		return gridBits;
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
	
	public String toString() {
		return gridBits.toString();
	}
}