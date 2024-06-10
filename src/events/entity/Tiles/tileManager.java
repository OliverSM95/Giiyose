package events.entity.Tiles;

import events.GatherGamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
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
        try{
            //grass tile
            tile[0] = new tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/events/entity/backgroundTiles/grassSprite.png"));

            // water tile
            tile[1] = new tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/events/entity/backgroundTiles/waterSprite.png"));
            tile[1].collision = true;

            //dirt tile
            tile[2] = new tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/events/entity/backgroundTiles/dirtSprite.png"));

            //tree on grass tile
            tile[3] = new tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/events/entity/backgroundTiles/treeOnGrassSprite.png"));
            tile[3].collision = true;

            tile[4] = new tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/events/entity/backgroundTiles/sandSprite.png"));



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

                g2.drawImage(tile[tileNum].image,screenX,screenY,gp.tileSize,gp.tileSize,null);
            }

            worldCol++;


            if(worldCol==gp.maxWorldCol){
                worldCol=0; // reset colum if it has reached the screen length

                worldRow++;

            }


        }

    }



}
