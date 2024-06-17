package ui;

import static utilz.Constants.UI.PauseButtons.SOUND_SIZE;
import static utilz.Constants.UI.VolumeButtons.SLIDER_WIDTH;
import static utilz.Constants.UI.VolumeButtons.VOLUME_HEIGHT;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import main.Game;

public class AudioOptions {

    // Buttons for controlling volume and sound settings
    private VolumeButton volumeButton;
    private SoundButton musicButton, sfxButton;

    private Game game;

    // Constructor initializes the game instance and creates buttons
    public AudioOptions(Game game) {
        this.game = game;
        createSoundButtons();
        createVolumeButton();
    }

    // Method to create the volume slider button
    private void createVolumeButton() {
        int vX = (int) (309 * Game.SCALE); // X position of the volume button
        int vY = (int) (278 * Game.SCALE); // Y position of the volume button
        volumeButton = new VolumeButton(vX, vY, SLIDER_WIDTH, VOLUME_HEIGHT);
    }

    // Method to create sound buttons for music and SFX
    private void createSoundButtons() {
        int soundX = (int) (450 * Game.SCALE); // X position of the sound buttons
        int musicY = (int) (140 * Game.SCALE); // Y position of the music button
        int sfxY = (int) (186 * Game.SCALE);   // Y position of the SFX button
        musicButton = new SoundButton(soundX, musicY, SOUND_SIZE, SOUND_SIZE);
        sfxButton = new SoundButton(soundX, sfxY, SOUND_SIZE, SOUND_SIZE);
    }

    // Update method to refresh button states
    public void update() {
        musicButton.update();
        sfxButton.update();
        volumeButton.update();
    }

    // Draw method to render buttons on the screen
    public void draw(Graphics g) {
        // Draw sound buttons
        musicButton.draw(g);
        sfxButton.draw(g);
        // Draw volume button
        volumeButton.draw(g);
    }

    // Handle mouse dragged events for the volume slider
    public void mouseDragged(MouseEvent e) {
        if (volumeButton.isMousePressed()) {
            float valueBefore = volumeButton.getFloatValue();
            volumeButton.changeX(e.getX());
            float valueAfter = volumeButton.getFloatValue();
            if (valueBefore != valueAfter) {
                game.getAudioPlayer().setVolume(valueAfter); // Adjust game volume
            }
        }
    }

    // Handle mouse pressed events for buttons
    public void mousePressed(MouseEvent e) {
        if (isIn(e, musicButton)) {
            musicButton.setMousePressed(true);
        } else if (isIn(e, sfxButton)) {
            sfxButton.setMousePressed(true);
        } else if (isIn(e, volumeButton)) {
            volumeButton.setMousePressed(true);
        }
    }

    // Handle mouse released events for buttons
    public void mouseReleased(MouseEvent e) {
        if (isIn(e, musicButton)) {
            if (musicButton.isMousePressed()) {
                musicButton.setMuted(!musicButton.isMuted());
                game.getAudioPlayer().toggleSongMute(); // Toggle music mute
            }
        } else if (isIn(e, sfxButton)) {
            if (sfxButton.isMousePressed()) {
                sfxButton.setMuted(!sfxButton.isMuted());
                game.getAudioPlayer().toggleEffectMute(); // Toggle SFX mute
            }
        }

        // Reset button states
        musicButton.resetBools();
        sfxButton.resetBools();
        volumeButton.resetBools();
    }

    // Handle mouse moved events for hover effects
    public void mouseMoved(MouseEvent e) {
        musicButton.setMouseOver(false);
        sfxButton.setMouseOver(false);
        volumeButton.setMouseOver(false);

        if (isIn(e, musicButton)) {
            musicButton.setMouseOver(true);
        } else if (isIn(e, sfxButton)) {
            sfxButton.setMouseOver(true);
        } else if (isIn(e, volumeButton)) {
            volumeButton.setMouseOver(true);
        }
    }

    // Helper method to check if the mouse event is within a button's bounds
    private boolean isIn(MouseEvent e, PauseButton b) {
        return b.getBounds().contains(e.getX(), e.getY());
    }

}
