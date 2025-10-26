package utilities;
import view.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInputs implements KeyListener {
    private GamePanel parent;
    
    public KeyInputs(GamePanel parent) {
        this.parent = parent;
    }

    @Override
    public void keyPressed(KeyEvent e) {
    	parent.setHeld(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
    	parent.setKey(e.getKeyCode());
    	parent.setHeld(-1);
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}