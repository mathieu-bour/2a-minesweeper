package fr.mathieubour.minesweeper.game;

public class Level {
    public static final Level EASY = new Level("Easy", 10, 10, 15);
    public static final Level MEDIUM = new Level("Medium", 20, 20, 50);
    public static final Level HARD = new Level("Hard", 30, 30, 150);
    public static final Level[] ALL = {EASY, MEDIUM, HARD};

    private String name;
    private final int rows;
    private final int columns;
    private final int mineCount;


    public Level(String name, int rows, int columns, int mineCount) {
        this.name = name;
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

    public String toString() {
        return String.format("%s (%d mines, %dx%d)", this.name, mineCount, rows, columns);
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
