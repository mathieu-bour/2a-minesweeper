package fr.mathieubour.minesweeper.client.panels;

import fr.mathieubour.minesweeper.client.states.ClientGameState;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class VotesPanel extends JPanel {
    private static VotesPanel instance;

    private HashMap<String, VotePanel> votePanels = new HashMap<>();

    private VotesPanel() {
        super(new GridBagLayout());
        redraw();
    }

    public static VotesPanel getInstance() {
        if (instance == null) {
            instance = new VotesPanel();
        }

        return instance;
    }

    public void redraw() {
        removeAll();
        GridBagConstraints constraints = new GridBagConstraints();
        JPanel votesPanel = new JPanel(new GridBagLayout());
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = 1;
        constraints.weighty = 1;

        AtomicInteger x = new AtomicInteger();
        ClientGameState.getInstance().getPlayers().forEach((id, player) -> {
            constraints.gridx = x.getAndIncrement();
            VotePanel votePanel = new VotePanel(player);
            votePanels.put(id, votePanel);
            votesPanel.add(votePanel, constraints);
        });

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.BOTH;
        add(votesPanel, constraints);
        invalidate();
    }
}
