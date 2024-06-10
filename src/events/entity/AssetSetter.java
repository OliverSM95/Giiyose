package events.entity;

import events.GatherGamePanel;
import events.entity.Objects.objBison;

public class AssetSetter {


    GatherGamePanel gp;


    public AssetSetter(GatherGamePanel gp) {
        this.gp = gp;
    }

    public void setObject(){
        gp.object[0] = new objBison();
        gp.object[0].worldX = 13 * gp.tileSize;
        gp.object[0].worldY = 23 * gp.tileSize;

    }

}
