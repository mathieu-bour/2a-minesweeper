package fr.mathieubour.deminer;


import fr.mathieubour.deminer.game.Field;
import fr.mathieubour.deminer.game.Level;

public class Test {
    public static void main(String args[]) throws Exception {
        Field f = new Field(Level.MEDIUM);
        f.generate();
        f.print();
    }
}
