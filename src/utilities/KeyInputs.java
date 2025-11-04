package utilities;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInputs implements KeyListener {
	private InputMirror inputs;
    
    public KeyInputs(InputMirror inputs) {
        this.inputs = inputs;
    }

    @Override
    public void keyPressed(KeyEvent e) {
    	inputs.setHeld(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
    	inputs.setKey(e.getKeyCode());
    	if(inputs.getHeld() == e.getKeyCode())
    		inputs.setHeld(InputMirror.KEY_EMPTY); // Resets heldKey if it's the same as released.
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}