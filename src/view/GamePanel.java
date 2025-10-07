package view;
import controllers.Game;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import utilities.KeyInputs;
import utilities.MouseInputs;

public class GamePanel extends JPanel  {
    private Game game;

    public GamePanel() {
        setFocusable(true);
        addMouseListener(new MouseInputs(this));
        addKeyListener(new KeyInputs(this));
    }

    // Extending JPanel allows us to use the paintComponent method, which simplifies drawing.
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public void readKey(KeyEvent e) {
        System.out.println(e.getKeyChar());
    }

    public void readMouse(MouseEvent e) {
        System.out.println("Test reading");
    }
}