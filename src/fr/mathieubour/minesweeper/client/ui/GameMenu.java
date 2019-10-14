package fr.mathieubour.minesweeper.client.ui;

import fr.mathieubour.minesweeper.client.Client;
import fr.mathieubour.minesweeper.client.panels.ChatPanel;
import fr.mathieubour.minesweeper.client.panels.FieldPanel;
import fr.mathieubour.minesweeper.client.panels.LoginPanel;
import fr.mathieubour.minesweeper.client.panels.ScoreboardPanel;
import fr.mathieubour.minesweeper.client.states.ClientGameState;
import fr.mathieubour.minesweeper.client.states.PlayerState;
import fr.mathieubour.minesweeper.client.views.WaitingView;
import fr.mathieubour.minesweeper.game.Player;

import javax.swing.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The main menu.
 */
public class GameMenu extends JMenuBar {
    private static GameMenu instance;

    public GameMenu() {
        // Debug
        JMenu debugMenu = new JMenu("Debug");
        JMenuItem loginItemMenu = new JMenuItem("Login");
        JMenuItem waitingItemMenu = new JMenuItem("Waiting");
        JMenuItem chatItemMenu = new JMenuItem("Chat");
        JMenuItem scoreboardItemMenu = new JMenuItem("Scoreboard");
        JMenuItem gameItemMenu = new JMenuItem("Game");

        debugMenu.add(loginItemMenu);
        debugMenu.add(waitingItemMenu);
        debugMenu.add(chatItemMenu);
        debugMenu.add(scoreboardItemMenu);
        debugMenu.add(gameItemMenu);

        // Add menus to the menu bar
        add(debugMenu);

        // Add actions
        Client client = Client.getInstance();
        loginItemMenu.addActionListener(actionEvent -> {
            client.setContentPane(LoginPanel.getInstance());
            client.revalidate();
        });
        waitingItemMenu.addActionListener(actionEvent -> {
            ConcurrentHashMap<String, Player> p = new ConcurrentHashMap<>();

            Player p1 = new Player("P1");
            p1.setId("1");
            p1.setScore(15);
            Player p2 = new Player("P2");
            p2.setId("2");
            p2.setScore(25);
            p2.setAlive(false);
            Player p3 = new Player("P3");
            p3.setId("3");
            p2.setScore(2);
            Player p4 = new Player("P4");
            p4.setId("4");
            p2.setScore(36);

            p.put("1", p1);
            p.put("2", p2);
            p.put("3", p3);
            p.put("4", p4);

            ChatPanel.getInstance().appendMessage("Hello");
            ChatPanel.getInstance().appendMessage("Hello 2");
            ChatPanel.getInstance().appendMessage("Hello 3");
            ChatPanel.getInstance().appendMessage("Hello 4");
            ChatPanel.getInstance().appendMessage("Hello 5");

            PlayerState.getInstance().setPlayer(p1);
            ClientGameState.getInstance().setPlayers(p);
            client.setContentPane(WaitingView.getInstance());
            client.revalidate();
        });
        chatItemMenu.addActionListener(actionEvent -> {
            client.setContentPane(ChatPanel.getInstance());
            client.revalidate();
        });
        scoreboardItemMenu.addActionListener(actionEvent -> {
            ConcurrentHashMap<String, Player> p = new ConcurrentHashMap<>();

            Player p1 = new Player("P1");
            Player p2 = new Player("P2");
            Player p3 = new Player("P3");
            Player p4 = new Player("P4");
            p1.setScore(15);
            p2.setScore(25);
            p2.setAlive(false);

            p.put("1", p1);
            p.put("2", p2);
            p.put("3", p3);
            p.put("4", p4);

            PlayerState.getInstance().setPlayer(p1);
            ClientGameState.getInstance().setPlayers(p);
            client.setContentPane(ScoreboardPanel.getInstance());
            client.revalidate();
        });
        gameItemMenu.addActionListener(actionEvent -> {
            client.setContentPane(FieldPanel.getInstance());
            client.revalidate();
        });
    }
}
