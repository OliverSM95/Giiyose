package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import gamestates.Gamestate;
import gamestates.Playing;
import main.Game;
import utilz.LoadSave;
import static utilz.Constants.UI.URMButtons.*;

public class LevelCompletedOverlay {

    private Playing playing; // Reference to the playing state
    private UrmButton menu, next; // Buttons for menu and next level
    private BufferedImage img; // Image for the level completed screen
    private int bgX, bgY, bgW, bgH; // Background image dimensions and position

    // Constructor initializes the playing state and creates the image and buttons
    public LevelCompletedOverlay(Playing playing) {
        this.playing = playing;
        initImg();
        initButtons();
    }

    // Method to initialize the menu and next buttons
    private void initButtons() {
        int menuX = (int) (330 * Game.SCALE); // X position for the menu button
        int nextX = (int) (445 * Game.SCALE); // X position for the next button
        int y = (int) (195 * Game.SCALE); // Y position for both buttons
        next = new UrmButton(nextX, y, URM_SIZE, URM_SIZE, 0); // Initialize the next button
        menu = new UrmButton(menuX, y, URM_SIZE, URM_SIZE, 2); // Initialize the menu button
    }

    // Method to load and scale the level completed image
    private void initImg() {
        img = LoadSave.GetSpriteAtlas(LoadSave.COMPLETED_IMG); // Load the level completed image
        bgW = (int) (img.getWidth() * Game.SCALE); // Scale the width of the image
        bgH = (int) (img.getHeight() * Game.SCALE); // Scale the height of the image
        bgX = Game.GAME_WIDTH / 2 - bgW / 2; // Center the image horizontally
        bgY = (int) (75 * Game.SCALE); // Set the vertical position of the image
    }

    // Draw method to render the overlay and buttons
    public void draw(Graphics g) {
        // Draw a semi-transparent black background
        g.setColor(new Color(0, 0, 0, 200));
        g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);

        // Draw the level completed image
        g.drawImage(img, bgX, bgY, bgW, bgH, null);

        // Draw the buttons
        next.draw(g);
        menu.draw(g);
    }

    // Update method to refresh button states
    public void update() {
        next.update();
        menu.update();
    }

    // Helper method to check if the mouse event is within a button's bounds
    private boolean isIn(UrmButton b, MouseEvent e) {
        return b.getBounds().contains(e.getX(), e.getY());
    }

    // Handle mouse moved events for hover effects
    public void mouseMoved(MouseEvent e) {
        next.setMouseOver(false);
        menu.setMouseOver(false);

        if (isIn(menu, e)) {
            menu.setMouseOver(true);
        } else if (isIn(next, e)) {
            next.setMouseOver(true);
        }
    }

    // Handle mouse released events to trigger button actions
    public void mouseReleased(MouseEvent e) {
        if (isIn(menu, e)) {
            if (menu.isMousePressed()) {
                playing.resetAll(); // Reset the game state
                playing.setGamestate(Gamestate.MENU); // Switch to menu state
            }
        } else if (isIn(next, e)) {
            if (next.isMousePressed()) {
                playing.loadNextLevel(); // Load the next level
                playing.getGame().getAudioPlayer().setLevelSong(playing.getLevelManager().getLevelIndex()); // Play the song for the next level
            }
        }

        // Reset button states
        menu.resetBools();
        next.resetBools();
    }

    // Handle mouse pressed events to set button press states
    public void mousePressed(MouseEvent e) {
        if (isIn(menu, e)) {
            menu.setMousePressed(true);
        } else if (isIn(next, e)) {
            next.setMousePressed(true);
        }
    }

}
