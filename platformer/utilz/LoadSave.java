package utilz;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;

public class LoadSave {

    // All images constant files (VERY EASY TO CHANGE / REMOVE)
    public static final String PLAYER_ATLAS = "player_sprites.png";
    public static final String LEVEL_ATLAS = "outside_sprites.png";
    public static final String MENU_BUTTONS = "button_atlas.png";
    public static final String MENU_BACKGROUND = "menu_background.png";
    public static final String PAUSE_BACKGROUND = "pause_menu.png";
    public static final String SOUND_BUTTONS = "sound_button.png";
    public static final String URM_BUTTONS = "urm_buttons.png";
    public static final String VOLUME_BUTTONS = "volume_buttons.png";
    public static final String MENU_BACKGROUND_IMG = "background_menu.png";
    public static final String PLAYING_BG_IMG = "playing_bg_img.png";
    public static final String BIG_CLOUDS = "big_clouds.png";
    public static final String SMALL_CLOUDS = "small_clouds.png";
    public static final String CRABBY_SPRITE = "crabby_sprite.png";
    public static final String STATUS_BAR = "health_power_bar.png";
    public static final String COMPLETED_IMG = "completed_sprite.png";
    public static final String POTION_ATLAS = "potions_sprites.png";
    public static final String CONTAINER_ATLAS = "objects_sprites.png";
    public static final String TRAP_ATLAS = "trap_atlas.png";
    public static final String CANNON_ATLAS = "cannon_atlas.png";
    public static final String CANNON_BALL = "ball.png";
    public static final String DEATH_SCREEN = "death_screen.png";
    public static final String OPTIONS_MENU = "options_background.png";
    public static final String PINKSTAR_ATLAS = "pinkstar_atlas.png";
    public static final String QUESTION_ATLAS = "question_atlas.png";
    public static final String EXCLAMATION_ATLAS = "exclamation_atlas.png";
    public static final String SHARK_ATLAS = "shark_atlas.png";
    public static final String CREDITS = "LandAck.png";
    public static final String GRASS_ATLAS = "grass_atlas.png";
    public static final String TREE_ONE_ATLAS = "tree_one_atlas.png";
    public static final String TREE_TWO_ATLAS = "tree_two_atlas.png";
    public static final String GAME_COMPLETED = "game_completed.png";
    public static final String RAIN_PARTICLE = "rain_particle.png";
    public static final String WATER_TOP = "water_atlas_animation.png";
    public static final String WATER_BOTTOM = "water.png";
    public static final String SHIP = "ship.png";
    public static final String GIIYOSE_LOGO = "Giiyose_Logo.png";

    // Very easy method implementation here, creating buffered images for other classes
    // To use: BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.FILENAME);
    public static BufferedImage GetSpriteAtlas(String fileName) {
        BufferedImage img = null;
        InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);
        try {
            img = ImageIO.read(is);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return img;
    }

    // Using insertion sort to go through each level, all levels are named 1.png, 2.png, etc.
    public static BufferedImage[] GetAllLevels() {
        URL url = LoadSave.class.getResource("/lvls");
        File file = null;

        try { // Try catch for getting file name to URL
            file = new File(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        File[] files = file.listFiles();
        insertionSort(files); // sort the files using insertion sort

        BufferedImage[] imgs = new BufferedImage[files.length];

        for (int i = 0; i < imgs.length; i++) {
            try {
                imgs[i] = ImageIO.read(files[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return imgs;
    }

    /**
     * Sorts an array of lvl files using the insertion sort algorithm.
     * The files are sorted based on the numeric value in their filenames.
     *
     * @param files the array of files to be sorted
     */
    private static void insertionSort(File[] files) {
        for (int i = 1; i < files.length; i++) {
            File key = files[i];
            int j = i - 1;

            // Move elements of files[0..i-1] that are greater than the key
            // to one position ahead of their current position
            while (j >= 0 && getNumericValue(files[j]) > getNumericValue(key)) {
                files[j + 1] = files[j];
                j = j - 1;
            }
            files[j + 1] = key;
        }
    }

    /**
     * Extracts the numeric value from a filename.
     * The filename is expected to be in the format "number.png".
     *
     * @param file the file whose numeric value is to be extracted
     * @return the numeric value extracted from the filename
     */
    private static int getNumericValue(File file) {
        String name = file.getName().replace(".png", "");
        return Integer.parseInt(name);
    }

}