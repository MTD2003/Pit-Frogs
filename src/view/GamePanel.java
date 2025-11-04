package view;

import controllers.Game;
import utilities.InputMirror;
import utilities.KeyInputs;
import utilities.MouseInputs;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
    private Image buffer;
    private InputMirror inputs;

    public GamePanel() {
    	this(Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);
    }

    public GamePanel(int width, int height) {
    	inputs = new InputMirror();
    	
        addMouseListener(new MouseInputs(inputs));
        addMouseMotionListener(new MouseInputs(inputs));
        addKeyListener(new KeyInputs(inputs));
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
    }
    
    // Extending JPanel allows us to use the paintComponent method, which simplifies drawing.
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(buffer, 0, 0, null);
    }
    
    public Image getImage() {
    	buffer = this.createImage(getWidth(), getHeight());
    	return buffer;
    }
    
    public InputMirror getInput() {
    	return inputs;
    }
}