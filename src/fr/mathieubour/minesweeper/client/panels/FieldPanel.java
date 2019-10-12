package fr.mathieubour.minesweeper.client.panels;

import fr.mathieubour.minesweeper.client.network.ClientSocketHandler;
import fr.mathieubour.minesweeper.client.states.GameState;
import fr.mathieubour.minesweeper.client.ui.TileButton;
import fr.mathieubour.minesweeper.game.*;
import fr.mathieubour.minesweeper.packets.TileRequestPacket;
import fr.mathieubour.minesweeper.utils.Log;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FieldPanel extends JPanel implements ActionListener {
    private static FieldPanel instance;
    private Field field;
    private Matrix<TileButton> tileButtonMatrix;

    private FieldPanel() {
        super(new GridBagLayout());
        drawGrid();
    }

    public static FieldPanel getInstance() {
        if (instance == null) {
            instance = (new FieldPanel());
        }

        return instance;
    }

    public Matrix<TileButton> getTileButtonMatrix() {
        return tileButtonMatrix;
    }

    private void drawGrid() {
        Log.info("Drawing grid");
        field = GameState.getInstance().getField();

        if (field == null) {
            field = new Field(Level.MEDIUM);
        }

//        GameFrame.getInstance().setSize(
//            field.getRows() * TileButton.TILE_SIZE_PX,
//            field.getColumns() * TileButton.TILE_SIZE_PX
//        );

        tileButtonMatrix = new Matrix<>(field.getLevel().getRows(), field.getLevel().getColumns(), null);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;

        for (int x = 0; x < field.getLevel().getRows(); x++) {
            constraints.gridx = x;

            for (int y = 0; y < field.getLevel().getColumns(); y++) {
                TileButton tileButton = new TileButton(x, y);
                tileButtonMatrix.set(x, y, tileButton);

                Tile tile = field.getTileMatrix().get(x, y);
                tileButton.setTile(tile);

                constraints.gridy = y;
                add(tileButton, constraints);

                tileButton.addActionListener(this);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        Log.info(source.getClass().getName());
        if (!(source instanceof TileButton)) {
            return;
        }

        TileButton tileButton = (TileButton) actionEvent.getSource();

        if (tileButton.getTile().getStatus() == TileStatus.PRISTINE) {
            ClientSocketHandler.getInstance().send(
                new TileRequestPacket(tileButton.x, tileButton.y)
            );
        }
    }
}
