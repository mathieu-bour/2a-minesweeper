package fr.mathieubour.minesweeper.client.panels;

import fr.mathieubour.minesweeper.client.network.ClientSocketHandler;
import fr.mathieubour.minesweeper.client.states.PlayerState;
import fr.mathieubour.minesweeper.client.ui.AssetsLoader;
import fr.mathieubour.minesweeper.game.LevelDifficulty;
import fr.mathieubour.minesweeper.game.Player;
import fr.mathieubour.minesweeper.game.Vote;
import fr.mathieubour.minesweeper.packets.PlayerVotePacket;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

class VotePanel extends JPanel {
    private Player player;
    private Vote vote;

    /**
     * Holds the player name.
     */
    private JLabel nameLabel = new JLabel();
    /**
     * Used to display the color chooser.
     *
     * @see JColorChooser
     */
    private JButton colorSelectorButton = new JButton();
    /**
     * Holds the other player color.
     */
    private JLabel colorLabel = new JLabel();
    /**
     * Hold the player score.
     */
    private JLabel scoreLabel = new JLabel();
    private HashMap<LevelDifficulty, JButton> voteButtons = new HashMap<>();

    VotePanel(Player player) {
        super(new GridBagLayout());
        this.player = player;
        redraw();
    }

    private void redraw() {
        AtomicInteger y = new AtomicInteger(0);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(0, 0, 20, 0);
        constraints.gridx = 0;

        constraints.fill = GridBagConstraints.NONE;
        constraints.gridy = y.getAndIncrement();
        Dimension colorDimension = new Dimension(40, 40);

        if (isCurrentPlayer()) {
            // Draw the JButton
            colorSelectorButton.setPreferredSize(colorDimension);
            colorSelectorButton.setOpaque(true);
            colorSelectorButton.setBorderPainted(false);
            colorSelectorButton.setFocusPainted(false);
            colorSelectorButton.setBorder(null);
            colorSelectorButton.setFocusable(false);
            colorSelectorButton.setRolloverEnabled(false);
            colorSelectorButton.setBackground(player.getColor());
            colorSelectorButton.setForeground(player.getColor());

            if (!player.isAlive()) {
                colorSelectorButton.setIcon(AssetsLoader.getInstance().get("bomb.png"));
            } else {
                colorSelectorButton.setIcon(AssetsLoader.getInstance().get("0.png"));
            }

            colorSelectorButton.addActionListener(actionEvent -> {
                Color newColor = JColorChooser.showDialog(this, "Choose a new color", player.getColor());

                if (newColor == null) {
                    return;
                }

                player.setColor(newColor);
                colorSelectorButton.setBackground(player.getColor());
                colorSelectorButton.setForeground(player.getColor());

                // Send packet
            });
            add(colorSelectorButton, constraints);
        } else {
            colorLabel.setPreferredSize(colorDimension);
            colorLabel.setOpaque(true);
            colorLabel.setBackground(player.getColor());

            if (!player.isAlive()) {
                colorLabel.setIcon(AssetsLoader.getInstance().get("bomb.png"));
            } else {
                colorLabel.setIcon(AssetsLoader.getInstance().get("0.png"));
            }

            add(colorLabel, constraints);
        }

        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setText(player.getName());
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridy = y.getAndIncrement();
        add(nameLabel, constraints);

        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scoreLabel.setText(String.valueOf(player.getScore()));
        constraints.gridy = y.getAndIncrement();
        add(scoreLabel, constraints);

        constraints.insets = new Insets(0, 0, 5, 0);
        for (LevelDifficulty levelDifficulty : LevelDifficulty.values()) {
            JButton levelButton = new JButton();
            voteButtons.put(levelDifficulty, levelButton);

            constraints.gridy = y.getAndIncrement();
            levelButton.setEnabled(isCurrentPlayer());
            levelButton.setText(getName(levelDifficulty));

            if (isCurrentPlayer()) {
                levelButton.addActionListener(actionEvent -> setCurrentDifficulty(levelDifficulty));
            }

            add(levelButton, constraints);
        }
    }

    private boolean isCurrentPlayer() {
        String playerId = player.getId();
        String currentId = PlayerState.getInstance().getPlayer().getId();
        return playerId.equals(currentId);
    }

    private String getName(LevelDifficulty levelDifficulty) {
        switch (levelDifficulty) {
            case EASY:
                return "Easy";
            case MEDIUM:
                return "Medium";
            case HARD:
                return "Hard";
            default:
                return "Unknown";
        }
    }

    private void setCurrentDifficulty(LevelDifficulty difficulty) {
        voteButtons.forEach((difficulty2, button) -> {
            if (difficulty != difficulty2) {
                button.setEnabled(false);
            }
        });

        ClientSocketHandler.getInstance().send(new PlayerVotePacket(difficulty));
    }
}
