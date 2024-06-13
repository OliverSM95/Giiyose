package events.entity;

import events.GatherGamePanel;
import events.entity.Objects.coinObj;
import events.entity.Objects.objBison;

public class AssetSetter {


    GatherGamePanel gp;


    /*
    Class used to create new game objects
     */


    public AssetSetter(GatherGamePanel gp) {
        this.gp = gp;
    }

    public void setObject(){
        gp.object[0] = new objBison();
        gp.object[0].worldX = 13 * gp.tileSize;
        gp.object[0].worldY = 23 * gp.tileSize;

        gp.object[1] = new coinObj();
        gp.object[1].worldX = 30 * gp.tileSize;
        gp.object[1].worldY = 20 * gp.tileSize;

        // add coin object to map
        gp.object[2] = new coinObj();
        gp.object[2].worldX = 10 * gp.tileSize;
        gp.object[2].worldY = 20 * gp.tileSize;
    }

    public void setNPC(){
        gp.npc[0] = new traderNPC(gp);
        gp.npc[0].worldX = gp.tileSize *14;
        gp.npc[0].worldY = gp.tileSize *14;
    }



}
