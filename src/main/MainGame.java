/*
===========================================
 Giiyose
 ---------------
 Created by Oliver Simm & Efe Bolukbasi
 Grade 12 ICS4U - Lawrence Park
 Mrs.Zheng
===========================================
 */


package main;

import events.Event;
import events.GatherGamePanel;
import events.Gathering;
import events.Hunting;
import player.Player;
import village.Village;
import ui.MenuFunctions;
import ui.GameBoardPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class MainGame extends JFrame {
    private Player player;
    private Village village;
    private GameBoardPanel gameBoardPanel;

    public MainGame() {
        // Initialize player and village
        player = new Player("PlayerName");
        village = new Village();

        // Get screen size and set JFrame size accordingly
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        setSize(width, height);

        // Set up JFrame properties
        setTitle("Giiyose Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create game board panel
        gameBoardPanel = new GameBoardPanel();
        add(gameBoardPanel, BorderLayout.CENTER);

        // Create control panel
        JPanel controlPanel = setupControlPanel();
        add(controlPanel, BorderLayout.EAST);

        // Create and start the game loop
        startGameLoop();

        // Show the frame
        setVisible(true);
    }

    private JPanel setupControlPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add player name labela
        JLabel nameLabel = new JLabel("Player: " + player.getName());
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(nameLabel, gbc);

        // Add move button
        JButton moveButton = new JButton("Move Locations");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(moveButton, gbc);

        // Add minigame button
        JButton minigameButton = new JButton("Play Minigame");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(minigameButton, gbc);

        return panel;
    }

    private void startGameLoop() {
        int delay = 16; // Approx. 60 FPS (1000ms / 60 ≈ 16ms)
        Timer timer = new Timer(delay, e -> {
            updateGame();
            gameBoardPanel.repaint();
        });
        timer.start();
    }

    private void updateGame() {
        // Logic to update game state (e.g., update player position, check game events)
    }

    public static void main(String[] args) {

        //       MenuFunctions.creditsMenu();
//MenuFunctions.landAcknowledgementMenu();
        //     SwingUtilities.invokeLater(MainGame::new);
        /*
        Random rand = new Random();
        int randomVal = rand.nextInt(0,2);
        if(randomVal ==0){
            Hunting.bisonEncounter();
        }else {
            Hunting.wolfEncounter();
        }

         */
        Gathering.gatheringGame();
        //Hunting.bisonEncounter();


    }
}