package events.entity;

import events.GatherGamePanel;
import events.entity.Objects.*;

public class AssetSetter {


    GatherGamePanel gp;


    /*
    Class used to create new game objects
     */


    public AssetSetter(GatherGamePanel gp) {
        this.gp = gp;
    }

    public void setObject(){
        int mapNum =0;


        gp.object[mapNum][0] = new objBison();
        gp.object[mapNum][0].worldX = 13 * gp.tileSize;
        gp.object[mapNum][0].worldY = 23 * gp.tileSize;

        gp.object[mapNum][1] = new coinObj();
        gp.object[mapNum][1].worldX = 30 * gp.tileSize;
        gp.object[mapNum][1].worldY = 20 * gp.tileSize;

        // add coin object to map
        gp.object[mapNum][2] = new coinObj();
        gp.object[mapNum][2].worldX = 10 * gp.tileSize;
        gp.object[mapNum][2].worldY = 20 * gp.tileSize;

        gp.object[mapNum][7] = new canoeObj();
        gp.object[mapNum][7].worldX = 44*gp.tileSize;
        gp.object[mapNum][7].worldY = 33*gp.tileSize;

        //MAP 1
        mapNum =1;
        gp.object[mapNum][3] = new goldOreObject();
        gp.object[mapNum][3].worldX = 30*gp.tileSize;
        gp.object[mapNum][3].worldY = 30*gp.tileSize;

        gp.object[mapNum][4] = new wolfObject();
        gp.object[mapNum][4].worldX = 23*gp.tileSize;
        gp.object[mapNum][4].worldY = 23*gp.tileSize;

        gp.object[mapNum][5] = new dungeonObj();
        gp.object[mapNum][5].worldX = 26*gp.tileSize;
        gp.object[mapNum][5].worldY = 23*gp.tileSize;

        gp.object[mapNum][6] = new canoeObj();
        gp.object[mapNum][6].worldX = 38*gp.tileSize;
        gp.object[mapNum][6].worldY = 23*gp.tileSize;

        gp.object[mapNum][18] = new dungeonObj();
        gp.object[mapNum][18].worldX = 46*gp.tileSize;
        gp.object[mapNum][18].worldY = 23*gp.tileSize;

        gp.object[mapNum][19] = new dungeonObj();
        gp.object[mapNum][19].worldX = 10*gp.tileSize;
        gp.object[mapNum][19].worldY = 13*gp.tileSize;

        gp.object[mapNum][20] = new dungeonObj();
        gp.object[mapNum][20].worldX = 15*gp.tileSize;
        gp.object[mapNum][20].worldY = 43*gp.tileSize;

        gp.object[mapNum][21] = new dungeonObj();
        gp.object[mapNum][21].worldX = 23*gp.tileSize;
        gp.object[mapNum][21].worldY = 7*gp.tileSize;

        gp.object[mapNum][23] = new dungeonObj();
        gp.object[mapNum][23].worldX = 36*gp.tileSize;
        gp.object[mapNum][23].worldY = 33*gp.tileSize;

        gp.object[mapNum][24] = new objBison();
        gp.object[mapNum][24].worldX = 10*gp.tileSize;
        gp.object[mapNum][24].worldY = 23*gp.tileSize;

        gp.object[mapNum][25] = new objBison();
        gp.object[mapNum][25].worldX = 20*gp.tileSize;
        gp.object[mapNum][25].worldY = 43*gp.tileSize;

        gp.object[mapNum][26] = new objBison();
        gp.object[mapNum][26].worldX = 32*gp.tileSize;
        gp.object[mapNum][26].worldY = 30*gp.tileSize;

        gp.object[mapNum][27] = new wolfObject();
        gp.object[mapNum][27].worldX = 15*gp.tileSize;
        gp.object[mapNum][27].worldY = 30*gp.tileSize;

        gp.object[mapNum][28] = new wolfObject();
        gp.object[mapNum][28].worldX = 22*gp.tileSize;
        gp.object[mapNum][28].worldY = 9*gp.tileSize;

        // MAP 2
        mapNum =2;
        gp.object[mapNum][8] = new dungeonObj();
        gp.object[mapNum][8].worldX = 46*gp.tileSize;
        gp.object[mapNum][8].worldY = 23*gp.tileSize;

        gp.object[mapNum][9] = new dungeonObj();
        gp.object[mapNum][9].worldX = 10*gp.tileSize;
        gp.object[mapNum][9].worldY = 13*gp.tileSize;

        gp.object[mapNum][10] = new dungeonObj();
        gp.object[mapNum][10].worldX = 15*gp.tileSize;
        gp.object[mapNum][10].worldY = 43*gp.tileSize;

        gp.object[mapNum][11] = new dungeonObj();
        gp.object[mapNum][11].worldX = 23*gp.tileSize;
        gp.object[mapNum][11].worldY = 7*gp.tileSize;

        gp.object[mapNum][12] = new dungeonObj();
        gp.object[mapNum][12].worldX = 36*gp.tileSize;
        gp.object[mapNum][12].worldY = 33*gp.tileSize;

        gp.object[mapNum][13] = new objBison();
        gp.object[mapNum][13].worldX = 10*gp.tileSize;
        gp.object[mapNum][13].worldY = 23*gp.tileSize;

        gp.object[mapNum][14] = new objBison();
        gp.object[mapNum][14].worldX = 20*gp.tileSize;
        gp.object[mapNum][14].worldY = 43*gp.tileSize;

        gp.object[mapNum][15] = new objBison();
        gp.object[mapNum][15].worldX = 32*gp.tileSize;
        gp.object[mapNum][15].worldY = 30*gp.tileSize;

        gp.object[mapNum][16] = new wolfObject();
        gp.object[mapNum][16].worldX = 15*gp.tileSize;
        gp.object[mapNum][16].worldY = 30*gp.tileSize;

        gp.object[mapNum][17] = new wolfObject();
        gp.object[mapNum][17].worldX = 22*gp.tileSize;
        gp.object[mapNum][17].worldY = 9*gp.tileSize;


    }

    public void setNPC(){
        int mapNum =0;

        /*
        gp.npc[mapNum][0] = new traderNPC(gp);
        gp.npc[mapNum][0].worldX = gp.tileSize *14;
        gp.npc[mapNum][0].worldY = gp.tileSize *14;

         */
    }



}
