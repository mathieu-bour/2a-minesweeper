package fr.mathieubour.minesweeper;


import fr.mathieubour.minesweeper.game.Field;
import fr.mathieubour.minesweeper.game.Level;

public class Test {
    public static void main(String args[]) throws Exception {
        Field f = new Field(Level.MEDIUM);
        f.generate();
        f.print();
    }
}
