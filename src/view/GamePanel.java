package view;
import controllers.Game;
import utilities.KeyInputs;
import utilities.MouseInputs;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
    private Game game;
    private ArrayList<InteractBox> interactables;
    private int mouseX, mouseY;
    private int keyLast;

    public GamePanel(Game game) {
    	this(game, 416, 416);
    }

    public GamePanel(Game game, int width, int height) {
    	this.game = game;
    	
        addMouseListener(new MouseInputs(this));
        addMouseMotionListener(new MouseInputs(this));
        addKeyListener(new KeyInputs(this));
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        
        // Setting base values.
        mouseX = -1;
        mouseY = -1;
        keyLast = -1;
    }
    
    // Extending JPanel allows us to use the paintComponent method, which simplifies drawing.
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
    
    // Returns the last released key and resets it.
    public int popKey() {
    	int tempKey = keyLast;
    	
    	keyLast = -1;
    	return tempKey;
    }
    
    public int getKey() {
    	return keyLast;
    }
    
    public int[] getMousePos() {
    	int[] xy = { mouseX, mouseY };
    	return xy;
    }

    public void setKey(int keyCode) {
        keyLast = keyCode;
    }

    public void setMousePos(int x, int y) {
    	mouseX = x;
    	mouseY = y;
    }
}