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
    public void keyPressed(KeyEvent e) {
        parent.readKey(e.getKeyCode());
        // Send inputs to GamePanel function.
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}