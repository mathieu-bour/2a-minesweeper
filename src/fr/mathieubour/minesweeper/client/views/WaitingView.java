package fr.mathieubour.minesweeper.client.views;

import fr.mathieubour.minesweeper.client.panels.ChatPanel;
import fr.mathieubour.minesweeper.client.panels.CountdownPanel;
import fr.mathieubour.minesweeper.client.panels.VotesPanel;

import javax.swing.*;
import java.awt.*;

public class WaitingView extends JPanel {
    private static WaitingView instance;

    private WaitingView() {
        super(new GridBagLayout());
        redraw();
    }

    public static WaitingView getInstance() {
        if (instance == null) {
            instance = new WaitingView();
        }

        return instance;
    }

    private void redraw() {
        removeAll();
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.insets = new Insets(15, 0, 15, 0);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        CountdownPanel.getInstance().setDescription("Waiting for other players");
        add(CountdownPanel.getInstance(), constraints);

        constraints.gridy = 1;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.insets = new Insets(0, 0, 15, 0);
        VotesPanel.getInstance().redraw();
        add(VotesPanel.getInstance(), constraints);

        constraints.gridy = 2;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        add(ChatPanel.getInstance(), constraints);
    }
}
