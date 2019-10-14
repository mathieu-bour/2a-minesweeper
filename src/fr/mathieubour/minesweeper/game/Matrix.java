package fr.mathieubour.minesweeper.game;

import java.io.Serializable;
import java.util.Vector;

public class Matrix<T> implements Serializable, Cloneable {
    private final int sizeX;
    private final int sizeY;

    private Vector<Vector<T>> internal;

    public Matrix(int sizeX, int sizeY, T defaultValue) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;

        internal = new Vector<>();

        for (int x = 0; x < this.sizeX; x++) {
            internal.add(new Vector<>(this.sizeY));

            for (int y = 0; y < this.sizeY; y++) {
                internal.get(x).add(defaultValue);
            }
        }
    }

    public T get(int x, int y) {
        return internal.get(x).get(y);
    }

    public void set(int x, int y, T value) {
        internal.get(x).set(y, value);
    }

    public String toString() {
        StringBuilder str = new StringBuilder();

        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                str.append(get(x, y).toString());
            }
            str.append("\n");
        }

        return str.toString();
    }

    public Vector<Vector<T>> getInternal() {
        return internal;
    }
}
