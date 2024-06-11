package events;

import events.entity.Objects.coinObj;
import player.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {

    GatherGamePanel gp;
    Font arial_40;

    BufferedImage coinImage;

    Player player;

    //BufferedImage coinImage;
    public boolean messageOn = false;
    public String message="";

    int messageCounter =0;

    public UI(GatherGamePanel gp,Player player){
        this.gp = gp;

        this.player = player;

        arial_40 = new Font("Arial",Font.BOLD,40);

        coinObj coin =  new coinObj();
        coinImage = coin.image;
    }

    public void showMessage(String text){
        message = text;
        messageOn = true;

    }


    public void draw(Graphics2D g2){
        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);
        g2.drawImage(coinImage, gp.tileSize/2,gp.tileSize/2,gp.tileSize,gp.tileSize,null);
        g2.drawString("x "+  player.getCoins(),74,50);


        if(messageOn){
            g2.drawString(message,gp.tileSize/2,gp.tileSize*5);
            messageCounter++;
            if(messageCounter >120){
                messageCounter=0;
                messageOn = false;
            }
        }
    }
}
