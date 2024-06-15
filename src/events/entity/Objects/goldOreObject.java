package events.entity.Objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class goldOreObject extends  superObject{


    public goldOreObject(){

        name = "goldOre";
        collision = true;

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/events/entity/backgroundTiles/goldOreSprite.png"));
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
