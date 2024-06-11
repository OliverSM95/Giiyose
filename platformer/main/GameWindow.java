package main;

import javax.swing.JFrame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

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
        jframe.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {

            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                gamePanel.getGame().windowFocusLost();
            }
        });
    }
}
