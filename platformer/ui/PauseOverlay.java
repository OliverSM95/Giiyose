package ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import gamestates.Gamestate;
import gamestates.Playing;
import main.Game;
import utilz.LoadSave;
import static utilz.Constants.UI.URMButtons.*;

public class PauseOverlay {

    private Playing playing; // Reference to the playing state
    private BufferedImage backgroundImg; // Background image for the pause overlay
    private int bgX, bgY, bgW, bgH; // Position and dimensions for the background image
    private AudioOptions audioOptions; // Audio options for the pause overlay
    private UrmButton menuB, replayB, unpauseB; // Buttons for menu, replay, and unpause

    // Constructor to initialize the pause overlay
    public PauseOverlay(Playing playing) {
        this.playing = playing;
        loadBackground(); // Load the background image
        audioOptions = playing.getGame().getAudioOptions(); // Get audio options from the game
        createUrmButtons(); // Create the URM buttons
    }

    // Method to create URM buttons (menu, replay, unpause)
    private void createUrmButtons() {
        int menuX = (int) (313 * Game.SCALE);
        int replayX = (int) (387 * Game.SCALE);
        int unpauseX = (int) (462 * Game.SCALE);
        int bY = (int) (325 * Game.SCALE);

        menuB = new UrmButton(menuX, bY, URM_SIZE, URM_SIZE, 2);
        replayB = new UrmButton(replayX, bY, URM_SIZE, URM_SIZE, 1);
        unpauseB = new UrmButton(unpauseX, bY, URM_SIZE, URM_SIZE, 0);
    }

    // Method to load the background image
    private void loadBackground() {
        backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.PAUSE_BACKGROUND);
        bgW = (int) (backgroundImg.getWidth() * Game.SCALE);
        bgH = (int) (backgroundImg.getHeight() * Game.SCALE);
        bgX = Game.GAME_WIDTH / 2 - bgW / 2;
        bgY = (int) (25 * Game.SCALE);
    }

    // Update method to update URM buttons and audio options
    public void update() {
        menuB.update();
        replayB.update();
        unpauseB.update();
        audioOptions.update();
    }

    // Draw method to render the pause overlay
    public void draw(Graphics g) {
        // Draw the background image
        g.drawImage(backgroundImg, bgX, bgY, bgW, bgH, null);

        // Draw URM buttons
        menuB.draw(g);
        replayB.draw(g);
        unpauseB.draw(g);

        // Draw audio options
        audioOptions.draw(g);
    }

    // Handle mouse dragged events
    public void mouseDragged(MouseEvent e) {
        audioOptions.mouseDragged(e);
    }

    // Handle mouse pressed events
    public void mousePressed(MouseEvent e) {
        if (isIn(e, menuB))
            menuB.setMousePressed(true);
        else if (isIn(e, replayB))
            replayB.setMousePressed(true);
        else if (isIn(e, unpauseB))
            unpauseB.setMousePressed(true);
        else
            audioOptions.mousePressed(e);
    }

    // Handle mouse released events
    public void mouseReleased(MouseEvent e) {
        if (isIn(e, menuB)) {
            if (menuB.isMousePressed()) {
                playing.resetAll();
                playing.setGamestate(Gamestate.MENU);
                playing.unpauseGame();
            }
        } else if (isIn(e, replayB)) {
            if (replayB.isMousePressed()) {
                playing.resetAll();
                playing.unpauseGame();
            }
        } else if (isIn(e, unpauseB)) {
            if (unpauseB.isMousePressed())
                playing.unpauseGame();
        } else
            audioOptions.mouseReleased(e);

        menuB.resetBools();
        replayB.resetBools();
        unpauseB.resetBools();
    }

    // Handle mouse moved events
    public void mouseMoved(MouseEvent e) {
        menuB.setMouseOver(false);
        replayB.setMouseOver(false);
        unpauseB.setMouseOver(false);

        if (isIn(e, menuB))
            menuB.setMouseOver(true);
        else if (isIn(e, replayB))
            replayB.setMouseOver(true);
        else if (isIn(e, unpauseB))
            unpauseB.setMouseOver(true);
        else
            audioOptions.mouseMoved(e);
    }

    // Check if the mouse event is within the bounds of a button
    private boolean isIn(MouseEvent e, PauseButton b) {
        return b.getBounds().contains(e.getX(), e.getY());
    }
}
