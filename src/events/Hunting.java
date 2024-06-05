package events;

import javax.swing.*;
import java.awt.*;

public class Hunting extends Event {

protected JFrame huntingFrame;
protected JPanel huntPan;
protected  JProgressBar healthBar;
   protected JLabel  bossName;

public Hunting(){


}


public static void bisonEncounter(){
Bison bison = new Bison();
bison.bisonFight();

}

public void wolfEncounter(){


}

  public  void updateHealth(int health) {
        healthBar.setValue(health);
    }




}
