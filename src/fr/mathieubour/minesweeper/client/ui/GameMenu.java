package fr.mathieubour.minesweeper.client.ui;

import fr.mathieubour.minesweeper.client.panels.FieldPanel;
import fr.mathieubour.minesweeper.client.panels.LoginPanel;
import fr.mathieubour.minesweeper.client.panels.WaitingPanel;
import fr.mathieubour.minesweeper.client.states.GameState;
import fr.mathieubour.minesweeper.client.states.PlayerState;
import fr.mathieubour.minesweeper.game.Player;

import javax.swing.*;
import java.util.HashMap;

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
        JMenuItem waitingItemMenu = new JMenuItem("Waiting");
        JMenuItem gameItemMenu = new JMenuItem("Game");

        debugMenu.add(loginItemMenu);
        debugMenu.add(waitingItemMenu);
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
        waitingItemMenu.addActionListener(actionEvent -> {
            HashMap<String, Player> p = new HashMap<>();

            Player p1 = new Player("P1");
            Player p2 = new Player("P2");
            Player p3 = new Player("P3");
            Player p4 = new Player("P4");

            p.put("1", p1);
            p.put("2", p2);
            p.put("3", p3);
            p.put("4", p4);

            PlayerState.getInstance().setPlayer(p1);
            GameState.getInstance().setPlayers(p);
            gameFrame.setContentPane(WaitingPanel.getInstance());
            gameFrame.revalidate();
        });
        gameItemMenu.addActionListener(actionEvent -> {
            gameFrame.setContentPane(FieldPanel.getInstance());
            gameFrame.revalidate();
        });
    }
}
