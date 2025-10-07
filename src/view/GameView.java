package view;

import java.awt.Component;
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
        mframe.setVisible(true);
    }

    public void addComponent(Component c) {
        mframe.add(c);
    }
}