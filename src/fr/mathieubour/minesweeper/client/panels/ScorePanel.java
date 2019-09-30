package fr.mathieubour.minesweeper.client.panels;

import fr.mathieubour.minesweeper.game.Player;

import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel {
    private JLabel colorLabel = new JLabel();
    private JLabel nameLabel = new JLabel();
    private JLabel scoreLabel = new JLabel();

    ScorePanel() {
        super(new GridBagLayout());
        draw();
    }

    ScorePanel(Color color, String name, int score) {
        this();
        setColor(color);
        setPlayerName(name);
        setPlayerScore(score);
    }

    ScorePanel(Player player) {
        this(player.getColor(), player.getName(), player.getScore());
    }

    void draw() {
        Dimension dimension = new Dimension(15, 15);
        colorLabel.setMinimumSize(dimension);
        colorLabel.setMaximumSize(dimension);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.gridy = 0;
        constraints.gridx = 0;

        add(colorLabel, constraints);

        constraints.gridx++;
        add(nameLabel, constraints);

        constraints.gridx++;
        add(scoreLabel, constraints);
    }

    void setColor(Color color) {
        colorLabel.setBackground(color);
    }

    void setPlayerName(String name) {
        nameLabel.setText(name);
    }

    void setPlayerScore(int score) {
        scoreLabel.setText(Integer.toString(score));
    }
}
