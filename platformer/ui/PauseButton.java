package ui;

import java.awt.*;

public class PauseButton {

    protected int x, y, width, height; // Position and dimensions of the button
    protected Rectangle bounds; // Button bounds for collision detection

    // Constructor to initialize button position and dimensions
    public PauseButton(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        createBounds(); // Create bounds for the button
    }

    // Method to create the rectangular bounds of the button
    private void createBounds() {
        bounds = new Rectangle(x, y, width, height); // Initialize bounds based on position and dimensions
    }

    // Getter for x position
    public int getX() {
        return x;
    }

    // Setter for x position
    public void setX(int x) {
        this.x = x;
    }

    // Getter for y position
    public int getY() {
        return y;
    }

    // Setter for y position
    public void setY(int y) {
        this.y = y;
    }

    // Getter for width
    public int getWidth() {
        return width;
    }

    // Setter for width
    public void setWidth(int width) {
        this.width = width;
    }

    // Getter for height
    public int getHeight() {
        return height;
    }

    // Setter for height
    public void setHeight(int height) {
        this.height = height;
    }

    // Getter for button bounds
    public Rectangle getBounds() {
        return bounds;
    }

    // Setter for button bounds
    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }
}
