package utilities;

public class InputMirror {
	private int mouseX, mouseY;
    private int keyHeld, keyLast, mouseState;
    
    public static final int KEY_EMPTY = -1;
    public static final int MOUSE_IDLE = -1;
    public static final int MOUSE_HELD = 1;
    public static final int MOUSE_CLICK = 2;
    
    public InputMirror() { }
    
    public int getKey() {
    	return keyLast;
    }
    
    public int getHeld() {
    	return keyHeld;
    }
    
    public int getMouseX() {
    	return mouseX;
    }
    
    public int getMouseY() {
    	return mouseY;
    }
    
    public int getMouseState() {
    	return mouseState;
    }

    public void setKey(int keyCode) {
        keyLast = keyCode;
    }
    
    public void setHeld(int keyCode) {
    	keyHeld = keyCode;
    }

    public void setMousePos(int x, int y) {
    	mouseX = x;
    	mouseY = y;
    }
    
    public void setMouseState(int mouseState) {
    	this.mouseState = mouseState;
    }
    
    public void clearKey() {
    	keyLast = KEY_EMPTY;
    }
    
    // Clears clicks, check to make sure MOUSE_HELD isn't active.
    public void clearClick() {
    	mouseState = MOUSE_IDLE;
    }
}
