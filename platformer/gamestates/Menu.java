package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import main.Game;
import ui.MenuButton;
import utilz.LoadSave;

public class Menu extends State implements Statemethods {

    // class variables
    private MenuButton[] buttons = new MenuButton[4];
    private BufferedImage backgroundImg, backgroundImgPink;
    private int menuX, menuY, menuWidth, menuHeight;

    //class constructor
    public Menu(Game game) {
        super(game);
        //load buttons and game background
        loadButtons();
        loadBackground();
        // set menu background
        backgroundImgPink = LoadSave.GetSpriteAtlas(LoadSave.MENU_BACKGROUND_IMG);

    }

    private void loadBackground() {// load background
        backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.MENU_BACKGROUND);//set background image
        menuWidth = (int) (backgroundImg.getWidth() * Game.SCALE);// set menu width
        menuHeight = (int) (backgroundImg.getHeight() * Game.SCALE);// set menu height
        menuX = Game.GAME_WIDTH / 2 - menuWidth / 2;// set menu x value
        menuY = (int) (25 * Game.SCALE); // set menu y value
    }

    private void loadButtons() {
        //load buttons (and scale them)
        buttons[0] = new MenuButton(Game.GAME_WIDTH / 2, (int) (130 * Game.SCALE), 0, Gamestate.PLAYING);// create playing button
        buttons[1] = new MenuButton(Game.GAME_WIDTH / 2, (int) (200 * Game.SCALE), 1, Gamestate.OPTIONS);// create options button
        buttons[2] = new MenuButton(Game.GAME_WIDTH / 2, (int) (270 * Game.SCALE), 3, Gamestate.CREDITS);// create Credits button
        buttons[3] = new MenuButton(Game.GAME_WIDTH / 2, (int) (340 * Game.SCALE), 2, Gamestate.QUIT);// create Quit button
    }

    @Override
    public void update() {// update menu buttons
        for (MenuButton mb : buttons)
            mb.update();
    }

    @Override
    public void draw(Graphics g) {// draw background images
        g.drawImage(backgroundImgPink, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
        g.drawImage(backgroundImg, menuX, menuY, menuWidth, menuHeight, null);

        for (MenuButton mb : buttons)// draw buttons
            mb.draw(g);
    }

    @Override
    public void mousePressed(MouseEvent e) { // if mouse pressed in menu
        for (MenuButton mb : buttons) {
            if (isIn(e, mb)) {
                mb.setMousePressed(true);//set pressed = true
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {// if mouse released in menu
        for (MenuButton mb : buttons) {
            if (isIn(e, mb)) {
                if (mb.isMousePressed())
                    mb.applyGamestate();
                if (mb.getState() == Gamestate.PLAYING)// set game state to playing game
                    game.getAudioPlayer().setLevelSong(game.getPlaying().getLevelManager().getLevelIndex());// start background audio
                break;
            }
        }
        resetButtons();// reset buttons
    }

    private void resetButtons() {// reset button function
        for (MenuButton mb : buttons)
            mb.resetBools();

    }


    //MOUSE AND KEY PRESSED FUNCTIONS
    @Override
    public void mouseMoved(MouseEvent e) {
        for (MenuButton mb : buttons)
            mb.setMouseOver(false);

        for (MenuButton mb : buttons)
            if (isIn(e, mb)) {
                mb.setMouseOver(true);
                break;
            }

    }
    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

}