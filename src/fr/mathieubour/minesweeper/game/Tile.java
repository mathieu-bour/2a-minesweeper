package fr.mathieubour.minesweeper.game;

import java.io.Serializable;

/**
 * Represents a tile and its status.
 *
 * @author Mathieu Bour
 * @version 1.0
 * @since 1.0
 */
public class Tile implements Serializable, Cloneable {
    private TileStatus status;
    private int bombsAround = 0;
    private Player sweeper;


    public Tile() {
        this(TileStatus.PRISTINE);
    }

    /**
     * Create a tile.
     *
     * @param status the tile status.
     * @see TileStatus
     */
    public Tile(TileStatus status) {
        this.status = status;
    }

    /**
     * Get the tile status.
     *
     * @return the title status.
     * @see TileStatus
     */
    public TileStatus getStatus() {
        return this.status;
    }

    public synchronized void setStatus(TileStatus status) {
        this.status = status;
    }

    /**
     * Get the number of bombs around the tile.
     *
     * @return the number of bombs around.
     */
    public int getBombsAround() {
        return this.bombsAround;
    }

    public void setBombsAround(int newBombsAround) {
        this.bombsAround = newBombsAround;
    }

    public Player getSweeper() {
        return sweeper;
    }

    public synchronized void setSweeper(Player sweeper) {
        this.sweeper = sweeper;
    }
}
