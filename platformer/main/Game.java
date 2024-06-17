package main;

import java.awt.Graphics;

import audio.AudioPlayer;
import gamestates.Credits;
import gamestates.GameOptions;
import gamestates.Gamestate;
import gamestates.Menu;
import gamestates.Playing;
import ui.AudioOptions;

public class Game implements Runnable {


    // Initializing gamePanel and gameThread
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120; // Setting FPS
    private final int UPS_SET = 200; // Setting Updates per second

    // Initializing main menus/screens
    private Playing playing;
    private Menu menu;
    private Credits credits;
    private GameOptions gameOptions;
    private AudioOptions audioOptions;
    private AudioPlayer audioPlayer;

    // Setting actual game screen properties
    public final static int TILES_DEFAULT_SIZE = 32; // tiles size used in outside_sprites.png
    public final static float SCALE = 2f; // Game scale
    public final static int TILES_IN_WIDTH = 26; // Actual tile width
    public final static int TILES_IN_HEIGHT = 14; // Actual tile height
    public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE); // Changing the tiles size based on scale
    public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH; // Window properties
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT; // Window properties

    private final boolean SHOW_FPS_UPS = true; // Displaying fps and ups on console

    public Game() {
        System.out.println("Size: " + GAME_WIDTH + " : " + GAME_HEIGHT); // Displaying game screen size
        initClasses(); // Instantiating each class
        gamePanel = new GamePanel(this);
        new GameWindow(gamePanel);
        gamePanel.requestFocusInWindow();
        startGameLoop(); // Starting main game loop
    }

    private void initClasses() { // Instantiating classes method
        audioOptions = new AudioOptions(this); // Audio chaning options
        audioPlayer = new AudioPlayer(); // Audio player
        menu = new Menu(this); // Menu screen
        playing = new Playing(this); // Playing state / main game
        credits = new Credits(this); // Credits screen
        gameOptions = new GameOptions(this); // Options screen
    }

    private void startGameLoop() { // Starting game loop method
        gameThread = new Thread(this); // Creating game thread
        gameThread.start(); // Starting game thread
    }

    public void update() { // Updating game states
        switch (Gamestate.state) {
            case MENU -> menu.update(); // Menu case points to updating menu screen
            case PLAYING -> playing.update(); // Playing case points to updating playing screen
            case OPTIONS -> gameOptions.update(); // Options case points to options menu screen
            case CREDITS -> credits.update(); //Credits case points to credits menu screen
            case QUIT -> System.exit(0); // Quit case points to quit menu screen
        }
    }

    public void render(Graphics g) { // Actually rendering screens / menus
        switch (Gamestate.state) {
            case MENU -> menu.draw(g); // Menu case points to rendering menu screen
            case PLAYING -> playing.draw(g); // Playing case points to rendering playing screen
            case OPTIONS -> gameOptions.draw(g); // Options case points to rendering options screen
            case CREDITS -> credits.draw(g); // Credits case points to rendering credits screen
        }
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS_SET;  // Calculating times per frame
        double timePerUpdate = 1000000000.0 / UPS_SET; // Calculating times per update

        long previousTime = System.nanoTime(); // Record previous time

        // Set both frames and updates to zero
        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis(); // Record lastCheck var

        // Set delta updates/frames to 0
        double deltaU = 0;
        double deltaF = 0;

        while (true) { // Frame / Update counter

            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            //Update / run general

            if (deltaU >= 1) {

                update();
                updates++;
                deltaU--;

            }

            if (deltaF >= 1) {

                gamePanel.repaint();
                frames++;
                deltaF--;

            }

            // Catching lost frames / updates
            if (SHOW_FPS_UPS)
                if (System.currentTimeMillis() - lastCheck >= 1000) {

                    lastCheck = System.currentTimeMillis();
                    System.out.println("FPS: " + frames + " | UPS: " + updates); // Printing FPS and UPS to console
                    // Resetting frames / updates when time since last check is greater than or equal to 1000 ms
                    frames = 0;
                    updates = 0;

                }

        }
    }

    // Resetting player directions when game window has lost focus (alt-tabbed)
    // Completely stops player from moving
    public void windowFocusLost() {
        if (Gamestate.state == Gamestate.PLAYING)
            playing.getPlayer().resetDirBooleans();
    }

    // Getter methods for main classes

    public Menu getMenu() {
        return menu;
    }

    public Playing getPlaying() {
        return playing;
    }

    public Credits getCredits() {
        return credits;
    }

    public GameOptions getGameOptions() {
        return gameOptions;
    }

    public AudioOptions getAudioOptions() {
        return audioOptions;
    }

    public AudioPlayer getAudioPlayer() {
        return audioPlayer;
    }
}