package ui;

import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import static utilz.Constants.UI.URMButtons.*;

public class UrmButton extends PauseButton {
    private BufferedImage[] imgs; // Array to store button images
    private int rowIndex, index; // Index variables for selecting images
    private boolean mouseOver, mousePressed; // State variables for mouse interaction

    // Constructor to initialize the UrmButton
    public UrmButton(int x, int y, int width, int height, int rowIndex) {
        super(x, y, width, height); // Call to superclass constructor (PauseButton)
        this.rowIndex = rowIndex; // Initialize the row index for button images
        loadImgs(); // Load images for the button
    }

    // Method to load images for the button
    private void loadImgs() {
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.URM_BUTTONS); // Load sprite atlas for Urm buttons
        imgs = new BufferedImage[3]; // Initialize the imgs array

        // Loop through and load each image from the sprite atlas
        for (int i = 0; i < imgs.length; i++) {
            imgs[i] = temp.getSubimage(i * URM_DEFAULT_SIZE, rowIndex * URM_DEFAULT_SIZE, URM_DEFAULT_SIZE, URM_DEFAULT_SIZE);
        }
    }

    // Method to update the state of the button
    public void update() {
        index = 0; // Default to the first image
        if (mouseOver)
            index = 1; // Set to the second image if mouse is over
        if (mousePressed)
            index = 2; // Set to the third image if button is pressed
    }

    // Method to draw the button
    public void draw(Graphics g) {
        g.drawImage(imgs[index], x, y, URM_SIZE, URM_SIZE, null); // Draw the current button image
    }

    // Method to reset the mouse interaction state variables
    public void resetBools() {
        mouseOver = false;
        mousePressed = false;
    }

    // Getter for mouseOver
    public boolean isMouseOver() {
        return mouseOver;
    }

    // Setter for mouseOver
    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    // Getter for mousePressed
    public boolean isMousePressed() {
        return mousePressed;
    }

    // Setter for mousePressed
    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }
}
