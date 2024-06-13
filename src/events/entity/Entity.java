package events.entity;

import events.GatherGamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Entity {

    GatherGamePanel gp;
    public int worldX,worldY;
    public int speed;

    public BufferedImage up1,up2,down1,down2,right1,right2,left1,left2,idleDown;
    public String direction;

    public int spriteCounter =0;
    public int spriteNum =1;

    public Rectangle hitBox = new Rectangle(0,0,48,48);
    public int hitBoxDefaultX,hitBoxDefaultY;
    public boolean collisionOn = false;

    public Entity(GatherGamePanel gp) {
        this.gp = gp;
    }


    public BufferedImage setup(String imagePath){
        UtilityTool uTool = new UtilityTool();

        BufferedImage scaledImage = null;
        try{
            scaledImage = ImageIO.read(getClass().getResourceAsStream(imagePath));
        }catch (IOException e){
            e.printStackTrace();
        }


        return scaledImage;
    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;
        int screenX = worldX -gp.player.worldX + gp.player.screenX;
        int screenY = worldY -gp.player.worldY + gp.player.screenY;

        // create a boundary to help loading efficiency  (only load/draw tiles near you)
        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){


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
            }

            g2.drawImage(image,screenX,screenY,gp.tileSize,gp.tileSize,null);
        }
    }


}
