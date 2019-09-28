package fr.mathieubour.minesweeper.client.ui;

import fr.mathieubour.minesweeper.client.panels.HomePanel;

import javax.swing.*;

public class GameFrame extends JFrame {
    public static GameFrame instance;

    private JPanel panel;

    public void init() {
        GameFrame.instance = this;

        // Preload icons
        AssetsLoader.preload();
        configure();
        setJMenuBar(new GameMenu());

        JPanel panel = new HomePanel();
        setContentPane(panel);
        setVisible(true);
    }

    private void configure() {
        setTitle("Minesweeper - Home");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }
}
