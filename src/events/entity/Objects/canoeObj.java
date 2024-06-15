package events.entity.Objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class canoeObj extends superObject{

    public canoeObj(){
        name = "canoe";
        collision = true;

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/events/entity/objectImages/cannoeSprite.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
