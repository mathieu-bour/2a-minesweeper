package fr.mathieubour.minesweeper.client;

import fr.mathieubour.minesweeper.client.ui.GameFrame;

import javax.swing.*;

public class Client {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        GameFrame frame = new GameFrame();
    }
}
