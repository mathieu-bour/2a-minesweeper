package fr.mathieubour.minesweeper.client.panels;

import fr.mathieubour.minesweeper.client.states.GameState;
import fr.mathieubour.minesweeper.game.Player;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ScorePanel extends JPanel {
    private static ScorePanel instance;

    private ScorePanel() {
        super(new GridBagLayout());
        draw();
    }

    public static synchronized ScorePanel getInstance() {
        if (instance == null) {
            instance = new ScorePanel();
        }

        return instance;
    }

    void draw() {
        HashMap<String, Player> players = GameState.getInstance().getPlayers();

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        AtomicInteger y = new AtomicInteger();

        players.forEach((key, player) -> {
            JLabel username = new JLabel(player.getName());
            constraints.gridx = 0;
            constraints.gridy = y.get();
            add(username, constraints);

            JLabel score = new JLabel(Integer.toString(player.getScore()));
            constraints.gridx = 1;
            add(score, constraints);
            y.getAndIncrement();
        });
    }
}
