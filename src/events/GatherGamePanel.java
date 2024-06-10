package events;

import events.entity.Tiles.tileManager;
import events.entity.gamePlayer;

import javax.swing.*;
import java.awt.*;

public class GatherGamePanel extends JPanel implements Runnable {

    // Screen Settings
    final int originalTileSize = 16;
    final int scale = 3; // set scale for 16x16 sprites
    public final int tileSize = originalTileSize * scale; // scale tile size

    // World Settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    // Dynamically set screen size
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public final int screenWidth = (int) screenSize.getWidth();
    public final int screenHeight = (int) screenSize.getHeight();

    // Instantiate background tile manager
    tileManager tileM = new tileManager(this);

    // Add key handler
    KeyHandler keyH = new KeyHandler();

    // Add FPS and Time
    public final int FPS = 60;
    Thread gameThread;

    // Instantiate collision checker
    public CollisionChecker cChecker = new CollisionChecker(this);

    // Initiate new gamePlayer
    public gamePlayer player = new gamePlayer(this, keyH);

    /*
    Default constructor for Game Panel
    */
    public GatherGamePanel() {
        // use inherited methods from JPanel Class
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); // used for better rendering
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this); // passing GatherGamePanel class to thread
        gameThread.start(); // start game timer loop
    }

    @Override
    public void run() {
        double drawInterval = 1000000000.0 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        // Create game loop
        while (gameThread != null) {
            // FPS system setting a 1/60 seconds delay between frames
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if (delta >= 1) {
                // Update information
                update();
                // Redraw the screen
                repaint();
                delta--;
            }
        }
    }

    public void update() { // update player position when key pressed
        player.update(); // call function from gamePlayer Class, located in entity package
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g; // change graphic g to Graphics 2d

        tileM.draw(g2); // this must be drawn before the player sprite is drawn
        player.draw(g2); // call draw function from [src/entity/gamePlayer.java]

        g2.dispose(); // used to save memory
    }
}
