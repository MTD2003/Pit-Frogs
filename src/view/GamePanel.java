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
    }

    private void buildPanel(Dimension d) {
        
    }

    // Extending JPanel allows us to use the paintComponent method, which simplifies drawing.
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public void readKey(int keyCode) {
        System.out.println(keyCode);
    }

    public void readMouse(int x, int y) {
        System.out.println("X: " + x + " Y: " + y);
    }
}