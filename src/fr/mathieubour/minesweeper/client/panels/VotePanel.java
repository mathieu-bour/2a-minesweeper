package fr.mathieubour.minesweeper.client.panels;

import fr.mathieubour.minesweeper.client.ui.AssetsLoader;
import fr.mathieubour.minesweeper.game.LevelDifficulty;
import fr.mathieubour.minesweeper.game.Player;
import fr.mathieubour.minesweeper.game.Vote;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class VotePanel extends JPanel implements ActionListener {
    private Player player;
    private boolean voteEnabled;
    private Vote vote;

    VotePanel(Player player, boolean voteEnabled) {
        super(new GridBagLayout());

        this.player = player;
        this.voteEnabled = voteEnabled;

        JLabel nameLabel = new JLabel(player.getName());
        JLabel loadingLabel = new JLabel(AssetsLoader.getInstance().get("loading.gif"));

        /*
        if (voteEnabled) {
            JButton[] levelButtons;

            for (LevelDifficulty levelDifficulty : LevelDifficulty.values()) {
                JButton levelButton = new JButton();
                levelButton.setText(getName(levelDifficulty));
            }
        }
        */

        // Place elements
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.gridx = 0;
        constraints.gridy = 0;

        add(nameLabel, constraints);
        constraints.gridy = 1;
        add(loadingLabel, constraints);
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

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
