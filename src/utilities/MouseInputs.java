package utilities;
import view.GamePanel;

import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputListener;

public class MouseInputs implements MouseInputListener {
    private GamePanel parent;

    public MouseInputs(GamePanel parent) {
        this.parent = parent;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        parent.setMousePos(e.getX(), e.getY());
        parent.setMouseState(GamePanel.MOUSE_CLICK);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    	parent.setMousePos(e.getX(), e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        parent.setMouseState(GamePanel.MOUSE_HELD);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mousePressed(e);
        mouseMoved(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}