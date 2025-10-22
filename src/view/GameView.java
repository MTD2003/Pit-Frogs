package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Insets;

import javax.swing.JFrame;
import utilities.Constants;

public class GameView {
    private JFrame mframe;
    private static final String GAME_NAME = "Pit Frogs";
    
    public GameView() {
        mframe = new JFrame(GAME_NAME);
        mframe.setSize(Constants.SCREEN_SIZE, Constants.SCREEN_SIZE);
        mframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mframe.setLocationRelativeTo(null);
        mframe.setResizable(true);
        mframe.setLayout(new BorderLayout());
        mframe.setVisible(true);
    }
    
    // Returns the screen's smallest dimension.
    // Used for scaling the actual visuals.
    public int getMinSize() {
    	return Math.min(getWidth(), getHeight());
    }
    
    public int getHeight() {
    	int addTop = mframe.getInsets().top;
    	return (mframe.getHeight() - addTop);
    }
    
    public int getWidth() {
    	return mframe.getWidth();
    }

    public void addComponent(Component c) {
        mframe.add(c, BorderLayout.CENTER);
        mframe.pack();
    }
}