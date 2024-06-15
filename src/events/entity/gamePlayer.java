package events.entity;

import events.GatherGamePanel;
import events.Hunting;
import events.KeyHandler;
import player.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class gamePlayer extends Entity{



    KeyHandler keyH;

    Player mainPlayer;

    Hunting huntingGames;

    Random rn = new Random();

    // values use to centre payer in middle of camera
    public final int screenX;
    public final int screenY;

    public gamePlayer( GatherGamePanel gp, KeyHandler keyH, Player player, Hunting hg) {

        super(gp); // calling entity super classes constructor
        this.huntingGames = hg;

        this.keyH = keyH;
        this.mainPlayer = player;




        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        hitBox = new Rectangle(8,16,32,32); // setting player hit box size
        hitBoxDefaultX = 8;
        hitBoxDefaultY = 16;


        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        worldX = gp.tileSize *23;
        worldY = gp.tileSize *21;
        speed = 4;
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
            }else if(keyH.downPressed){
                direction = "down";
            }else if(keyH.leftPressed){
                direction = "left";
            }else if(keyH.rightPressed){
                direction = "right";
            }

            // add collisions (check tile collision)
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //check object collision
            int  objIndex =  gp.cChecker.checkObject(this,true);
            objectInteraction(objIndex);

            //if collision is false, player can move
            if(collisionOn ==false){
                switch(direction){
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                          break;
                    case "right":
                        worldX += speed;
                          break;
                }
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

    public void objectInteraction(int i){

        if(i != 999){// if 999 not object touch

       // gp.object[i] = null; // deletes object when touched

            String objName = gp.object[gp.currentMap][i].name;
            switch(objName){
                case "Coin":
                    gp.playSoundEffect(1);
                    gp.object[gp.currentMap][i] = null;
                    int coinsGained = rn.nextInt(1,7);
                    mainPlayer.addCoins(coinsGained);
                    gp.ui.showMessage("You gained: "+coinsGained+" coins");
                    //gp.currentMap =1;
                    break;

                case "Bison":
                    gp.object[gp.currentMap][i] = null;

                    Hunting bH = new Hunting(mainPlayer);
                    bH.bisonEncounter();

                   // huntingGames.bisonEncounter();
                    speed = 4;
                    break;
                case "goldOre":
                    gp.object[gp.currentMap][i] = null;
                    mainPlayer.setGold(2);
                    gp.ui.showMessage("You gained: "+2+" Gold");
                    break;
                case "wolf":
                    gp.object[gp.currentMap][i] = null;
                    Hunting wolf = new Hunting(mainPlayer);
                    wolf.wolfEncounter();
                    break;
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
        }

            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

    }

}
