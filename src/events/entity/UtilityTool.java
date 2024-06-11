package events.entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UtilityTool {


    public BufferedImage scaleImage(BufferedImage original, int width, int height){

        // scaling images before game loop to speed up rendering
        BufferedImage scaledImage = new BufferedImage(width,height,original.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original,0,0,width,height,null);
        g2.dispose();
        //tile[0].image = scaledImage;

        return scaledImage;
    }

}
