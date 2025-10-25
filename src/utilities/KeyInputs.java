package utilities;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import view.GamePanel;

public class KeyInputs implements KeyListener {
    private GamePanel parent;
    
    public KeyInputs(GamePanel parent) {
        this.parent = parent;
    }

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
    	parent.setKey(e.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}