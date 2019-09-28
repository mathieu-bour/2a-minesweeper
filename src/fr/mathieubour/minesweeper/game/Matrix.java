package fr.mathieubour.minesweeper.game;

import java.io.Serializable;
import java.util.Vector;

public class Matrix<T> implements Serializable {
    private int sizeX;
    private int sizeY;
    private Vector<Vector<T>> internal;

    Matrix(int sizeX, int sizeY, T defaultValue) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;

        this.internal = new Vector<>();

        for (int x = 0; x < this.sizeX; x++) {
            this.internal.add(new Vector<>(this.sizeY));

            for (int y = 0; y < this.sizeY; y++) {
                this.internal.get(x).add(defaultValue);
            }
        }
    }

    T get(int x, int y) {
        return this.internal.get(x).get(y);
    }

    void set(int x, int y, T value) {
        this.internal.get(x).set(y, value);
    }

    public String toString() {
        StringBuilder str = new StringBuilder();

        for (int x = 0; x < this.sizeX; x++) {
            for (int y = 0; y < this.sizeY; y++) {
                str.append(this.get(x, y).toString());
            }
            str.append("\n");
        }

        return str.toString();
    }
}
