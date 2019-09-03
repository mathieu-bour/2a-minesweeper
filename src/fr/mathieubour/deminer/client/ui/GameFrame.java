package fr.mathieubour.deminer.client.ui;

import javax.swing.*;

public class GameFrame extends JFrame {
    static GameFrame ref;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem newGameMenuItem;
    private JMenuItem quitItemMenu;

    private JPanel panel;

    public void init() {
        GameFrame.ref = this;
        configure();
        makeMenuBar();

        JPanel panel = new FieldPanel();
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

    private void makeMenuBar() {
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        newGameMenuItem = new JMenuItem("New game");
        quitItemMenu = new JMenuItem("Exit");

        fileMenu.add(newGameMenuItem);
        fileMenu.add(quitItemMenu);
        menuBar.add(fileMenu);

        setJMenuBar(menuBar);
    }
}
