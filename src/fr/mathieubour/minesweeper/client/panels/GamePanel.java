package fr.mathieubour.minesweeper.client.panels;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private static GamePanel instance;

    private GamePanel() {
        super(new BorderLayout());
        add(FieldPanel.getInstance(), BorderLayout.CENTER);
        add(ScorePanel.getInstance(), BorderLayout.EAST);
    }

    public static synchronized GamePanel getInstance() {
        if (instance == null) {
            instance = new GamePanel();
        }

        return instance;
    }
}
