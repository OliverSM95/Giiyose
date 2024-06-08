package events.entity;

import events.GatherGamePanel;
import events.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

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
        direction = "Down";
    }


    public void getPlayerImage(){

        try {
            System.out.println(getClass().getResource("/events/entity/BoImages/BoSpriteUp2.png"));
            idleDown = ImageIO.read(getClass().getResource("/events/entity/BoImages/BoSpriteDown1.png"));

            up1 = ImageIO.read(getClass().getResourceAsStream("/events/entity/BoImages/BoSpriteUp2.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/events/entity/BoImages/BoSpriteUp3.png"));

            down1 = ImageIO.read(getClass().getResourceAsStream("/events/entity/BoImages/BoSpriteDown2.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/events/entity/BoImages/BoSpriteDown3.png"));

            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/events/entity/images/BoSpriteLeft2.png")));
            left2 = ImageIO.read(getClass().getResourceAsStream("/events/entity/BoImages/BoSpriteLeft3.png"));

            right1 = ImageIO.read(getClass().getResourceAsStream("/events/entity/BoImages/BoSpriteRight2.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/events/entity/BoImages/BoSpriteRight3.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void update(){
        if(keyH.upPressed){
            direction = "up";
            y -= speed;

        }else if(keyH.downPressed){
            direction = "down";
            y += speed;
        }else if(keyH.leftPressed){
            direction = "left";
            x -= speed;
        }else if(keyH.rightPressed){
            direction = "right";
            x += speed;
        }
    }

    public void draw(Graphics2D g2){
        //g2.setColor(Color.WHITE);
       // g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = switch (direction) {
            case "up" -> up1;
            case "down" -> down1;
            case "left" -> left1;
            case "right" -> right1;
            default -> idleDown;
        };
        if (image != null) {
            g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
        } else {
            System.out.println("Image is null for direction: " + direction);
        }
    }

}
