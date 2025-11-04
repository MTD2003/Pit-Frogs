package view;

// Represents the "hitbox" of elements in the game that are interactable through the user interface.
// Could be menu items, grid elements, etc.
// Responds to clicks and keybinds.
public class InteractBox {
	private int x, y, width, height;
	private final int bind; // Represents the keyboard input ID.
	private Interactable parent;
	
	public InteractBox(Interactable parent, int x, int y, int width, int height) {
		this(parent, x, y, width, height, -2);
	}
	
	public InteractBox(Interactable parent, int x, int y, int width, int height, int bind) {
		this.parent = parent;
		this.bind = bind;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	private boolean boundsCheck(int clickX, int clickY) {
		return ((clickX > x && clickX < (x + width)) && (clickY > y && clickY < (y + height)));
	}
	
	public boolean checkHit(int clickX, int clickY) {
		if(boundsCheck(clickX, clickY)) {
			parent.onActivate();
			return true;
		}
		
		return false;
	}
	
	public boolean checkBind(int key) {
		if(key == bind) {
			parent.onActivate();
			return true;
		}
		
		return false;
	}
	
	public void checkHold(int clickX, int clickY, int key, boolean mouseHeld) {
		if(key == bind) {
			parent.onPress();
		} else if(boundsCheck(clickX, clickY)) {
			if(mouseHeld)
				parent.onPress();
			else
				parent.onHover();
		} else {
			parent.onNothing();
		}
	}
	
	public void forceActivate() {
		parent.onActivate();
	}
	
	public void updateScale(int lastScale, int currentScale) {
		x = (x / lastScale) * currentScale;
		y = (y / lastScale) * currentScale;
		width = (width / lastScale) * currentScale;
		height = (height / lastScale) * currentScale;
	}
}