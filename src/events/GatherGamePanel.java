package events;

import com.sun.tools.javac.Main;
import events.entity.AssetSetter;
import events.entity.Entity;
import events.entity.Objects.superObject;
import events.entity.Tiles.tileManager;
import events.entity.gamePlayer;
import main.MainGame;
import main.mainMapMenu;
import player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GatherGamePanel extends JPanel implements Runnable {

    // add mainplayer
    Player mainPl = new Player();


    // Screen Settings
    final int originalTileSize = 16;
    final int scale = 3; // set scale for 16x16 sprites
    public final int tileSize = originalTileSize * scale; // scale tile size

    // World Settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int maxMap =10;
    public int currentMap =0;


    // Dynamically set screen size
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public final int screenWidth = (int) screenSize.getWidth();
    public final int screenHeight = (int) screenSize.getHeight();

    // Instantiate background tile manager
    tileManager tileM = new tileManager(this);

    //Instantiate sound class
    Sound soundEffect = new Sound();
    Sound music = new Sound();



    // Add key handler
    KeyHandler keyH = new KeyHandler();





    //instantiate hunting minigames
    Hunting huntGame = new Hunting(mainPl);

    // Add FPS and Time
    public final int FPS = 60;
   public Thread gameThread;

    // Instantiate collision checker
    public CollisionChecker cChecker = new CollisionChecker(this);

    // Instantiate User Interface
    public UI ui = new UI(this,mainPl);

    // Instantiate AssetSetter
    public AssetSetter asSetter = new AssetSetter(this);

    // INSTANTIATE ENTITY & OBJECTS
    public gamePlayer player = new gamePlayer(this, keyH,mainPl,huntGame);
    public Entity npc[][] = new Entity[maxMap][10];
    public superObject object[][] = new superObject[maxMap][50];



    //game state (to handle pause menu)
    public int gameState;
    public final int playState =1;
    public final int pauseState =2;

    mainMapMenu mg;

    //JButton returnToMap = new JButton("Return to Map");



    /*
    ===============================================
    Default constructor for Game Panel
    ===============================================
    */
    public GatherGamePanel(mainMapMenu mainGame) {
        //this.mg = mainGame;
        // use inherited methods from JPanel Class
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); // used for better rendering
        this.addKeyListener(keyH);
        this.setFocusable(true);

       // returnToMap.setBounds(50,50,200,100);
       // returnToMap.setFont(new Font("Arial",Font.BOLD,20));
       // returnToMap.addActionListener(e ->{
            //this.setVisible(false);
            //mainGame.setupControlPanel();
          //  mainGame.setVisible(true);

         //  System.out.println("Button Pressed");
            // SwingUtilities.invokeLater(MainGame::new);
      //  });
       // this.add(returnToMap);
    }



    public void setupGame(){
        asSetter.setObject();
        asSetter.setNPC();
        gameState = playState;

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

        if(gameState == playState){
            player.update(); // call function from gamePlayer Class, located in entity package
        }
        if(gameState == pauseState){
            // dont update game information while game is paused
        }



    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g; // change graphic g to Graphics 2d

        //draw tiles
        tileM.draw(g2); // this must be drawn before the player sprite is drawn

        //draw objects
        for(int i = 0; i < object[1].length; i++) {
            if(object[currentMap][i] != null) {
                object[currentMap][i].draw(g2,this);
            }
        }

        // DRAW NPC
        for(int i = 0; i < npc[1].length; i++) {
            if(npc[currentMap][i] != null) {
                npc[currentMap][i].draw(g2);
            }
        }

        //draw player
        player.draw(g2); // call draw function from [src/entity/gamePlayer.java]

        //draw uI
        ui.draw(g2);

        g2.dispose(); // used to save memory
    }

    // sound methods

    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic(){
        music.stop();
    }

    public void playSoundEffect(int i){

        soundEffect.setFile(i);
        soundEffect.play();

    }

}
