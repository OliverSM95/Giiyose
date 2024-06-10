package events.entity.Objects;

import java.io.IOException;
import javax.imageio.ImageIO;
public class coinObj extends superObject{

    public coinObj(){

        name = "Coin";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/events/entity/objectImages/coinSprite.png"));
        }catch (IOException e){
            e.printStackTrace();
        }

    }

}
