package ui;

import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import static utilz.Constants.UI.PauseButtons.*;

public class SoundButton extends PauseButton {
    private BufferedImage[][] soundImgs; // Array to hold images for sound button
    private boolean mouseOver, mousePressed; // State variables for mouse interaction
    private boolean muted; // Flag to indicate whether sound is muted
    private int rowIndex, colIndex; // Indices to determine which image to display

    // Constructor to initialize the sound button
    public SoundButton(int x, int y, int width, int height) {
        super(x, y, width, height); // Call to superclass constructor (PauseButton)

        loadSoundImgs(); // Load images for the sound button
    }

    // Method to load images for the sound button
    private void loadSoundImgs() {
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.SOUND_BUTTONS); // Load sprite atlas for sound buttons
        soundImgs = new BufferedImage[2][3]; // Initialize the soundImgs array

        // Loop through and load each image from the sprite atlas
        for (int j = 0; j < soundImgs.length; j++) {
            for (int i = 0; i < soundImgs[j].length; i++) {
                soundImgs[j][i] = temp.getSubimage(i * SOUND_SIZE_DEFAULT, j * SOUND_SIZE_DEFAULT, SOUND_SIZE_DEFAULT, SOUND_SIZE_DEFAULT);
            }
        }
    }

    // Method to update the state of the sound button
    public void update() {
        // Determine the row index based on whether sound is muted or not
        if (muted)
            rowIndex = 1;
        else
            rowIndex = 0;

        // Set the column index based on mouse interaction
        colIndex = 0;
        if (mouseOver)
            colIndex = 1;
        if (mousePressed)
            colIndex = 2;
    }

    // Method to reset the mouse interaction state variables
    public void resetBools() {
        mouseOver = false;
        mousePressed = false;
    }

    // Method to draw the sound button
    public void draw(Graphics g) {
        g.drawImage(soundImgs[rowIndex][colIndex], x, y, width, height, null); // Draw the appropriate image
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

    // Getter for muted
    public boolean isMuted() {
        return muted;
    }

    // Setter for muted
    public void setMuted(boolean muted) {
        this.muted = muted;
    }
}
