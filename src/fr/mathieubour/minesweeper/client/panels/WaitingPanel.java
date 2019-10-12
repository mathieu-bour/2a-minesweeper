package fr.mathieubour.minesweeper.client.panels;

import fr.mathieubour.minesweeper.client.states.GameState;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class WaitingPanel extends JPanel {
    private static WaitingPanel instance;
    private HashMap<String, VotePanel> votePanels = new HashMap<>();

    private WaitingPanel() {
        super(new GridBagLayout());
        draw();
    }

    public static WaitingPanel getInstance() {
        if (instance == null) {
            instance = new WaitingPanel();
        }

        return instance;
    }

    private void draw() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridy = 0;

        AtomicInteger x = new AtomicInteger();
        GameState.getInstance().getPlayers().forEach((id, player) -> {
            constraints.gridx = x.getAndIncrement();
            VotePanel votePanel = new VotePanel(player, true);
            votePanels.put(id, votePanel);
            add(votePanel, constraints);
        });
    }
}
