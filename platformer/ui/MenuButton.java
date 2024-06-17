package ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import gamestates.Gamestate;
import utilz.LoadSave;
import static utilz.Constants.UI.Buttons.*;

public class MenuButton {
    private int xPos, yPos; // Button position
    private int rowIndex; // Row index for button image
    private int index; // Current button state index
    private int xOffsetCenter = B_WIDTH / 2; // Offset to center the button horizontally
    private Gamestate state; // Game state associated with the button
    private BufferedImage[] imgs; // Array of button images for different states
    private boolean mouseOver, mousePressed; // Mouse state flags
    private Rectangle bounds; // Button bounds for collision detection

    // Constructor initializes button position, row index, state, and loads images
    public MenuButton(int xPos, int yPos, int rowIndex, Gamestate state) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.rowIndex = rowIndex;
        this.state = state;
        loadImgs();
        initBounds();
    }

    // Method to initialize the button bounds
    private void initBounds() {
        bounds = new Rectangle(xPos - xOffsetCenter, yPos, B_WIDTH, B_HEIGHT); // Create rectangle for button bounds
    }

    // Method to load button images
    private void loadImgs() {
        imgs = new BufferedImage[3]; // Initialize image array with 3 states
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.MENU_BUTTONS); // Load the button sprite sheet
        for (int i = 0; i < imgs.length; i++) {
            imgs[i] = temp.getSubimage(i * B_WIDTH_DEFAULT, rowIndex * B_HEIGHT_DEFAULT, B_WIDTH_DEFAULT, B_HEIGHT_DEFAULT); // Extract button images from sprite sheet
        }
    }

    // Method to draw the button
    public void draw(Graphics g) {
        g.drawImage(imgs[index], xPos - xOffsetCenter, yPos, B_WIDTH, B_HEIGHT, null); // Draw the button image based on current state
    }

    // Method to update the button state based on mouse interaction
    public void update() {
        index = 0; // Default state
        if (mouseOver) {
            index = 1; // Mouse over state
        }
        if (mousePressed) {
            index = 2; // Mouse pressed state
        }
    }

    // Getter for mouse over state
    public boolean isMouseOver() {
        return mouseOver;
    }

    // Setter for mouse over state
    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    // Getter for mouse pressed state
    public boolean isMousePressed() {
        return mousePressed;
    }

    // Setter for mouse pressed state
    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    // Getter for button bounds
    public Rectangle getBounds() {
        return bounds;
    }

    // Method to apply the button's game state
    public void applyGamestate() {
        Gamestate.state = state; // Change the game state to the button's associated state
    }

    // Method to reset mouse state flags
    public void resetBools() {
        mouseOver = false; // Reset mouse over flag
        mousePressed = false; // Reset mouse pressed flag
    }

    // Getter for button's associated game state
    public Gamestate getState() {
        return state;
    }

}
