package events.entity.Objects;

import events.GatherGamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class superObject {

    BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX,worldY;

    public Rectangle hitBox = new Rectangle(0,0,48,48);
    public int hitBoxDefaultX=0;
    public int hitBoxDefaultY=0;


    public void draw(Graphics2D g2, GatherGamePanel gp) {


        int screenX = worldX -gp.player.worldX + gp.player.screenX;
        int screenY = worldY -gp.player.worldY + gp.player.screenY;

        // create a boundary to help loading efficiency  (only load/draw tiles near you)
        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){

            g2.drawImage(image,screenX,screenY,gp.tileSize,gp.tileSize,null);
        }
    }

}
