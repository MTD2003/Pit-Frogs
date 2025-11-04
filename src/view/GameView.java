package view;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JFrame;

public class GameView {
    private JFrame mframe;
    private static final String GAME_NAME = "Pit Frogs";
    
    public GameView() {
        mframe = new JFrame(GAME_NAME);
        mframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mframe.setResizable(true);
        mframe.setLayout(new BorderLayout());
        mframe.setVisible(true);
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
        mframe.setLocationRelativeTo(null);
    }
}