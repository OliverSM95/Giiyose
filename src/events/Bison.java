package events;

import javax.swing.*;
import java.awt.*;

public class Bison extends Hunting{


    public Bison(){
super.bossName = new JLabel("Bison");



        // bison health bar
        this.healthBar = new JProgressBar();
        this.healthBar.setMinimum(0);
        this.healthBar.setMaximum(200);
       this.healthBar.setValue(200);



        huntingFrame.add(huntPan);
        huntingFrame.setVisible(true);





        huntingFrame = new JFrame("Hunting Game");
        huntingFrame.setSize(600,600);
        huntingFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);



        healthBar.setForeground(Color.GREEN);
        healthBar.setBackground(Color.RED);
        huntPan.add(healthBar,BorderLayout.NORTH);


        huntPan.setLayout(new BorderLayout());
        huntPan.add(bossName,BorderLayout.CENTER);


    }
    public  void bisonFight(){

        JButton attack = new JButton();
        attack.addActionListener(e -> {
          super.updateHealth(healthBar.getValue()-50);
        });


huntPan.add(attack);
    }





}
