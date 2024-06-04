package ui;

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

}
