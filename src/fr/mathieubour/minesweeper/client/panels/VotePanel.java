package fr.mathieubour.minesweeper.client.panels;

import fr.mathieubour.minesweeper.client.network.ClientSocketHandler;
import fr.mathieubour.minesweeper.client.states.PlayerState;
import fr.mathieubour.minesweeper.client.ui.AssetsLoader;
import fr.mathieubour.minesweeper.game.LevelDifficulty;
import fr.mathieubour.minesweeper.game.Player;
import fr.mathieubour.minesweeper.packets.PlayerColorPacket;
import fr.mathieubour.minesweeper.packets.PlayerVotePacket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Display the user profile and allow him to:
 * 1. Change his color;
 * 2. vote for the next game difficulty.
 */
class VotePanel extends JPanel {
    /**
     * The player associated with the vote panel.
     */
    private Player player;
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
    private ConcurrentHashMap<LevelDifficulty, JButton> voteButtons = new ConcurrentHashMap<>();

    VotePanel(Player player) {
        super(new GridBagLayout());
        this.player = player;
        redraw();
    }

    /**
     * Redraw the vote panel.
     */
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
            colorSelectorButton.setIcon(getIcon());

            colorSelectorButton.addActionListener(this::openColorChooser);
            add(colorSelectorButton, constraints);
        } else {
            // Draw the JLabel
            colorLabel.setPreferredSize(colorDimension);
            colorLabel.setOpaque(true);
            colorLabel.setBackground(player.getColor());
            colorLabel.setIcon(getIcon());

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

    /**
     * Get the icon to display on the user color (a mine or a transparent tile).
     *
     * @return The ImageIcon object.
     * @see AssetsLoader
     */
    private ImageIcon getIcon() {
        if (!player.isAlive()) {
            return AssetsLoader.getInstance().get("bomb.png");
        } else {
            return AssetsLoader.getInstance().get("0.png");
        }
    }

    /**
     * Handle click on the color chooser and send the new color to the server if necessary.
     *
     * @param actionEvent The event.
     */
    private void openColorChooser(ActionEvent actionEvent) {
        Color newColor = JColorChooser.showDialog(this, "Choose a new color", player.getColor());

        if (newColor == null) {
            return;
        }

        player.setColor(newColor);
        colorSelectorButton.setBackground(player.getColor());
        colorSelectorButton.setForeground(player.getColor());
        ClientSocketHandler.getInstance().send(new PlayerColorPacket(player));
    }

    /**
     * @return if the panel user is the current user.
     */
    private boolean isCurrentPlayer() {
        String playerId = player.getId();
        String currentId = PlayerState.getInstance().getPlayer().getId();
        return playerId.equals(currentId);
    }

    /**
     * Translate the difficulty name to an human-readable string.
     *
     * @param levelDifficulty The level difficulty.
     * @return The level difficulty name.
     * @see LevelDifficulty
     */
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

    /**
     * Set the current user difficulty, and send it to the server.
     *
     * @param difficulty The level difficulty.
     */
    private void setCurrentDifficulty(LevelDifficulty difficulty) {
        voteButtons.forEach((difficulty2, button) -> {
            if (difficulty != difficulty2) {
                button.setEnabled(false);
            }
        });

        ClientSocketHandler.getInstance().send(new PlayerVotePacket(difficulty));
    }
}
