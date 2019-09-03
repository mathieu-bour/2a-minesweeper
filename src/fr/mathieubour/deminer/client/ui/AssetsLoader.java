package fr.mathieubour.deminer.client.ui;

import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.Objects;

public class AssetsLoader {
    private static String ASSETS_DIR = "../";
    private static HashMap<String, Image> imageMap = new HashMap<>();

    public static void preload() {
        File actual = new File(String.valueOf(AssetsLoader.class.getResource(ASSETS_DIR)));
        String[] list = Objects.requireNonNull(actual.list());

        for (String f : list) {
            System.out.println(f);
        }
    }
}
