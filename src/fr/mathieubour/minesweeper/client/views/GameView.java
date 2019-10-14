package fr.mathieubour.minesweeper.client.views;

import fr.mathieubour.minesweeper.client.panels.FieldPanel;
import fr.mathieubour.minesweeper.client.panels.ScoreboardPanel;

import javax.swing.*;
import java.awt.*;

public class GameView extends JPanel {
    private static GameView instance;

    private GameView() {
        super(new BorderLayout());
        redraw();
    }

    public static synchronized GameView getInstance() {
        if (instance == null) {
            instance = new GameView();
        }

        return instance;
    }

    public void redraw() {
        removeAll();
        add(FieldPanel.getInstance(), BorderLayout.CENTER);
        ScoreboardPanel.getInstance().redraw();
        add(ScoreboardPanel.getInstance(), BorderLayout.EAST);
    }
}
