package events.entity;

import events.GatherGamePanel;
import events.entity.Objects.coinObj;
import events.entity.Objects.goldOreObject;
import events.entity.Objects.objBison;
import events.entity.Objects.wolfObject;

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


        mapNum =1;
        gp.object[mapNum][3] = new goldOreObject();
        gp.object[mapNum][3].worldX = 30*gp.tileSize;
        gp.object[mapNum][3].worldY = 30*gp.tileSize;

        gp.object[mapNum][4] = new wolfObject();
        gp.object[mapNum][4].worldX = 23*gp.tileSize;
        gp.object[mapNum][4].worldY = 23*gp.tileSize;
    }

    public void setNPC(){
        int mapNum =0;

        gp.npc[mapNum][0] = new traderNPC(gp);
        gp.npc[mapNum][0].worldX = gp.tileSize *14;
        gp.npc[mapNum][0].worldY = gp.tileSize *14;
    }



}
