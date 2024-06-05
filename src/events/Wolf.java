package events;

import javax.swing.*;
import java.awt.*;

public class Wolf extends Hunting {



    public Wolf(){
        // wolf frame
        super.bossName = new JLabel("Wolf");





        // wolf health bar
        this.healthBar = new JProgressBar();
        healthBar.setMinimum(0);
        healthBar.setMaximum(100);
        healthBar.setValue(100);
        healthBar.setForeground(Color.GREEN);
        healthBar.setBackground(Color.RED);
        healthBar.setForeground(Color.GREEN);
        healthBar.setForeground(Color.GREEN);
        healthBar.setBackground(Color.RED);
        huntPan.add(healthBar,BorderLayout.NORTH);



        huntingFrame.add(huntPan);
        huntingFrame.setVisible(true);

    }
    public static void wolfFight(){




    }



}
