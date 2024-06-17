package ui;

import static utilz.Constants.UI.URMButtons.URM_SIZE;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import gamestates.Gamestate;
import gamestates.Playing;
import main.Game;
import utilz.LoadSave;

public class GameOverOverlay {

    private Playing playing; // Reference to the playing state
    private BufferedImage img; // Image for the game over screen
    private int imgX, imgY, imgW, imgH; // Image dimensions and position
    private UrmButton menu, play; // Buttons for menu and play actions

    // Constructor initializes the playing state and creates the image and buttons
    public GameOverOverlay(Playing playing) {
        this.playing = playing;
        createImg();
        createButtons();
    }

    // Method to create buttons for menu and replay
    private void createButtons() {
        int menuX = (int) (335 * Game.SCALE); // X position for the menu button
        int playX = (int) (440 * Game.SCALE); // X position for the play button
        int y = (int) (195 * Game.SCALE); // Y position for both buttons
        play = new UrmButton(playX, y, URM_SIZE, URM_SIZE, 0);
        menu = new UrmButton(menuX, y, URM_SIZE, URM_SIZE, 2);
    }

    // Method to load and scale the game over image
    private void createImg() {
        img = LoadSave.GetSpriteAtlas(LoadSave.DEATH_SCREEN);
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

        // Draw the game over image
        g.drawImage(img, imgX, imgY, imgW, imgH, null);

        // Draw the buttons
        menu.draw(g);
        play.draw(g);
    }

    // Update method to refresh button states
    public void update() {
        menu.update();
        play.update();
    }

    // Helper method to check if the mouse event is within a button's bounds
    private boolean isIn(UrmButton b, MouseEvent e) {
        return b.getBounds().contains(e.getX(), e.getY());
    }

    // Handle mouse moved events for hover effects
    public void mouseMoved(MouseEvent e) {
        play.setMouseOver(false);
        menu.setMouseOver(false);

        if (isIn(menu, e)) {
            menu.setMouseOver(true);
        } else if (isIn(play, e)) {
            play.setMouseOver(true);
        }
    }

    // Handle mouse released events to trigger button actions
    public void mouseReleased(MouseEvent e) {
        if (isIn(menu, e)) {
            if (menu.isMousePressed()) {
                playing.resetAll();
                playing.setGamestate(Gamestate.MENU); // Switch to menu state
            }
        } else if (isIn(play, e)) {
            if (play.isMousePressed()) {
                playing.resetAll();
                playing.getGame().getAudioPlayer().setLevelSong(playing.getLevelManager().getLevelIndex()); // Reset and play level song
            }
        }

        // Reset button states
        menu.resetBools();
        play.resetBools();
    }

    // Handle mouse pressed events to set button press states
    public void mousePressed(MouseEvent e) {
        if (isIn(menu, e)) {
            menu.setMousePressed(true);
        } else if (isIn(play, e)) {
            play.setMousePressed(true);
        }
    }

}
