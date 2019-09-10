package fr.mathieubour.minesweeper.game;

/**
 * Represents a tile and its status.
 *
 * @author Mathieu Bour
 * @version 1.0
 * @since 1.0
 */
public class Tile {
    private TileStatus status;
    private int bombsAround = 0;

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

    @Override
    public String toString() {
        switch (this.status) {
            case EMPTY:
                return " ";
            case MINED:
                return "X";
            case PRISTINE:
                return "?";
            default:
                return "E";
        }
    }
}
