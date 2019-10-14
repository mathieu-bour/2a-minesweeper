package fr.mathieubour.minesweeper.client.panels;

import fr.mathieubour.minesweeper.client.Client;
import fr.mathieubour.minesweeper.client.network.ClientSocketHandler;
import fr.mathieubour.minesweeper.client.states.ClientGameState;
import fr.mathieubour.minesweeper.client.states.PlayerState;
import fr.mathieubour.minesweeper.client.ui.TileButton;
import fr.mathieubour.minesweeper.game.*;
import fr.mathieubour.minesweeper.packets.TileRequestPacket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class FieldPanel extends JPanel {
    private static FieldPanel instance;
    private Matrix<TileButton> tileButtonMatrix;

    private FieldPanel() {
        super(new GridBagLayout());
        redraw();
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

    public void redraw() {
        removeAll();
        Field field = ClientGameState.getInstance().getField();

        if (field == null) {
            field = new Field(Level.MEDIUM);
        }

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

                tileButton.addActionListener(this::onTileClick);
            }
        }

        Client.getInstance().setSize(
            field.getLevel().getRows() * TileButton.TILE_SIZE_PX + ScorePanel.WIDTH,
            field.getLevel().getColumns() * TileButton.TILE_SIZE_PX + 150
        );
    }

    private void onTileClick(ActionEvent actionEvent) {
        TileButton tileButton = (TileButton) actionEvent.getSource();
        boolean pristine = tileButton.getTile().getStatus() == TileStatus.PRISTINE;
        boolean alive = PlayerState.getInstance().getPlayer().isAlive();

        if (pristine && alive) {
            ClientSocketHandler.getInstance().send(
                new TileRequestPacket(tileButton.x, tileButton.y)
            );
        }
    }
}
