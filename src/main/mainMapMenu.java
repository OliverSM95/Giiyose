package main;

import events.Gathering;
import player.Player;
import ui.GameBoardPanel;
import village.Village;

import javax.swing.*;
import java.awt.*;



public class mainMapMenu extends JFrame {

    mainMapMenu mainGameInstance;
    Player player;
    public GameBoardPanel gameBoardPanel;
    public mainMapMenu(Player pl){

        // Initialize player and village
        this.player = pl;


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
            Gathering.gatheringGame(this);
            this.dispose();
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

        // Show the frame
        setVisible(true);
    }


    public JPanel setupControlPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add player name label
        JLabel nameLabel = new JLabel("Player: " + player.getName());
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(nameLabel, gbc);

        // Add move button
        JButton moveButton = new JButton("Move Locations");
        gbc.gridx = 0;
        gbc.gridy = 1;

        moveButton.addActionListener(e -> { // switch case for selecting user location

            switch (player.getLocation()){
                case "Ontario":
                    String[] locationOptionsOntario = {"Saskatchewan","Quebec"};
                    int selectedOptionOntario = JOptionPane.showOptionDialog(null, "Choose Location: ", "Confirm Action", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, locationOptionsOntario, 3);
                    if (selectedOptionOntario ==0) {
                        player.setLocation("Saskatchewan");
                    } else if (selectedOptionOntario == 1) {
                        player.setLocation("Quebec");
                    } else {
                        player.setLocation(player.getLocation()); // if menu closed stay in the same location
                    }

                    break;
                case "Quebec":
                    String[] locationOptionsQuebec = {"Ontario","Newfoundland"};
                    int selectedOptionQuebec = JOptionPane.showOptionDialog(null, "Choose Location: ", "Confirm Action", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, locationOptionsQuebec, 3);
                    if (selectedOptionQuebec ==0) {
                        player.setLocation("Ontario");
                    } else if (selectedOptionQuebec == 1) {
                        player.setLocation("Newfoundland");
                    } else {
                        player.setLocation(player.getLocation()); // if menu closed stay in the same location
                    }
                    break;
                case "Newfoundland":
                    String[] locationOptionsNewfoundLand = {"Quebec"};
                    int selectedOptionNewfoundLand = JOptionPane.showOptionDialog(null, "Choose Location: ", "Confirm Action", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, locationOptionsNewfoundLand, 3);
                    if (selectedOptionNewfoundLand ==0) {
                        player.setLocation("Quebec");
                    } else {
                        player.setLocation(player.getLocation()); // if menu closed stay in the same location
                    }
                    break;
                case "BC":
                    String[] locationOptionsBC = {"Yukon","Alberta"};
                    int selectedOptionBC = JOptionPane.showOptionDialog(null, "Choose Location: ", "Confirm Action", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, locationOptionsBC, 3);
                    if (selectedOptionBC ==0) {
                        //==============================if(player has wolf coat allow them to move to yukon else no ====================================

                        player.setLocation("Yukon");
                    } else if (selectedOptionBC == 1) {
                        player.setLocation("Alberta");
                    } else {
                        player.setLocation(player.getLocation()); // if menu closed stay in the same location
                    }
                    break;
                case "Alberta":
                    String[] locationOptionsAlberta = {"BC","Saskatchewan"};
                    int selectedOptionAlberta = JOptionPane.showOptionDialog(null, "Choose Location: ", "Confirm Action", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, locationOptionsAlberta, 3);
                    if (selectedOptionAlberta ==0) {
                        player.setLocation("BC");
                    } else if (selectedOptionAlberta == 1) {
                        player.setLocation("Saskatchewan");
                    } else {
                        player.setLocation(player.getLocation()); // if menu closed stay in the same location
                    }
                    break;
                case "Saskatchewan":
                    String[] locationOptionsSaskatchewan = {"Alberta","Ontario","Nunavut"};
                    int selectedOptionSaskatchewan = JOptionPane.showOptionDialog(null, "Choose Location: ", "Confirm Action", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, locationOptionsSaskatchewan, 3);
                    if (selectedOptionSaskatchewan ==0) {
                        player.setLocation("Alberta");
                    } else if (selectedOptionSaskatchewan == 1) {
                        player.setLocation("Ontario");
                    }else if(selectedOptionSaskatchewan == 2){
                        //==============================if(player has wolf coat allow them to move to Nunavut else no ====================================
                        player.setLocation("Nunavut");
                    } else {
                        player.setLocation(player.getLocation()); // if menu closed stay in the same location
                    }

                    break;
                case "Nunavut":
                    String[] locationOptionsNunavut = {"Yukon","Saskatchewan"};
                    int selectedOptionNunavut = JOptionPane.showOptionDialog(null, "Choose Location: ", "Confirm Action", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, locationOptionsNunavut, 3);
                    if (selectedOptionNunavut ==0) {
                        player.setLocation("Yukon");
                    } else if (selectedOptionNunavut == 1) {
                        player.setLocation("Saskatchewan");

                    } else {
                        player.setLocation(player.getLocation()); // if menu closed stay in the same location
                    }
                    break;
                case "Yukon":
                    String[] locationOptionsYukon = {"BC","Nunavut"};
                    int selectedOptionYukon = JOptionPane.showOptionDialog(null, "Choose Location: ", "Confirm Action", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, locationOptionsYukon, 3);
                    if (selectedOptionYukon ==0) {
                        player.setLocation("BC");
                    } else if (selectedOptionYukon == 1) {
                        player.setLocation("Nunavut");
                    } else {
                        player.setLocation(player.getLocation()); // if menu closed stay in the same location
                    }
                    break;



            }
        });
        panel.add(moveButton, gbc);


        return panel;
    }



}
