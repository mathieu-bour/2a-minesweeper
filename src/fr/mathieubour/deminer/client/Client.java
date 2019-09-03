package fr.mathieubour.deminer.client;

import fr.mathieubour.deminer.client.ui.AssetsLoader;
import fr.mathieubour.deminer.client.ui.GameFrame;

import javax.swing.*;

public class Client {
    public static void main(String[] args) {
        // GameFrame frame = new GameFrame();
        // frame.init();
        AssetsLoader.preload();
    }
}
