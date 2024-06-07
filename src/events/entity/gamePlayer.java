package events.entity;

import events.GatherGamePanel;
import events.KeyHandler;

import java.awt.*;

public class gamePlayer extends Entity{


    GatherGamePanel gp;
    KeyHandler keyH;

    public gamePlayer( GatherGamePanel gp, KeyHandler keyH ) {

        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
    }
    public void setDefaultValues(){
        x=100;
        y=100;
        speed=4;
    }

    public void update(){
        if(keyH.upPressed){
            y -= speed;

        }else if(keyH.downPressed){
            y += speed;
        }else if(keyH.leftPressed){
            x -= speed;
        }else if(keyH.rightPressed){
            x += speed;
        }
    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.WHITE);
        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
    }

}
