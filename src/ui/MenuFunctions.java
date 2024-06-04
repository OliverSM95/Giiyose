package ui;

import main.MainGame;

import javax.swing.*;
import java.awt.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;



public class MenuFunctions {




    public static void landAcknowledgementMenu(){

        JFrame frame = new JFrame();
        frame.setSize(400,400);

        JPanel panel = new JPanel();
        panel.setSize(300,400);
        JTextArea acknowledgements = new JTextArea("We acknowledge that we are on the traditional territory"+" of many nations including the Mississaugas of the Credit,"+"the Anishnabeg, the Chippewa, the Haudenosaunee and the Wendat peoples"+" and is now home to many diverse First Nations, Inuit and Métis peoples.");
        acknowledgements.setSize(300,160);
        acknowledgements.setBackground(Color.orange);


        acknowledgements.setLineWrap(true);// make words stay in panel
        acknowledgements.setWrapStyleWord(true);
        acknowledgements.setEditable(false); // Make it read-only if you don't want users to edit the text

        JScrollPane scrollPane = new JScrollPane(acknowledgements);

        panel.add(scrollPane);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);


    }


    public static void mainMenu(){


        JFrame frame = new JFrame();
        frame.setSize(400,400);

        JPanel panel = new JPanel();
        panel.setSize(400,400);
    panel.setLayout(null);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        JButton startButton = new JButton("Start");

        startButton.setBounds(20,10,85, 30);


        JButton creditsButton = new JButton("Credits");// credits button

        creditsButton.setBounds(150,10,85, 30);





    startButton.addActionListener(e -> {

        SwingUtilities.invokeLater(MainGame::new);
    });

    creditsButton.addActionListener(e -> {
        creditsMenu();
    });



        panel.add(startButton);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);

    }

    public static void creditsMenu(){

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

        creditMembers1.setFont(new Font("Arial",Font.PLAIN,10));

        creditMembers1.setBounds(20,-10,400,400);
        credPanel.add(creditMembers1);

        JLabel creditMembers2 = new JLabel("Lead Development Engineer: Efe Bolukbasi ");

        creditMembers2.setFont(new Font("Arial",Font.PLAIN,10));

        creditMembers2.setBounds(20,10,400,400);
        credPanel.add(creditMembers2);



        credPanel.add(Title);
        credFrame.add(credPanel);
        credFrame.setVisible(true);
    }



}
