package events;

import player.Inventory;
import player.Player;
import ui.MenuFunctions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Random;

public class Hunting extends Event {

    private static Random rand = new Random();

    public Hunting() {


    }


    public static void bisonEncounter() {
        JFrame huntingFrame = new JFrame("Hunting");

        JProgressBar healthBar = new JProgressBar();

        huntingFrame.setSize(600, 600);
        huntingFrame.setResizable(false);
        huntingFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        huntingFrame.setLayout(null);
        huntingFrame.setLocationRelativeTo(null);

        // Custom JPanel with background image
        JPanel huntPanel = new JPanel() {
            private Image backgroundImage = new ImageIcon("src/Images/bisonbackground.jpg").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        huntPanel.setSize(600, 600);
        huntPanel.setLayout(null);


        // Bison Picture
        ImageIcon bisonImage = new ImageIcon("src/Images/bison.png");
        JLabel bisonLabel = new JLabel(bisonImage);
        bisonLabel.setBounds(50, 100, 540, 360);
        huntPanel.add(bisonLabel);
        huntPanel.revalidate();

        //Bison name
        JLabel bossName = new JLabel("Bison");
        bossName.setFont(new Font("Arial", Font.BOLD, 42));
        bossName.setBounds(huntingFrame.getWidth() / 3 + 30, 40, 540, 70);
        huntPanel.add(bossName);


        // bison health bar
        healthBar.setMinimum(0);
        healthBar.setMaximum(200);
        healthBar.setValue(200);
        healthBar.setForeground(Color.GREEN);
        healthBar.setBackground(Color.RED);
        healthBar.setStringPainted(true);
        healthBar.setBounds(0, 0, huntingFrame.getWidth(), 30);
        huntPanel.add(healthBar);


        // attack button
        // create Button
        JButton attackButton = new JButton("Attack");
        attackButton.setBackground(Color.RED);
        attackButton.setBounds(huntingFrame.getWidth() / 3, 500, 200, 60);
        JLabel coinsRemovedLabel = new JLabel();
        // listen to button press
        attackButton.addActionListener(e -> {
            int coinsRemoved = rand.nextInt(1, 6);

            coinsRemovedLabel.setText("The Bison hit you  | -" + coinsRemoved + ": Coins");
            coinsRemovedLabel.setFont(new Font("Arial", Font.BOLD, 15));
            coinsRemovedLabel.setForeground(Color.RED);
            coinsRemovedLabel.setBounds(huntingFrame.getWidth() / 3 + 30, 40, 540, 70);


            //remove boss name & or coins removed message after first button click
            huntPanel.remove(bossName);
            huntPanel.remove(coinsRemovedLabel);
            huntPanel.revalidate();
            huntPanel.repaint();


            //adding attack outcomes (either hit or get attacked)
            // 3:1 chance of hitting to missing
            int attackChances = rand.nextInt(0, 4);
            if (attackChances == 0 || attackChances == 1 || attackChances == 2) {// if hit


                healthBar.setValue(healthBar.getValue() - rand.nextInt(5, 51));// remove health between (5,50)
                System.out.println(healthBar.getValue());
            } else if (attackChances == 3) {
                // if attacked by the bison then remove players coins between 1-5

                Player.removeCoins(coinsRemoved);
                System.out.println("Player Coins: " + Player.getCoins());
                //show coins removed
                huntPanel.add(coinsRemovedLabel);
                // JOptionPane.showMessageDialog(null,coinsRemoved+" Coins Removed");


            }
// if bison health =0 then close hunting frame
            if (healthBar.getValue() <= 0) {
                huntingFrame.dispose();
                JOptionPane.showMessageDialog(null, "You Killed the Bison!");

            }
        });
        // add attack button to panel
        huntPanel.add(attackButton);


        huntingFrame.add(huntPanel);
        huntingFrame.setVisible(true);


        System.out.println(healthBar.getValue());


    }

    public static void wolfEncounter() {

        JFrame huntingFrameWolf = new JFrame("Hunting");

        JProgressBar healthBar = new JProgressBar();

        huntingFrameWolf.setSize(600, 600);
        huntingFrameWolf.setResizable(false);
        huntingFrameWolf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        huntingFrameWolf.setLayout(null);
        huntingFrameWolf.setLocationRelativeTo(null);        // Custom JPanel with background image
        JPanel huntPanel = new JPanel() {
            private Image backgroundImage = new ImageIcon("src/Images/wolfbackground.jpeg").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        huntPanel.setSize(600, 600);
        huntPanel.setLayout(null);


        // Wolf Picture
        ImageIcon WolfImage = new ImageIcon("src/Images/Wolf.png");
        JLabel WolfLabel = new JLabel(WolfImage);
        WolfLabel.setBounds(50, 110, 540, 360);
        huntPanel.add(WolfLabel);
        huntPanel.revalidate();

        //Bison name
        JLabel bossName = new JLabel("Wolf");
        bossName.setFont(new Font("Arial", Font.BOLD, 42));
        bossName.setOpaque(true);
        bossName.setForeground(Color.WHITE);
        bossName.setBackground(Color.DARK_GRAY);
        bossName.setBounds(huntingFrameWolf.getWidth() / 3 + 30, 40, 100, 50);
        huntPanel.add(bossName);


        // bison health bar
        healthBar.setMinimum(0);
        healthBar.setMaximum(100);
        healthBar.setValue(100);
        healthBar.setForeground(Color.GREEN);
        healthBar.setBackground(Color.RED);
        healthBar.setStringPainted(true);
        healthBar.setBounds(0, 0, huntingFrameWolf.getWidth(), 30);
        huntPanel.add(healthBar);


        // attack button
        // create Button
        JButton attackButton = new JButton("Attack");
        attackButton.setBackground(Color.RED);
        attackButton.setBounds(huntingFrameWolf.getWidth() / 3, 500, 200, 60);

        JLabel coinsRemovedLabel = new JLabel();// must initialize to allow previous coins lost label to be removed after every click
        // listen to button press
        attackButton.addActionListener(e -> {

            // coins removed message
            int coinsRemoved = rand.nextInt(1, 4);
            coinsRemovedLabel.setText("The Wolf hit you  | -" + coinsRemoved + ": Coins");
            coinsRemovedLabel.setFont(new Font("Arial", Font.BOLD, 15));
            coinsRemovedLabel.setForeground(Color.RED);
            coinsRemovedLabel.setOpaque(true);// must be set to true to allow background color to be present
            coinsRemovedLabel.setBackground(Color.WHITE);
            coinsRemovedLabel.setBounds(huntingFrameWolf.getWidth() / 3 + 30, 40, 200, 30);


            //remove boss name & or coins removed message after first button click
            huntPanel.remove(bossName);
            huntPanel.remove(coinsRemovedLabel);
            huntPanel.revalidate();
            huntPanel.repaint();


            //adding attack outcomes (either hit or get attacked)
            // 3:1 chance of hitting to missing
            int attackChances = rand.nextInt(0, 3);
            if (attackChances == 0 || attackChances == 1 ) {// if hit


                healthBar.setValue(healthBar.getValue() - rand.nextInt(5, 41));// remove health between (5,40)
                System.out.println(healthBar.getValue());
            } else if (attackChances == 2) {
                // if attacked by the bison then remove players coins between 1-5
                Player.removeCoins(coinsRemoved);
                System.out.println("Player Coins: " + Player.getCoins());
                //show coins removed
                huntPanel.add(coinsRemovedLabel);
                // JOptionPane.showMessageDialog(null,coinsRemoved+" Coins Removed");


            }
            // if bison health =0 then close hunting frame
            if (healthBar.getValue() <= 0) {
                huntingFrameWolf.dispose();
                JOptionPane.showMessageDialog(null, "You Killed the Wolf!");

            }
        });
        // add attack button to panel
        huntPanel.add(attackButton);


        huntingFrameWolf.add(huntPanel);
        huntingFrameWolf.setVisible(true);


        System.out.println(healthBar.getValue());


    }
}


