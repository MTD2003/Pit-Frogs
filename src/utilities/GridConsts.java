package utilities;

import java.awt.event.KeyEvent;

public class GridConsts {
	public static final int X = 0;
	public static final int Y = 1;
	
	public static final int ORTHOGONAL = 0;
	public static final int DIAGONAL = 1;
	
	public static final int MAX_SIZE = 12;
	public static final int MIN_SIZE = 6;
	
	public static final int MIN_PLAYERS = 2;
	public static final int MAX_PLAYERS = 4;
	
	// Returns the numpad keybind based on the given X and Y direction.
	// This doesn't really fit in any other class.
	public static int calcKeyBind(int x, int y) {
		int numpad = 5;
		numpad -= Math.signum(x) * 3;
		numpad += Math.signum(y);
		
		return numpad + KeyEvent.VK_NUMPAD0;
	}
}
