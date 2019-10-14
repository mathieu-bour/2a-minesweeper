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
    public static final int TILE_SIZE_PX = 25;
    public final int x;
    public final int y;

    private Tile tile;

    public TileButton(int x, int y) {
        super();

        this.x = x;
        this.y = y;

        setBorder(null);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setOpaque(true);

        setPressedIcon(AssetsLoader.getInstance().get("pristine-pressed.png"));

        setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        setMargin(new Insets(0, 0, 0, 0));

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
        setIcon(AssetsLoader.getInstance().get(getImageName()));

        if (tile.getSweeper() != null) {
            Player sweeper = tile.getSweeper();
            setPressedIcon(AssetsLoader.getInstance().get(getImageName()));
            setBackground(sweeper.getColor());
            setForeground(sweeper.getColor());
        }

        setFocusable(tile.getStatus() != TileStatus.PRISTINE);
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
