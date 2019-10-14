package fr.mathieubour.minesweeper.client.panels;

import fr.mathieubour.minesweeper.client.ui.AssetsLoader;
import fr.mathieubour.minesweeper.client.ui.TileButton;
import fr.mathieubour.minesweeper.game.Player;

import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel {
    public static final int WIDTH = 150;

    private final JLabel colorLabel = new JLabel();
    private final JLabel nameLabel = new JLabel();
    private final JLabel scoreLabel = new JLabel();

    private ScorePanel() {
        super(new GridBagLayout());
        draw();
    }

    private ScorePanel(Color color, boolean isAlive, String name, int score) {
        this();
        setColor(color);
        setAlive(isAlive);
        setPlayerName(name);
        setPlayerScore(score);
    }

    ScorePanel(Player player) {
        this(player.getColor(), player.isAlive(), player.getName(), player.getScore());
    }

    private void draw() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(0, 5, 5, 5);

        constraints.gridy = 0;
        constraints.gridx = 0;

        colorLabel.setPreferredSize(new Dimension(TileButton.TILE_SIZE_PX, TileButton.TILE_SIZE_PX));
        colorLabel.setOpaque(true);

        add(colorLabel, constraints);

        constraints.gridx++;
        add(nameLabel, constraints);

        constraints.gridx++;
        add(scoreLabel, constraints);
    }

    private void setColor(Color color) {
        colorLabel.setBackground(color);
    }

    public void setAlive(boolean isAlive) {
        if (!isAlive) {
            colorLabel.setIcon(AssetsLoader.getInstance().get("bomb.png"));
        } else {
            colorLabel.setIcon(null);
        }
    }

    private void setPlayerName(String name) {
        nameLabel.setText(name);
    }

    public void setPlayerScore(int score) {
        scoreLabel.setText(Integer.toString(score));
    }
}
