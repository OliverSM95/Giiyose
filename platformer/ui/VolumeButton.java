package ui;

import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import static utilz.Constants.UI.VolumeButtons.*;

public class VolumeButton extends PauseButton {
    private BufferedImage[] imgs; // Array to store button images
    private BufferedImage slider; // Image for the volume slider
    private int index = 0; // Index for selecting button image
    private boolean mouseOver, mousePressed; // State variables for mouse interaction
    private int buttonX, minX, maxX; // X positions for button and slider movement
    private float floatValue = 0f; // Float value representing the volume

    // Constructor to initialize the VolumeButton
    public VolumeButton(int x, int y, int width, int height) {
        super(x + width / 2, y, VOLUME_WIDTH, height); // Call to superclass constructor (PauseButton)
        bounds.x -= VOLUME_WIDTH / 2; // Adjust the bounds of the button
        buttonX = x + width / 2; // Initialize the buttonX position
        this.x = x; // Initialize the x position of the button
        this.width = width; // Initialize the width of the button
        minX = x + VOLUME_WIDTH / 2; // Initialize the minimum X position for the button
        maxX = x + width - VOLUME_WIDTH / 2; // Initialize the maximum X position for the button
        loadImgs(); // Load images for the button
    }

    // Method to load images for the button and slider
    private void loadImgs() {
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.VOLUME_BUTTONS); // Load sprite atlas for volume buttons
        imgs = new BufferedImage[3]; // Initialize the imgs array for button images
        for (int i = 0; i < imgs.length; i++) {
            imgs[i] = temp.getSubimage(i * VOLUME_DEFAULT_WIDTH, 0, VOLUME_DEFAULT_WIDTH, VOLUME_DEFAULT_HEIGHT);
        }
        slider = temp.getSubimage(3 * VOLUME_DEFAULT_WIDTH, 0, SLIDER_DEFAULT_WIDTH, VOLUME_DEFAULT_HEIGHT); // Load slider image
    }

    // Method to update the state of the button
    public void update() {
        index = 0; // Default to the first image
        if (mouseOver)
            index = 1; // Set to the second image if mouse is over
        if (mousePressed)
            index = 2; // Set to the third image if button is pressed
    }

    // Method to draw the button and slider
    public void draw(Graphics g) {
        g.drawImage(slider, x, y, width, height, null); // Draw the slider image
        g.drawImage(imgs[index], buttonX - VOLUME_WIDTH / 2, y, VOLUME_WIDTH, height, null); // Draw the button image
    }

    // Method to change the X position of the button (slider position)
    public void changeX(int x) {
        if (x < minX)
            buttonX = minX; // Ensure the button stays within the minimum X position
        else if (x > maxX)
            buttonX = maxX; // Ensure the button stays within the maximum X position
        else
            buttonX = x; // Set the buttonX to the new X position

        updateFloatValue(); // Update the float value based on the new buttonX position
        bounds.x = buttonX - VOLUME_WIDTH / 2; // Update the bounds of the button
    }

    // Method to update the float value based on the buttonX position
    private void updateFloatValue() {
        float range = maxX - minX; // Calculate the range of the slider movement
        float value = buttonX - minX; // Calculate the value of the slider position
        floatValue = value / range; // Calculate the float value as a percentage of the range
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

    // Getter for floatValue
    public float getFloatValue() {
        return floatValue;
    }
}
