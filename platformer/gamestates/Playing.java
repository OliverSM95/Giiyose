package gamestates;

import entities.EnemyManager;
import entities.Player;
import levels.LevelManager;
import main.Game;
import ui.GameOverOverlay;
import ui.PauseOverlay;
import utilz.LoadSave;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import static utilz.Constants.Environment.*;

public class Playing extends State implements Statemethods{
    private Player player;
    private LevelManager levelManager;
    private EnemyManager enemyManager;
    private GameOverOverlay gameOverOverlay;
    private PauseOverlay pauseOverlay;
    private boolean paused = false;

    private int xLvlOffet; // For moving screen
    private int leftBorder = (int) (0.2 * Game.GAME_WIDTH);
    private int rightBorder = (int) (0.8 * Game.GAME_WIDTH);
    private int lvlTilesWide = LoadSave.GetLevelData()[0].length;
    private int maxTilesOffset = lvlTilesWide - Game.TILES_IN_WIDTH;
    private int maxLvlOffsetX = maxTilesOffset * Game.TILES_SIZE;

    private BufferedImage backgroundImg, bigCloud, smallCloud;
    private int[] smallCloudsPos;
    private Random rnd = new Random();

    private boolean gameOver;

    public Playing(Game game) {
        super(game);
        initClasses();

        backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.PLAYING_BG_IMG);
        bigCloud = LoadSave.GetSpriteAtlas(LoadSave.BIG_CLOUDS);
        smallCloud = LoadSave.GetSpriteAtlas(LoadSave.SMALL_CLOUDS);
        smallCloudsPos = new int[8];
        for (int i = 0; i < smallCloudsPos.length; i++)
            smallCloudsPos[i] = (int) (90 * Game.SCALE) + rnd.nextInt((int)(100 * Game.SCALE));
    }

    private void initClasses(){
        levelManager = new LevelManager(game);
        enemyManager = new EnemyManager(this);
        player = new Player(200,200, (int) (64* Game.SCALE), (int) (40* Game.SCALE), this);
        player.loadLvlData(levelManager.getCurrentLevel().getLevelData());
        pauseOverlay = new PauseOverlay(this);
        gameOverOverlay = new GameOverOverlay(this);
    }

    @Override
    public void update() {
        if (!paused && !gameOver){
            levelManager.update();
            player.update();
            enemyManager.update(levelManager.getCurrentLevel().getLevelData(), player);
            checkCloseToBorder();
        }else{
            pauseOverlay.update();
        }
    }

    private void checkCloseToBorder(){
        int playerX = (int) player.getHitbox().x;
        int diff = playerX - xLvlOffet;

        if (diff > rightBorder)
            xLvlOffet += diff - rightBorder;
        else if (diff < leftBorder)
            xLvlOffet += diff - leftBorder;

        if (xLvlOffet > maxLvlOffsetX)
            xLvlOffet = maxLvlOffsetX;
        else if (xLvlOffet < 0)
            xLvlOffet = 0;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundImg, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);

        drawClouds(g);

        levelManager.draw(g, xLvlOffet);
        player.render(g, xLvlOffet);
        enemyManager.draw(g, xLvlOffet);

        if (paused){
            g.setColor(new Color(0,0,0,150));
            g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
            pauseOverlay.draw(g);
        }else if (gameOver)
            gameOverOverlay.draw(g);
    }

    private void drawClouds(Graphics g){

        for (int i = 0; i < 3; i++) // Big Clouds
            g.drawImage(bigCloud, i * BIG_CLOUD_WIDTH - (int) (xLvlOffet * 0.3), (int) (204 * Game.SCALE), BIG_CLOUD_WIDTH, BIG_CLOUD_HEIGHT, null);

        for (int i = 0; i < smallCloudsPos.length; i++) // Small Clouds
            g.drawImage(smallCloud, SMALL_CLOUD_WIDTH * 4 * i - (int) (xLvlOffet * 0.7), smallCloudsPos[i], SMALL_CLOUD_WIDTH, SMALL_CLOUD_HEIGHT, null);
    }

    public void resetAll(){
        //TODO: reset player, enemy, lvl, etc.
        gameOver = false;
        paused = false;
        player.resetAll();
        enemyManager.resetAllEnemies();
    }

    public void setGameOver(boolean gameOver){
        this.gameOver = gameOver;
    }

    public void checkEnemyHit(Rectangle2D.Float attackBox){
        enemyManager.checkEnemyHit(attackBox);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!gameOver)
            if (e.getButton() == java.awt.event.MouseEvent.BUTTON1){
                player.setAttacking(true);
            }
    }

    public void mouseDragged(MouseEvent e){
        if (!gameOver)
            if (paused)
                pauseOverlay.mouseDragged(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (!gameOver)
            if (paused)
                pauseOverlay.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (!gameOver)
            if (paused)
                pauseOverlay.mouseReleased(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (!gameOver)
            if (paused)
                pauseOverlay.mouseMoved(e);
    }

    public void unpauseGame(){
        paused = false;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (gameOver)
            gameOverOverlay.keyPressed(e);
        else
            switch (e.getKeyCode()) {
                case KeyEvent.VK_A:
                    player.setLeft(true);
                    break;
                case KeyEvent.VK_D:
                    player.setRight(true);
                    break;
                case KeyEvent.VK_SPACE:
                    player.setJump(true);
                    break;
                case KeyEvent.VK_ESCAPE:
                    paused = !paused;
                    break;
            }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (!gameOver)
            switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                player.setLeft(false);
                break;
            case KeyEvent.VK_D:
                player.setRight(false);
                break;
            case KeyEvent.VK_SPACE:
                player.setJump(false);
                break;
            }
    }

    public void windowFocusLost(){
        player.resetDirBooleans();
    }

    public Player getPlayer(){
        return player;
    }
}