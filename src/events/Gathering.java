package events;

import javax.swing.*;

public class Gathering extends Event {

    public static void gatheringGame(){

        JFrame gatheringFrame = new JFrame();
        gatheringFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gatheringFrame.setResizable(false);
        gatheringFrame.setTitle("Gathering Mini-Game");
        gatheringFrame.setLocationRelativeTo(null);
        gatheringFrame.setVisible(true);
    }

}
