package events;

import javax.swing.*;
import java.awt.*;

public class Gathering extends Event {

    public static void gatheringGame() {
        // Create frame
        JFrame gatheringFrame = new JFrame();
        gatheringFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gatheringFrame.setResizable(false);
        gatheringFrame.setTitle("Gathering Mini-Game");

        // Set frame to full screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        gatheringFrame.setSize(screenSize.width, screenSize.height);
        gatheringFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Optional, for full-screen effect

        // Create game panel
        GatherGamePanel gamePanel = new GatherGamePanel();
        gamePanel.setPreferredSize(screenSize);
        gatheringFrame.add(gamePanel);

        // Pack and make visible
        gatheringFrame.pack();
        gatheringFrame.setLocationRelativeTo(null);
        gatheringFrame.setVisible(true);

        // Start the game thread
        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
}
