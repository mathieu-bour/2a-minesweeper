package fr.mathieubour.deminer.client.ui;

import fr.mathieubour.deminer.game.Tile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class TileButton extends JButton {
    public static int TILE_SIZE_PX = 40;
    private Tile tile;

    TileButton() {
        super();
        redraw();
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public void redraw() {
        setMargin(new Insets(0, 0, 0, 0));

        try {
            Image img = ImageIO.read(getClass().getResource("../../assets/" + this.getImageName()));
            Image scaledImage = img.getScaledInstance(TILE_SIZE_PX, TILE_SIZE_PX, java.awt.Image.SCALE_SMOOTH);
            setIcon(new ImageIcon(scaledImage));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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
