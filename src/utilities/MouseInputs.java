package utilities;

import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputListener;
import view.GamePanel;

public class MouseInputs implements MouseInputListener {
    private GamePanel parent;

    public MouseInputs(GamePanel parent) {
        this.parent = parent;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Register click for gameplay.
        parent.readMouse(e.getX(), e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // Move mouse position.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Modify sprite to show clicking.
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // Modify sprite to show clicking(?)
        mouseMoved(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}