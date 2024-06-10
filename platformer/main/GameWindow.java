package main;

import javax.swing.JFrame;

public class GameWindow {
    private JFrame jframe;

    public GameWindow(GamePanel gamePanel){
        jframe = new JFrame();


        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Action for when clicked X
        jframe.add(gamePanel);
        jframe.setResizable(false);
        jframe.pack(); // Make window big enough to fit panel
        jframe.setLocationRelativeTo(null); // Center panel
        jframe.setVisible(true); // Required to see panel
    }
}
