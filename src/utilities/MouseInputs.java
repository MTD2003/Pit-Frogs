package utilities;

import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputListener;

public class MouseInputs implements MouseInputListener {
    private InputMirror inputs;

    public MouseInputs(InputMirror inputs) {
        this.inputs = inputs;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        inputs.setMouseState(InputMirror.MOUSE_CLICK);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    	inputs.setMousePos(e.getX(), e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {
    	inputs.setMouseState(InputMirror.MOUSE_HELD);
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