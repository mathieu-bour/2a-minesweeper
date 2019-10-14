package fr.mathieubour.minesweeper.game;

import java.io.Serializable;
import java.util.Random;

/**
 * @author Mathieu Bour
 * @version 1.0.0
 */
public class Field implements Serializable, Cloneable {
    private final Level level;
    private Matrix<Tile> tileMatrix;

    /**
     * Build the field using the default Level.MEDIUM.
     *
     * @see Level
     */
    public Field() {
        this(Level.MEDIUM);
    }

    /**
     * Build the field using a level configuration.
     *
     * @param level The level.
     */
    public Field(Level level) {
        this.level = level;
        this.initialize();
    }

    /**
     * @return The level.
     */
    public Level getLevel() {
        return level;
    }

    /**
     * @return The tile matrix.
     */
    public Matrix<Tile> getTileMatrix() {
        return tileMatrix;
    }

    /**
     * Create the Matrix using the columns/rows parameters.
     */
    private void initialize() {
        tileMatrix = new Matrix<>(level.getColumns(), level.getRows(), null);

        for (int x = 0; x < level.getColumns(); x++) {
            for (int y = 0; y < level.getRows(); y++) {
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

        for (int i = 0; i < level.getMineCount(); i++) {
            do {
                x = rand.nextInt(level.getColumns() - 1);
                y = rand.nextInt(level.getRows() - 1);
            } while (tileMatrix.get(x, y).getStatus() == TileStatus.MINED);

            tileMatrix.get(x, y).setStatus(TileStatus.MINED);
        }

        // Compute the "mines around" values for each tile of the field.
        for (x = 0; x < level.getColumns(); x++) {
            for (y = 0; y < level.getRows(); y++) {
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
                boolean xValid = 0 <= xx && xx < level.getColumns();
                boolean yValid = 0 <= yy && yy < level.getRows();

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

    /**
     * Check that the field still got a least 1 pristine tile.
     *
     * @return if the field is considered at finished.
     */
    public boolean isFinished() {
        for (int x = 0; x < level.getColumns(); x++) {
            for (int y = 0; y < level.getRows(); y++) {
                Tile tile = tileMatrix.get(x, y);

                if (tile.getStatus() == TileStatus.PRISTINE) {
                    return false;
                }
            }
        }

        return true;
    }
}
