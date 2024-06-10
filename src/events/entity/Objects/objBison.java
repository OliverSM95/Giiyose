package events.entity.Objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class objBison extends superObject{



    public objBison(){
        name = "Bison";
        collision = true;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/events/entity/objectImages/bisonSprite.png"));

        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
