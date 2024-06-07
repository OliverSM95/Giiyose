package events;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GatherGamePanel extends JPanel implements Runnable{


    //screen Settings
    final int originalTileSize = 16;
    final int scale =3; // set scale for 16x16 sprites
    final int tileSize = originalTileSize * scale; // scale tile size
    // number of tiles on the screen (4:3) ratio
    final int maxScreenColum = 16;
    final int maxScreenRow = 12;
    // set Jframe sizes
    final int screenWidth = tileSize*maxScreenColum;//768 pixels
    final int screenHeight = tileSize*maxScreenRow;//576 pixels

    //add key handler
    KeyHandler keyH = new KeyHandler();

    // add FPS and Time
    Thread gameThread;



    //set player default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed =4;

    public GatherGamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); // used for better rendering
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public  void startGameThread(){
        gameThread = new Thread(this);// passing GatherGamePanel class to thread
        gameThread.start();
    }

    public final int FPS =60;


    @Override
    public void run() {
        double drawInterval = 1000000000.0/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        //create game loop
        while(gameThread != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime)/drawInterval;
            lastTime = currentTime;

            if(delta >= 1) {
                // update information
                update();
                // redraw the screen
                repaint();
                delta--;

            }

        }}



    public void update(){// update player position when key pressed
    if(keyH.upPressed){
        playerY -= playerSpeed;

    }else if(keyH.downPressed){
        playerY += playerSpeed;
    }else if(keyH.leftPressed){
        playerX -= playerSpeed;
    }else if(keyH.rightPressed){
        playerX += playerSpeed;
    }
    }


    public void paintComponent(Graphics g){

    super.paintComponent(g);

    Graphics2D g2 = (Graphics2D) g;// change graphic g to Graphics 2d
    g2.setColor(Color.WHITE);
    g2.fillRect(playerX, playerY, tileSize, tileSize);
    g2.dispose();// used to save memory
    }
}
