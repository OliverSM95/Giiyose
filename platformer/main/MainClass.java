/*
===========================================
 Giiyose
 ---------------
 Created by Oliver Simm & Efe Bolukbasi
 Grade 12 ICS4U1 - Lawrence Park Collegiate Institute
 Mrs. Zheng
===========================================
 */


package main;


import utilz.LoadSave;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MainClass {

    // Run the main game
    public static void main(String[] args){

        mainMenu();

    }

    // Start with Giiyose main menu
    public static void mainMenu() {
        JFrame frame = new JFrame();
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel() {

            // Load Giiyose logo image
            BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.GIIYOSE_LOGO);

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
            }

        };
        panel.setSize(800, 800);
        panel.setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        JButton startButton = new JButton("Start");

        startButton.setBounds(200, 670, 160, 80);
        startButton.setFont(new Font("Arial", Font.BOLD, 30));

        JButton creditsButton = new JButton("Credits");// credits button
        creditsButton.setFont(new Font("Arial", Font.BOLD, 30));

        creditsButton.setBounds(400, 670, 160, 80);

        // action listeners for buttons
        startButton.addActionListener(e -> {
            new Game();
            frame.dispose();
        });

        creditsButton.addActionListener(e -> {// credits button action listener
            creditsMenu();
            frame.dispose();

        });


        panel.add(creditsButton);
        panel.add(startButton);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
    }


    public static void creditsMenu(){

        // Create jframe

        JFrame credFrame = new JFrame();
        credFrame.setSize(400,400);
        credFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel credPanel = new JPanel();
        credPanel.setLayout(null);
        credPanel.setSize(400,400);

        JLabel Title = new JLabel("Giiyose Credits");// thanks for playing message
        Title.setFont(new Font("Arial", Font.BOLD, 40)); // Set font size to 40
        Title.setBounds(40,-110,400,300);


        JLabel creditMembers1 = new JLabel("Lead Software Designer: Oliver Simm");

        creditMembers1.setFont(new Font("Arial",Font.PLAIN,18));

        creditMembers1.setBounds(20,-120,400,400);
        credPanel.add(creditMembers1);

        JLabel creditMembers2 = new JLabel("Lead Development Engineer: Efe Bolukbasi ");

        creditMembers2.setFont(new Font("Arial",Font.PLAIN,18));

        creditMembers2.setBounds(20,-100,400,400);
        credPanel.add(creditMembers2);

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial",Font.BOLD,40));
        backButton.setBounds(110,130,150, 150);
        backButton.addActionListener(e -> {
            mainMenu();
            credFrame.dispose();
        });

        // Adding the option to go back
        credPanel.add(backButton);


        credPanel.add(Title);
        credFrame.add(credPanel);
        credFrame.setVisible(true);
    }

}
