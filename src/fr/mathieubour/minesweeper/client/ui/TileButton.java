package fr.mathieubour.minesweeper.client.ui;

import fr.mathieubour.minesweeper.game.Tile;

import javax.swing.*;
import java.awt.*;

public class TileButton extends JButton {
    public static int TILE_SIZE_PX = 40;
    public int x;
    public int y;

    private Tile tile;

    public TileButton(int x, int y) {
        super();
        this.x = x;
        this.y = y;
        setTile(new Tile());
        redraw();
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public void redraw() {
        setMargin(new Insets(0, 0, 0, 0));
        setIcon(AssetsLoader.images.get(getImageName()));
    }

    private String getImageName() {
        switch (this.tile.getStatus()) {
            case PRISTINE:
                return "pristine.png";
            case MINED:
                return "bomb.png";
            case EMPTY:
                return this.tile.getBombsAround() + ".png";
            default:
                throw new IllegalStateException("Unexpected value: " + this.tile.getStatus());
        }
    }
}
