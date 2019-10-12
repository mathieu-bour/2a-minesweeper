package fr.mathieubour.minesweeper.client.ui;

import fr.mathieubour.minesweeper.client.panels.WaitingPanel;
import fr.mathieubour.minesweeper.client.states.GameState;
import fr.mathieubour.minesweeper.client.states.PlayerState;
import fr.mathieubour.minesweeper.game.Player;

import javax.swing.*;
import java.util.HashMap;

public class GameFrame extends JFrame {
    private static GameFrame instance;

    private JPanel panel;

    public GameFrame() {
        super("Minesweeper - Home");

        GameFrame.instance = this;

        // Preload icons
        configure();
        setJMenuBar(new GameMenu());

        init();
        setContentPane(WaitingPanel.getInstance());
        setVisible(true);
    }

    void init() {
        HashMap<String, Player> p = new HashMap<>();

        Player p1 = new Player("Player 1");
        Player p2 = new Player("Player 2");
        Player p3 = new Player("Player 3");
        Player p4 = new Player("Player 4");

        p.put("1", p1);
        p.put("2", p2);
        p.put("3", p3);
        p.put("4", p4);

        PlayerState.getInstance().setPlayer(p1);
        GameState.getInstance().setPlayers(p);
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
