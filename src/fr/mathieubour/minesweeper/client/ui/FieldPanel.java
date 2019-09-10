package fr.mathieubour.minesweeper.client.ui;

import fr.mathieubour.minesweeper.client.listeners.TileButtonListener;
import fr.mathieubour.minesweeper.game.Field;
import fr.mathieubour.minesweeper.game.Level;

import javax.swing.*;
import java.awt.*;

class FieldPanel extends JPanel {
    private Field field;
    private TileButtonListener tileButtonListener = new TileButtonListener();

    FieldPanel() {
        super();

        field = new Field(Level.MEDIUM);
        drawGrid();
    }

    private void drawGrid() {
        GameFrame.instance.setSize(
                field.getRows() * TileButton.TILE_SIZE_PX,
                field.getColumns() * TileButton.TILE_SIZE_PX
        );

        setLayout(new GridLayout(field.getRows(), field.getColumns()));

        for (int x = 0; x < field.getRows(); x++) {
            for (int y = 0; y < field.getColumns(); y++) {
                TileButton tileButton = new TileButton(x, y);
                tileButton.addActionListener(tileButtonListener);
                add(tileButton);
            }
        }
    }
}
