package fr.mathieubour.deminer.client.ui;

import fr.mathieubour.deminer.game.Field;
import fr.mathieubour.deminer.game.Level;

import javax.swing.*;
import java.awt.*;

public class FieldPanel extends JPanel {
    private Field field;

    FieldPanel() {
        super();

        field = new Field(Level.MEDIUM);
        drawGrid();
    }

    void drawGrid() {
        GameFrame.ref.setSize(
                this.field.getRows() * TileButton.TILE_SIZE_PX,
                this.field.getColumns() * TileButton.TILE_SIZE_PX
        );
        
        setLayout(new GridLayout(this.field.getRows(), this.field.getColumns()));

        for (int i = 0; i < this.field.getRows() * this.field.getColumns(); i++) {
            add(new TileButton());
        }
    }
}
