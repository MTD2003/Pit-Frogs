package view;
import controllers.Game;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import utilities.KeyInputs;
import utilities.MouseInputs;

public class GamePanel extends JPanel {
    private Game game;

    public GamePanel() {
        buildPanel(new Dimension(400, 400));
    }

    public GamePanel(int width, int height) {
        buildPanel(new Dimension(width, height));
    }

    private void buildPanel(Dimension d) {
        addMouseListener(new MouseInputs(this));
        addMouseMotionListener(new MouseInputs(this));
        addKeyListener(new KeyInputs(this));
        setPreferredSize(d);
        setFocusable(true);
    }

    // Extending JPanel allows us to use the paintComponent method, which simplifies drawing.
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public void readKey(int keyCode) {
        System.out.println(keyCode);
    }

    public void readMouse(int x, int y) {
        System.out.println("Test reading");
    }
}