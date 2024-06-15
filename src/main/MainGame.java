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
import java.awt.event.ActionListener;
import java.util.Random;

import static ui.MenuFunctions.mainGameInstance;
import static ui.MenuFunctions.mainMenu;

public class MainGame extends JFrame {
    public Player player;
    private Village village;
    private GameBoardPanel gameBoardPanel;
/*
    public MainGame(Player pl) {
        // Initialize player and village
        this.player = pl;
        village = new Village();

        // Get screen size and set JFrame size accordingly
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        setSize(width, height);

        // Set up JFrame properties
        setTitle("Giiyose");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create game board panel
        gameBoardPanel = new GameBoardPanel(player);

        JButton enterWilds = new JButton("Enter Wilds");
        enterWilds.addActionListener(e -> {
           Gathering.gatheringGame(mainMapMenu);

        });
        enterWilds.setBounds(width/2,height,200,0);
        enterWilds.setBackground(Color.RED);
        enterWilds.setForeground(Color.YELLOW);

        gameBoardPanel.add(enterWilds);


        add(gameBoardPanel, BorderLayout.CENTER);// jframe add panel


        // Create control panel
        JPanel controlPanel = setupControlPanel();
        add(controlPanel, BorderLayout.EAST);

        // Create and start the game loop
        startGameLoop();

        // Show the frame
        setVisible(true);
    }

 */



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
        player.getLocation();
        System.out.println(player.getLocation());
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
        //Gathering.gatheringGame();
        //Hunting.bisonEncounter();


        mainMenu();


    }
}