package fr.mathieubour.minesweeper.client.ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;

/**
 * Load the images (the tiles textures) on start-up.
 */
public class AssetsLoader {
    private static AssetsLoader instance;

    /**
     * The images, indexed by their filename.
     */
    private final HashMap<String, ImageIcon> images = new HashMap<>();

    /**
     * The images filenames to load.
     */
    private final String[] imagesNames = {
        "0.png",
        "1.png",
        "2.png",
        "3.png",
        "4.png",
        "5.png",
        "6.png",
        "7.png",
        "8.png",
        "bomb.png",
        "pristine.png",
        "pristine-pressed.png",
        "loading.gif"
    };

    private AssetsLoader() {
        preload();
    }

    public static synchronized AssetsLoader getInstance() {
        if (instance == null) {
            instance = new AssetsLoader();
        }

        return instance;
    }

    /**
     * Load the images using ImageIO.
     *
     * @see ImageIO
     */
    private void preload() {
        for (String image : imagesNames) {
            try {
                Image img = ImageIO.read(AssetsLoader.class.getResource("../../assets/" + image));
                Image scaledImage = img.getScaledInstance(
                    TileButton.TILE_SIZE_PX,
                    TileButton.TILE_SIZE_PX,
                    java.awt.Image.SCALE_SMOOTH
                );

                images.put(image, new ImageIcon(scaledImage));
            } catch (IOException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    /**
     * Get an image by its name.
     *
     * @param key The image name
     * @return The image
     */
    public ImageIcon get(String key) {
        return images.getOrDefault(key, null);
    }
}
