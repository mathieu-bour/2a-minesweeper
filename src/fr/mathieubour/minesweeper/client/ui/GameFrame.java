package fr.mathieubour.minesweeper.client.ui;

import fr.mathieubour.minesweeper.client.panels.LoginPanel;

import javax.swing.*;

public class GameFrame extends JFrame {
    private static GameFrame instance;

    private JPanel panel;

    public GameFrame() {
        super("Minesweeper - Home");

        GameFrame.instance = this;

        // Preload icons
        AssetsLoader.preload();
        configure();
        setJMenuBar(new GameMenu());

        setContentPane(LoginPanel.getInstance());
        setVisible(true);
    }

    public static synchronized GameFrame getInstance() {
        if (instance == null) {
            instance = new GameFrame();
        }

        return instance;
    }

    private void configure() {
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }
}
