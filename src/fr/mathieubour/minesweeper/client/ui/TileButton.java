package fr.mathieubour.minesweeper.client.ui;

import fr.mathieubour.minesweeper.game.Player;
import fr.mathieubour.minesweeper.game.Tile;
import fr.mathieubour.minesweeper.game.TileStatus;
import fr.mathieubour.minesweeper.utils.Log;

import javax.swing.*;
import java.awt.*;

/**
 * Represent a game tile. A tile is identified by its coordinates and can have multiple statuses.
 */
public class TileButton extends JButton {
    public static final int TILE_SIZE_PX = 30;
    public final int x;
    public final int y;

    private Tile tile;

    public TileButton(int x, int y) {
        super();

        setBorder(null);
        // setBorderPainted(false);
        setMargin(new Insets(0, 0, 0, 0));
        // setContentAreaFilled(false);
        setPressedIcon(AssetsLoader.getInstance().get("pristine-pressed.png"));

        this.x = x;
        this.y = y;
        setTile(new Tile());

        redraw();
    }

    public void setTile(Tile tile) {
        this.tile = tile;
        redraw();
    }

    public Tile getTile() {
        return tile;
    }

    public void redraw() {
        Log.info("Drawing (" + x + "," + y + "): " + tile.getStatus() + ":" + tile.getBombsAround());

        setIcon(AssetsLoader.getInstance().get(getImageName()));
        setDisabledIcon(AssetsLoader.getInstance().get(getImageName()));

        if (tile.getSweeper() != null) {
            Player sweeper = tile.getSweeper();
            setBackground(sweeper.getColor());
        }

        setEnabled(tile.getStatus() == TileStatus.PRISTINE);
    }

    private String getImageName() {
        switch (this.tile.getStatus()) {
            case PRISTINE:
                return "pristine.png";
            case MINED:
                return "bomb.png";
            case EMPTY:
                return tile.getBombsAround() + ".png";
            default:
                throw new IllegalStateException("Unexpected value: " + tile.getStatus());
        }
    }
}
