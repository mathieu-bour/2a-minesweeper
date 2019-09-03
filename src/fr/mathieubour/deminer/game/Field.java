package fr.mathieubour.deminer.game;

import java.util.Random;

/**
 * @author Mathieu Bour
 * @version 1.0.0
 */
public class Field {
    private int rows;
    private int columns;
    private int mines;
    private Matrix<Tile> grid;

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
                this.mines = 15;
                break;
            case MEDIUM:
                this.rows = 20;
                this.columns = 20;
                this.mines = 50;
                break;
            case HARD:
                this.rows = 30;
                this.columns = 30;
                this.mines = 150;
                break;
            case CUSTOM:
            default:
                // throw new Exception("CUSTOM difficulty require columns, rows and mines count.");
        }

        this.reset();
    }

    /**
     * Create a custom field.
     *
     * @param rows    The number of rows of the grid.
     * @param columns The number of columns of teh grid.
     * @param mines   The number of mines into the field. Must be lower that the number of cases (rows * columns).
     * @throws Exception
     */
    public Field(int rows, int columns, int mines) throws Exception {
        if (rows * columns < mines) {
            throw new Exception("Too few cases to handle " + mines + " mines! " +
                    "(Got a " + columns + " by " + rows + " field)");
        }


        this.rows = rows;
        this.columns = columns;
        this.mines = mines;

        this.reset();
    }

    /**
     * Reset the field to empty (no mines).
     */
    public void reset() {
        this.grid = new Matrix<>(this.columns, this.rows, new Tile());
    }

    /**
     * Generate a new mine field base don the parameters of the fields (rows, columns, number of mines).
     */
    public void generate() {
        int x, y;
        Random rand = new Random(System.currentTimeMillis());

        for (int i = 0; i < this.mines; i++) {
            do {
                x = rand.nextInt(this.columns - 1);
                y = rand.nextInt(this.rows - 1);
            } while (this.grid.get(x, y).getStatus() == TileStatus.MINED);

            this.grid.set(x, y, new Tile(TileStatus.MINED));
        }
    }

    /**
     * Print the field into the console, using (X as mine, . as empty).
     */
    public void print() {
        System.out.println(this.grid.toString());
    }

    public int around(int x, int y) {
        int count = 0;
        int[][] coords = {
                {x - 1, y - 1},
                {x - 1, y},
                {x - 1, y + 1},
                {x, y - 1},
                {x, y + 1},
                {x + 1, y - 1},
                {x + 1, y},
                {x + 1, y + 1}
        };

        for (int[] coord : coords) {
            int mineX = coord[0];
            int mineY = coord[1];

            boolean xValid = 0 <= mineX && mineX < this.columns; // valid x coords
            boolean yValid = 0 <= mineY && mineY < this.rows; // valid y coords
            boolean hasMine = this.grid.get(x, y).getStatus() == TileStatus.MINED; // there is a mine here!

            if (xValid && yValid && hasMine) {
                count++;
            }
        }

        return count;
    }


    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }
}
