package fr.mathieubour.minesweeper.client.ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;

public class AssetsLoader {
    static HashMap<String, ImageIcon> images = new HashMap<>();
    private static String[] imagesNames = {
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
            "pristine.png"
    };

    static void preload() {
        String ASSETS_DIR = "../../assets";

        for (String image : imagesNames) {
            try {
                Image img = ImageIO.read(AssetsLoader.class.getResource("../../assets/" + image));
                Image scaledImage = img.getScaledInstance(TileButton.TILE_SIZE_PX, TileButton.TILE_SIZE_PX, java.awt.Image.SCALE_SMOOTH);
                images.put(image, new ImageIcon(scaledImage));
            } catch (IOException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }
}
