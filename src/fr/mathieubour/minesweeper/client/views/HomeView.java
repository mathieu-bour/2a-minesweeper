package fr.mathieubour.minesweeper.client.views;

import fr.mathieubour.minesweeper.client.panels.LoginPanel;
import fr.mathieubour.minesweeper.client.panels.OfflinePanel;

import javax.swing.*;
import java.awt.*;

/**
 * Display the home view with the single player and server login forms.
 */
public class HomeView extends JPanel {
    /**
     * The singleton.
     */
    private static HomeView instance;

    private HomeView() {
        super(new GridBagLayout());
        draw();
    }

    public static HomeView getInstance() {
        if (instance == null) {
            instance = new HomeView();
        }

        return instance;
    }

    private void draw() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.BOTH;
        add(OfflinePanel.getInstance(), constraints);

        constraints.gridx = 1;
        add(LoginPanel.getInstance(), constraints);
    }
}
