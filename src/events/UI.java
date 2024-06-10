package events;

import player.Player;

import java.awt.*;

public class UI {

    GatherGamePanel gp;
    Font arial_40;

    Player player;

    //BufferedImage coinImage;
    public boolean messageOn = false;
    public String message="";

    public UI(GatherGamePanel gp,Player player){
        this.gp = gp;

        this.player = player;

        arial_40 = new Font("Arial",Font.BOLD,40);
    }

    public void showMessage(String text){
        message = text;
        messageOn = true;

    }


    public void draw(Graphics2D g2){
        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);
        g2.drawString("Coins: "+  player.getCoins(),40,40);
    }
}
