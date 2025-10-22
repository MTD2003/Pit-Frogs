package view;

// Represents the "hitbox" of elements in the game that are interactable through the user interface.
// Could be menu items, grid elements, etc.
// Responds to clicks and userbinds.
public class InteractBox {
	private int x, y, width, height;
	private final int bind; // Represents the keyboard input ID.
	private Interactable parent;
	
	public InteractBox(Interactable parent, int x, int y, int width, int height) {
		this(parent, x, y, width, height, -1);
	}
	
	public InteractBox(Interactable parent, int x, int y, int width, int height, int bind) {
		this.parent = parent;
		this.bind = bind;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void checkHit(int clickX, int clickY) {
		if((clickX > x && clickX < (x + width)) && (clickY > y && clickY < (y + height))) {
			System.out.println("IN BOUNDS");
			// 
		}
	}
	
	public void checkBind(int key) {
		if(key == bind) {
			System.out.println("KEY CLACKED");
		}
	}
}