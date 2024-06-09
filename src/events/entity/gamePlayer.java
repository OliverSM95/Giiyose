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
        getPlayerImage();
    }
    public void setDefaultValues(){
        x=100;
        y=100;
        speed=4;
        direction = "down";
    }


    public void getPlayerImage(){

        try {
            // load walking animation images

            up1 = ImageIO.read(getClass().getResourceAsStream("/events/entity/BoImages/BoSpriteUp2.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/events/entity/BoImages/BoSpriteUp3.png"));

            down1 = ImageIO.read(getClass().getResourceAsStream("/events/entity/BoImages/BoSpriteDown2.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/events/entity/BoImages/BoSpriteDown3.png"));

            left1 = ImageIO.read(getClass().getResourceAsStream("/events/entity/BoImages/BoSpriteLeft2.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/events/entity/BoImages/BoSpriteLeft3.png"));

            right1 = ImageIO.read(getClass().getResourceAsStream("/events/entity/BoImages/BoSpriteRight2Fixed.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/events/entity/BoImages/BoSpriteRight3.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void update(){

        if(keyH.upPressed ==true || keyH.downPressed ==true || keyH.leftPressed ==true || keyH.rightPressed ==true){
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

            spriteCounter++;
            if(spriteCounter>10){
                if(spriteNum==1){
                    spriteNum=2;
                }else if(spriteNum==2){
                    spriteNum=1;
                }
                spriteCounter=0;
            }

        }





    }

    public void draw(Graphics2D g2){
        //g2.setColor(Color.WHITE);
       // g2.fillRect(x, y, gp.tileSize, gp.tileSize);



        BufferedImage image = null;
         switch (direction) {
            case "up":
                if(spriteNum ==1){
                    image = up1;
                }else if(spriteNum ==2){
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum ==1){
                    image = down1;
                }else if(spriteNum ==2){
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum ==1){
                    image = left1;
                }else if(spriteNum ==2){
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum ==1){
                    image = right1;
                }else if(spriteNum ==2){
                    image = right2;
                }
                break;


        };

            g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);

    }

}
