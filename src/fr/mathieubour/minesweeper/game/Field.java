package fr.mathieubour.minesweeper.game;

import java.io.Serializable;
import java.util.Random;

/**
 * @author Mathieu Bour
 * @version 1.0.0
 */
public class Field implements Serializable, Cloneable {
    private int rows;
    private int columns;
    private int minesCount;
    private Matrix<Tile> tileMatrix;

    /**
     * Build the field using a predefined difficulty.
     *
     * @param level The level.
     */
    public Field(Level level) {
        switch (level) {
            case EASY:
                this.rows = 10;
                this.columns = 10;
                this.minesCount = 15;
                break;
            case MEDIUM:
                this.rows = 20;
                this.columns = 20;
                this.minesCount = 50;
                break;
            case HARD:
                this.rows = 30;
                this.columns = 30;
                this.minesCount = 150;
                break;
        }

        this.initialize();
    }

    public Field(int rows, int columns, int minesCount) {
        this.rows = rows;
        this.columns = columns;
        this.minesCount = minesCount;

        this.initialize();
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getMinesCount() {
        return minesCount;
    }

    public Matrix<Tile> getTileMatrix() {
        return tileMatrix;
    }

    /**
     * Create the Matrix using the columns/rows parameters.
     */
    private void initialize() {
        tileMatrix = new Matrix<>(columns, rows, null);

        for (int x = 0; x < getColumns(); x++) {
            for (int y = 0; y < getRows(); y++) {
                tileMatrix.set(x, y, new Tile(TileStatus.PRISTINE));
            }
        }
    }

    /**
     * Generate a new mine field base don the parameters of the fields (rows, columns, number of mines).
     */
    public void placeMines() {
        int x, y;
        Random rand = new Random(System.currentTimeMillis());

        for (int i = 0; i < minesCount; i++) {
            do {
                x = rand.nextInt(columns - 1);
                y = rand.nextInt(rows - 1);
            } while (tileMatrix.get(x, y).getStatus() == TileStatus.MINED);

            tileMatrix.get(x, y).setStatus(TileStatus.MINED);
        }

        // Compute the "mines around" values for each tile of the field.
        for (x = 0; x < getColumns(); x++) {
            for (y = 0; y < getRows(); y++) {
                Tile tile = tileMatrix.get(x, y);
                tile.setBombsAround(around(x, y));

                if (tile.getStatus() == TileStatus.PRISTINE) {
                    tile.setStatus(TileStatus.EMPTY);
                }
            }
        }
    }

    /**
     * Get the mines around a given tile, identified by its x and y coordinates.
     *
     * @param x The x coordinate
     * @param y The y coordinate
     * @return The number of mines around the tile (between 0 and 8).
     */
    private int around(int x, int y) {
        int count = 0;

        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                int xx = x + dx;
                int yy = y + dy;

                boolean isSelf = dx == 0 && dy == 0;
                boolean xValid = 0 <= xx && xx < columns;
                boolean yValid = 0 <= yy && yy < rows;

                if (!xValid || !yValid || isSelf) {
                    continue;
                }

                boolean hasMine = tileMatrix.get(xx, yy).getStatus() == TileStatus.MINED;

                if (hasMine) {
                    count++;
                }
            }
        }

        return count;
    }
}
