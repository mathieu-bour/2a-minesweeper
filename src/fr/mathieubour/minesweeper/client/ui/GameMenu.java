package fr.mathieubour.minesweeper.client.ui;

import fr.mathieubour.minesweeper.client.panels.FieldPanel;
import fr.mathieubour.minesweeper.client.panels.LoginPanel;

import javax.swing.*;

/**
 * The main menu.
 */
class GameMenu extends JMenuBar {
    private static GameMenu instance;

    GameMenu() {
        // Add menu items to their parent menu

        // Server
        JMenu serverMenu = new JMenu("Server");
        JMenuItem connectMenuItem = new JMenuItem("Connect");
        serverMenu.add(connectMenuItem);
        JMenuItem disconnectMenuItem = new JMenuItem("Disconnect");
        serverMenu.add(disconnectMenuItem);

        // Debug
        JMenu debugMenu = new JMenu("Debug");
        JMenuItem loginItemMenu = new JMenuItem("Login");
        JMenuItem gameItemMenu = new JMenuItem("Game");

        debugMenu.add(loginItemMenu);
        debugMenu.add(gameItemMenu);

        // Add menus to the menu bar
        add(serverMenu);
        add(debugMenu);

        // Add actions
        GameFrame gameFrame = GameFrame.getInstance();
        loginItemMenu.addActionListener(actionEvent -> {
            gameFrame.setContentPane(LoginPanel.getInstance());
            gameFrame.revalidate();
        });
        gameItemMenu.addActionListener(actionEvent -> {
            gameFrame.setContentPane(FieldPanel.getInstance());
            gameFrame.revalidate();
        });
    }
}
