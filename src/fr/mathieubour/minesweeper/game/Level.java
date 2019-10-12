package fr.mathieubour.minesweeper.game;

public class Level {
    public static final Level EASY = new Level(10, 10, 15);
    public static final Level MEDIUM = new Level(20, 20, 50);
    public static final Level HARD = new Level(30, 30, 150);

    private final int rows;
    private final int columns;
    private final int mineCount;


    public Level(int rows, int columns, int mineCount) {
        this.rows = rows;
        this.columns = columns;
        this.mineCount = mineCount;
    }

    public static Level fromDifficulty(LevelDifficulty difficulty) {
        switch (difficulty) {
            case EASY:
                return Level.EASY;
            case MEDIUM:
                return Level.MEDIUM;
            case HARD:
                return Level.HARD;
            default:
                return null;
        }
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getMineCount() {
        return mineCount;
    }
}
