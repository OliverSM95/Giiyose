package events.entity.Objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class dungeonObj extends superObject {


    public dungeonObj(){

        name = "dungeon";
        collision = true;


        try{
            image = ImageIO.read(getClass().getResourceAsStream("/events/entity/objectImages/dungeonSprite.png"));
        }catch (IOException e){
            e.printStackTrace();
        }


    }

}
