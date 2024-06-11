package events.entity.Tiles;

import events.GatherGamePanel;
import events.entity.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;

public class tileManager {


    GatherGamePanel gp;
    public tile[] tile;
    final int numTiles =10;// number of unique background sprites

    public int[][] mapTileNumber;



    public tileManager(GatherGamePanel gp) {

        this.gp = gp;

        tile = new tile[numTiles];
        mapTileNumber = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/events/entity/maps/mapTest.txt");
    }

    public void getTileImage(){
            //set tile array to specific images

            // setup grass tile
            setup(0,"/events/entity/backgroundTiles/grassSprite.png",false);
            // water tile
            setup(1,"/events/entity/backgroundTiles/waterSprite.png",true);
            //dirt sprite
            setup(2,"/events/entity/backgroundTiles/dirtSprite.png",false);
            //tree sprite
            setup(3,"/events/entity/backgroundTiles/treeOnGrassSprite.png",true);
            //sand sprite
            setup(4,"/events/entity/backgroundTiles/sandSprite.png",false);



    }

    public void setup(int index, String imagePath,boolean collision){ // render tiles better
        UtilityTool uTool = new UtilityTool();

        try{
            tile[index] = new tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream(imagePath));
            tile[index].image = uTool.scaleImage(tile[index].image,gp.tileSize, gp.tileSize);
            tile[index].collision =collision;


        }catch (IOException e){
            e.printStackTrace();
        }
    }


    public void loadMap(String filePath){

        try{
            InputStream inStream = getClass().getResourceAsStream(filePath);

            BufferedReader br = new BufferedReader(new InputStreamReader(inStream));

            int col=0;
            int row=0;
            while(col<gp.maxWorldCol && row<gp.maxWorldRow){
                String line = br.readLine();// read first line from map data

                while(col<gp.maxWorldCol)
                {
                    String[] numbers = line.split(" ");// scrape numbers from map text (turning strings to int)

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNumber[col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol){
                    col=0;
                    row++;
                }
            }
            br.close();// close buffer reader  (not used past this point)

        }catch (Exception e){

        }
    }



    public void draw(Graphics g2){

        // create while loop to draw images
        int worldCol = 0;
        int worldRow = 0;


        while(worldCol<gp.maxWorldCol && worldRow<gp.maxWorldCol){

            int tileNum = mapTileNumber[worldCol][worldRow];

            //drawing location
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX -gp.player.worldX + gp.player.screenX;
            int screenY = worldY -gp.player.worldY + gp.player.screenY;

             // create a boundary to help loading efficiency  (only load/draw tiles near you)
            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
               worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
               worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
               worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){

                g2.drawImage(tile[tileNum].image,screenX,screenY,null);
            }

            worldCol++;


            if(worldCol==gp.maxWorldCol){
                worldCol=0; // reset colum if it has reached the screen length

                worldRow++;

            }


        }

    }



}
