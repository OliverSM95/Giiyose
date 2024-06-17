package gamestates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.ArrayList;

import entities.EnemyManager;
import entities.Player;
import levels.LevelManager;
import main.Game;
import objects.ObjectManager;
import ui.GameCompletedOverlay;
import ui.GameOverOverlay;
import ui.LevelCompletedOverlay;
import ui.PauseOverlay;
import utilz.LoadSave;
import effects.DialogueEffect;
import effects.Rain;

import static utilz.Constants.Environment.*;
import static utilz.Constants.Dialogue.*;

public class Playing extends State implements Statemethods {

    // class variables
    private Player player;
    // Entity Manager Initialization
    private LevelManager levelManager;
    private EnemyManager enemyManager;
    private ObjectManager objectManager;
    // overlay variables
    private PauseOverlay pauseOverlay;
    private GameOverOverlay gameOverOverlay;
    private GameCompletedOverlay gameCompletedOverlay;
    private LevelCompletedOverlay levelCompletedOverlay;
    private Rain rain;

    private boolean paused = false;

    private int xLvlOffset;// x offset
    // set game borders
    private int leftBorder = (int) (0.25 * Game.GAME_WIDTH);
    private int rightBorder = (int) (0.75 * Game.GAME_WIDTH);
    private int maxLvlOffsetX;// Set maximum offset

    private BufferedImage backgroundImg, bigCloud, smallCloud, shipImgs[];// Create images for background
    private BufferedImage[] questionImgs, exclamationImgs;
    private ArrayList<DialogueEffect> dialogEffects = new ArrayList<>();// Create array list for dialogs

    private int[] smallCloudsPos;// array for cloud positions
    private Random rnd = new Random();

    // game state booleans
    private boolean gameOver;
    private boolean lvlCompleted;
    private boolean gameCompleted;
    private boolean playerDying;
    private boolean drawRain;

    // Ship will be decided to drawn here. It's just a cool addition to the game
    // for the first level. Hinting on that the player arrived with the boat.

    // If you would like to have it on more levels, add a value for objects when
    // creating the level from lvlImgs. Just like any other object.

    // Then play around with position values so it looks correct depending on where
    // you want
    // it.

    private boolean drawShip = true;
    private int shipAni, shipTick, shipDir = 1;
    private float shipHeightDelta, shipHeightChange = 0.05f * Game.SCALE;

    // class constructor
    public Playing(Game game) {
        super(game);
        initClasses();

        // set background images
        backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.PLAYING_BG_IMG);
        bigCloud = LoadSave.GetSpriteAtlas(LoadSave.BIG_CLOUDS);
        smallCloud = LoadSave.GetSpriteAtlas(LoadSave.SMALL_CLOUDS);
        smallCloudsPos = new int[8];
        for (int i = 0; i < smallCloudsPos.length; i++)
            smallCloudsPos[i] = (int) (90 * Game.SCALE) + rnd.nextInt((int) (100 * Game.SCALE));// Scale background clouds and place them randomly

        shipImgs = new BufferedImage[4];
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.SHIP);
        for (int i = 0; i < shipImgs.length; i++)
            shipImgs[i] = temp.getSubimage(i * 78, 0, 78, 72);

        // load assets
        loadDialogue();
        calcLvlOffset();
        loadStartLevel();
        setDrawRainBoolean();
    }

    private void loadDialogue() {
        loadDialogueImgs();

        // Load dialogue array with premade objects, that gets activated when needed.
        // This is a simple
        // way of avoiding ConcurrentModificationException error. (Adding to a list that
        // is being looped through.

        for (int i = 0; i < 10; i++)
            dialogEffects.add(new DialogueEffect(0, 0, EXCLAMATION));
        for (int i = 0; i < 10; i++)
            dialogEffects.add(new DialogueEffect(0, 0, QUESTION));

        for (DialogueEffect de : dialogEffects)
            de.deactive();
    }

    private void loadDialogueImgs() {// load images containing dialogue
        BufferedImage qtemp = LoadSave.GetSpriteAtlas(LoadSave.QUESTION_ATLAS);
        questionImgs = new BufferedImage[5];
        for (int i = 0; i < questionImgs.length; i++)// iterate though dialogue array
            questionImgs[i] = qtemp.getSubimage(i * 14, 0, 14, 12);

        BufferedImage etemp = LoadSave.GetSpriteAtlas(LoadSave.EXCLAMATION_ATLAS);
        exclamationImgs = new BufferedImage[5];
        for (int i = 0; i < exclamationImgs.length; i++)
            exclamationImgs[i] = etemp.getSubimage(i * 14, 0, 14, 12);
    }

    public void loadNextLevel() {// load next level function
        levelManager.setLevelIndex(levelManager.getLevelIndex() + 1);// increase the maps completed by 1
        levelManager.loadNextLevel();// load next level
        player.setSpawn(levelManager.getCurrentLevel().getPlayerSpawn());//reset player spawn
        resetAll();
        drawShip = false;// remove ship sprite once level 1 is passed
    }

    private void loadStartLevel() {// load starting level
        enemyManager.loadEnemies(levelManager.getCurrentLevel());
        objectManager.loadObjects(levelManager.getCurrentLevel());
    }

    private void calcLvlOffset() {
        maxLvlOffsetX = levelManager.getCurrentLevel().getLvlOffset();
    }

    private void initClasses() {
        // create entity managers
        levelManager = new LevelManager(game);
        enemyManager = new EnemyManager(this);
        objectManager = new ObjectManager(this);

        player = new Player(200, 200, (int) (64 * Game.SCALE), (int) (40 * Game.SCALE), this);// scale the player
        player.loadLvlData(levelManager.getCurrentLevel().getLevelData());// load level data
        player.setSpawn(levelManager.getCurrentLevel().getPlayerSpawn());// set player spawn

        // create overlay objects
        pauseOverlay = new PauseOverlay(this);
        gameOverOverlay = new GameOverOverlay(this);
        levelCompletedOverlay = new LevelCompletedOverlay(this);
        gameCompletedOverlay = new GameCompletedOverlay(this);

        rain = new Rain();
    }

    @Override
    public void update() {// update entity objects
        if (paused)
            pauseOverlay.update();// update pause overlay
        else if (lvlCompleted)
            levelCompletedOverlay.update(); // update level completed overlay
        else if (gameCompleted)
            gameCompletedOverlay.update();// update game finished overlay
        else if (gameOver)
            gameOverOverlay.update(); // update game over overlay
        else if (playerDying)
            player.update();// if player takes damage update sprite
        else {
            updateDialogue();
            if (drawRain)/// draw rain
                rain.update(xLvlOffset);
            levelManager.update();
            objectManager.update(levelManager.getCurrentLevel().getLevelData(), player);
            player.update();
            enemyManager.update(levelManager.getCurrentLevel().getLevelData());
            checkCloseToBorder();
            if (drawShip)
                updateShipAni();
        }
    }

    private void updateShipAni() {
        shipTick++;// animate starting ship to bob in water based on set tick speed
        if (shipTick >= 35) {
            shipTick = 0;
            shipAni++;
            if (shipAni >= 4)
                shipAni = 0;
        }

        shipHeightDelta += shipHeightChange * shipDir;
        shipHeightDelta = Math.max(Math.min(10 * Game.SCALE, shipHeightDelta), 0);

        if (shipHeightDelta == 0)
            shipDir = 1;// bob ship up
        else if (shipHeightDelta == 10 * Game.SCALE)// check if ship has reached animation "apex" and start lowing height
            shipDir = -1;// bob ship down

    }

    private void updateDialogue() {
        for (DialogueEffect de : dialogEffects)// update entity dialogue
            if (de.isActive())
                de.update();
    }

    private void drawDialogue(Graphics g, int xLvlOffset) {// draw entity dialogue
        for (DialogueEffect de : dialogEffects)
            if (de.isActive()) {//If dialogue active
                if (de.getType() == QUESTION)
                    g.drawImage(questionImgs[de.getAniIndex()], de.getX() - xLvlOffset, de.getY(), DIALOGUE_WIDTH, DIALOGUE_HEIGHT, null);// draw question mark
                else
                    g.drawImage(exclamationImgs[de.getAniIndex()], de.getX() - xLvlOffset, de.getY(), DIALOGUE_WIDTH, DIALOGUE_HEIGHT, null);// draw exclamation mark
            }
    }

    public void addDialogue(int x, int y, int type) {
        // Not adding a new one, we are recycling. #ThinkGreen lol
        dialogEffects.add(new DialogueEffect(x, y - (int) (Game.SCALE * 15), type));
        for (DialogueEffect de : dialogEffects)
            if (!de.isActive())
                if (de.getType() == type) {
                    de.reset(x, -(int) (Game.SCALE * 15));
                    return;
                }
    }

    private void checkCloseToBorder() {//check if player is close to game border
        int playerX = (int) player.getHitbox().x;
        int diff = playerX - xLvlOffset;

        if (diff > rightBorder)
            xLvlOffset += diff - rightBorder;
        else if (diff < leftBorder)
            xLvlOffset += diff - leftBorder;

        xLvlOffset = Math.max(Math.min(xLvlOffset, maxLvlOffsetX), 0);// offset player location if they approach the game border
    }

    @Override
    public void draw(Graphics g) {// draw method ( ordered in terms of layers)
        g.drawImage(backgroundImg, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);// draw background

        drawClouds(g);// draw clounds
        if (drawRain)// if rain is active then draw it
            rain.draw(g, xLvlOffset);

        if (drawShip)// draw ship if map is level 1
            g.drawImage(shipImgs[shipAni], (int) (100 * Game.SCALE) - xLvlOffset, (int) ((288 * Game.SCALE) + shipHeightDelta), (int) (78 * Game.SCALE), (int) (72 * Game.SCALE), null);

        /// draw entities
        levelManager.draw(g, xLvlOffset);
        objectManager.draw(g, xLvlOffset);
        enemyManager.draw(g, xLvlOffset);
        player.render(g, xLvlOffset);
        objectManager.drawBackgroundTrees(g, xLvlOffset);
        drawDialogue(g, xLvlOffset);

        if (paused) {
            g.setColor(new Color(0, 0, 0, 150));
            g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
            pauseOverlay.draw(g);
        } else if (gameOver)
            gameOverOverlay.draw(g);
        else if (lvlCompleted)
            levelCompletedOverlay.draw(g);
        else if (gameCompleted)
            gameCompletedOverlay.draw(g);

    }

    private void drawClouds(Graphics g) {// draw cloud method
        for (int i = 0; i < 4; i++)
            g.drawImage(bigCloud, i * BIG_CLOUD_WIDTH - (int) (xLvlOffset * 0.3), (int) (204 * Game.SCALE), BIG_CLOUD_WIDTH, BIG_CLOUD_HEIGHT, null);// draw big cloud

        for (int i = 0; i < smallCloudsPos.length; i++)
            g.drawImage(smallCloud, SMALL_CLOUD_WIDTH * 4 * i - (int) (xLvlOffset * 0.7), smallCloudsPos[i], SMALL_CLOUD_WIDTH, SMALL_CLOUD_HEIGHT, null);// Draw Small Cloud
    }

    public void setGameCompleted() {
        gameCompleted = true;
    }

    public void resetGameCompleted() {
        gameCompleted = false;
    }

    public void resetAll() {//reset all game attributes
        gameOver = false;
        paused = false;
        lvlCompleted = false;
        playerDying = false;
        drawRain = false;

        setDrawRainBoolean();

        player.resetAll();
        enemyManager.resetAllEnemies();
        objectManager.resetAllObjects();
        dialogEffects.clear();
    }

    private void setDrawRainBoolean() {
        // This method makes it rain 20% of the time you load a level.
        if (rnd.nextFloat() >= 0.8f)
            drawRain = true;
    }

    //Set & Get Methods
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void checkObjectHit(Rectangle2D.Float attackBox) {
        objectManager.checkObjectHit(attackBox);
    }

    public void checkEnemyHit(Rectangle2D.Float attackBox) {
        enemyManager.checkEnemyHit(attackBox);
    }

    public void checkPotionTouched(Rectangle2D.Float hitbox) {
        objectManager.checkObjectTouched(hitbox);
    }

    public void checkSpikesTouched(Player p) {
        objectManager.checkSpikesTouched(p);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!gameOver) {
            if (e.getButton() == MouseEvent.BUTTON1)
                player.setAttacking(true);
            else if (e.getButton() == MouseEvent.BUTTON3)
                player.powerAttack();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!gameOver && !gameCompleted && !lvlCompleted)
            switch (e.getKeyCode()) {
                case KeyEvent.VK_A:// if player presses 'a'
                    player.setLeft(true);// move left
                    break;
                case KeyEvent.VK_D:// if player presses 'd'

                    player.setRight(true);// move right
                    break;
                case KeyEvent.VK_SPACE:// if player presses ' space '
                    player.setJump(true);// jump
                    break;
                case KeyEvent.VK_ESCAPE:// if player presses 'escape'
                    paused = !paused;// pause game
            }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (!gameOver && !gameCompleted && !lvlCompleted)
            switch (e.getKeyCode()) {
                case KeyEvent.VK_A:// if 'a' key released
                    player.setLeft(false);
                    break;
                case KeyEvent.VK_D:// if 'd' key released
                    player.setRight(false);
                    break;
                case KeyEvent.VK_SPACE:// if 'space' key released
                    player.setJump(false);
                    break;
            }
    }

    public void mouseDragged(MouseEvent e) {
        if (!gameOver && !gameCompleted && !lvlCompleted)
            if (paused)
                pauseOverlay.mouseDragged(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (gameOver)
            gameOverOverlay.mousePressed(e);
        else if (paused)
            pauseOverlay.mousePressed(e);
        else if (lvlCompleted)
            levelCompletedOverlay.mousePressed(e);
        else if (gameCompleted)
            gameCompletedOverlay.mousePressed(e);

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (gameOver)
            gameOverOverlay.mouseReleased(e);
        else if (paused)
            pauseOverlay.mouseReleased(e);
        else if (lvlCompleted)
            levelCompletedOverlay.mouseReleased(e);
        else if (gameCompleted)
            gameCompletedOverlay.mouseReleased(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (gameOver)
            gameOverOverlay.mouseMoved(e);
        else if (paused)
            pauseOverlay.mouseMoved(e);
        else if (lvlCompleted)
            levelCompletedOverlay.mouseMoved(e);
        else if (gameCompleted)
            gameCompletedOverlay.mouseMoved(e);
    }

    public void setLevelCompleted(boolean levelCompleted) {// set level as completed
        game.getAudioPlayer().lvlCompleted();
        if (levelManager.getLevelIndex() + 1 >= levelManager.getAmountOfLevels()) {
            // No more levels
            gameCompleted = true;
            levelManager.setLevelIndex(0);
            levelManager.loadNextLevel();
            resetAll();
            return;
        }
        this.lvlCompleted = levelCompleted;
    }

    // Set and get methods

    public void setMaxLvlOffset(int lvlOffset) {
        this.maxLvlOffsetX = lvlOffset;
    }

    public void unpauseGame() {
        paused = false;
    }

    public void windowFocusLost() {
        player.resetDirBooleans();
    }

    public Player getPlayer() {
        return player;
    }

    public EnemyManager getEnemyManager() {
        return enemyManager;
    }

    public ObjectManager getObjectManager() {
        return objectManager;
    }

    public LevelManager getLevelManager() {
        return levelManager;
    }

    public void setPlayerDying(boolean playerDying) {
        this.playerDying = playerDying;
    }
}