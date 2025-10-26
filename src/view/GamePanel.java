package view;
import utilities.KeyInputs;
import utilities.MouseInputs;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
    private ArrayList<InteractBox> interactables;
    private int mouseX, mouseY;
    private int keyHeld, keyLast, mouseState;
    private Image buffer;
    
    public static final int KEY_EMPTY = -1;
    public static final int MOUSE_IDLE = -1;
    public static final int MOUSE_HELD = 1;
    public static final int MOUSE_CLICK = 2;

    public GamePanel() {
    	this(416, 416);
    }

    public GamePanel(int width, int height) {
    	// Setting base values.
        mouseX = 0;
        mouseY = 0;
        keyLast = KEY_EMPTY;
        mouseState = MOUSE_IDLE;
    	
        addMouseListener(new MouseInputs(this));
        addMouseMotionListener(new MouseInputs(this));
        addKeyListener(new KeyInputs(this));
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
    }
    
    // Extending JPanel allows us to use the paintComponent method, which simplifies drawing.
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(buffer, 0, 0, null);
    }
    
    // Returns the last released key and resets it.
    public int popKey() {
    	int tempKey = keyLast;
    	
    	keyLast = -1;
    	return tempKey;
    }
    
    public int popMouseState() {
    	int tempMouse = mouseState;
    	
    	mouseState = MOUSE_IDLE;
    	return tempMouse;
    }
    
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
    
    public Image getImage() {
    	buffer = this.createImage(getWidth(), getHeight());
    	return buffer;
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
    	this.createImage(getWidth(), getHeight());
    }
    
    public void setMouseClick() {
    	mouseState = MOUSE_CLICK;
    }
    
    public void setMouseHeld() {
    	mouseState = MOUSE_HELD;
    }
}