package events.entity.Objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class wolfObject extends superObject{

    public wolfObject(){
        name = "wolf";
        collision = true;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/events/entity/objectImages/wolfSprite.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
