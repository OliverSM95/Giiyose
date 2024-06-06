package events;

import javax.swing.*;

public class GatherGamePanel extends JPanel {


    //screen Settings
    final int originalTileSize = 16;
    final int scale =3; // set scale for 16x16 sprites
    final int tileSize = originalTileSize * scale; // scale tile size
    // number of tiles on the screen (4:3) ratio
    final int maxScreenColum = 16;
    final int maxScreenRow = 12;
    // set Jframe sizes
    final int screenWidth = tileSize*maxScreenColum;
    final int screenHeight = tileSize*maxScreenRow;


}
