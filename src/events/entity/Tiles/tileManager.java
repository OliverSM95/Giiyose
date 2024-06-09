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
    tile[] tile;
    final int numTiles =10;

    int[][] mapTileNumber;



    public tileManager(GatherGamePanel gp) {

        this.gp = gp;

        tile = new tile[numTiles];
        mapTileNumber = new int[gp.maxScreenColum][gp.maxScreenRow];

        getTileImage();
        loadMap();
    }

    public void getTileImage(){

        try{
            //set tile array to specific images
            tile[0] = new tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/events/entity/backgroundTiles/grassSprite.png"));

            tile[1] = new tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/events/entity/backgroundTiles/waterSprite.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(){

        try{
            InputStream inStream = getClass().getResourceAsStream("/events/entity/maps/mapTest.txt");

            BufferedReader br = new BufferedReader(new InputStreamReader(inStream));

            int col=0;
            int row=0;
            while(col<gp.maxScreenColum && row<gp.maxScreenRow){
                String line = br.readLine();// read first line from map data

                while(col<gp.maxScreenColum)
                {
                    String[] numbers = line.split(" ");// scrape numbers from map text (turning strings to int)

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNumber[col][row] = num;
                    col++;
                }
                if(col == gp.maxScreenColum){
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
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col<gp.maxScreenColum && row<gp.maxScreenRow){

            int tileNum = mapTileNumber[col][row];
            System.out.println(tileNum);

            g2.drawImage(tile[tileNum].image,x,y,gp.tileSize,gp.tileSize,null);
            col++;
            x += gp.tileSize;

            if(col==gp.maxScreenColum){
                col=0; // reset colum if it has reached the screen length
                x=0;
                row++;
                y+=gp.tileSize;
            }


        }

    }



}
