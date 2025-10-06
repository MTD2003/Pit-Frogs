package view;
import java.awt.Graphics;
import javax.swing.JPanel; 

public class GamePanel extends JPanel  {
    public GamePanel() {

    }
    // Extending JPanel allows us to use the paintComponent method, which simplifies drawing.
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}