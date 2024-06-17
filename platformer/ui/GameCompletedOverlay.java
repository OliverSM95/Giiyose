package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import gamestates.Gamestate;
import gamestates.Playing;
import main.Game;
import utilz.LoadSave;

public class GameCompletedOverlay {

    private Playing playing; // Reference to the playing state
    private BufferedImage img; // Image for the game completed overlay
    private MenuButton quit, credit; // Buttons for quitting and showing credits
    private int imgX, imgY, imgW, imgH; // Image dimensions and position

    // Constructor initializes the playing state and creates the image and buttons
    public GameCompletedOverlay(Playing playing) {
        this.playing = playing;
        createImg();
        createButtons();
    }

    // Method to create buttons for quitting and showing credits
    private void createButtons() {
        quit = new MenuButton(Game.GAME_WIDTH / 2, (int) (270 * Game.SCALE), 2, Gamestate.MENU);
        credit = new MenuButton(Game.GAME_WIDTH / 2, (int) (200 * Game.SCALE), 3, Gamestate.CREDITS);
    }

    // Method to load and scale the game completed image
    private void createImg() {
        img = LoadSave.GetSpriteAtlas(LoadSave.GAME_COMPLETED);
        imgW = (int) (img.getWidth() * Game.SCALE);
        imgH = (int) (img.getHeight() * Game.SCALE);
        imgX = Game.GAME_WIDTH / 2 - imgW / 2;
        imgY = (int) (100 * Game.SCALE);
    }

    // Draw method to render the overlay and buttons
    public void draw(Graphics g) {
        // Draw a semi-transparent black background
        g.setColor(new Color(0, 0, 0, 200));
        g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);

        // Draw the game completed image
        g.drawImage(img, imgX, imgY, imgW, imgH, null);

        // Draw the buttons
        credit.draw(g);
        quit.draw(g);
    }

    // Update method to refresh button states
    public void update() {
        credit.update();
        quit.update();
    }

    // Helper method to check if the mouse event is within a button's bounds
    private boolean isIn(MenuButton b, MouseEvent e) {
        return b.getBounds().contains(e.getX(), e.getY());
    }

    // Handle mouse moved events for hover effects
    public void mouseMoved(MouseEvent e) {
        credit.setMouseOver(false);
        quit.setMouseOver(false);

        if (isIn(quit, e))
            quit.setMouseOver(true);
        else if (isIn(credit, e))
            credit.setMouseOver(true);
    }

    // Handle mouse released events to trigger button actions
    public void mouseReleased(MouseEvent e) {
        if (isIn(quit, e)) {
            if (quit.isMousePressed()) {
                playing.resetAll();
                playing.resetGameCompleted();
                playing.setGamestate(Gamestate.MENU); // Switch to menu state
            }
        } else if (isIn(credit, e)) {
            if (credit.isMousePressed()) {
                playing.resetAll();
                playing.resetGameCompleted();
                playing.setGamestate(Gamestate.CREDITS); // Switch to credits state
            }
        }

        // Reset button states
        quit.resetBools();
        credit.resetBools();
    }

    // Handle mouse pressed events to set button press states
    public void mousePressed(MouseEvent e) {
        if (isIn(quit, e))
            quit.setMousePressed(true);
        else if (isIn(credit, e))
            credit.setMousePressed(true);
    }
}
