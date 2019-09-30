package fr.mathieubour.minesweeper.game;

import java.awt.*;
import java.io.Serializable;

public class Player implements Serializable {
    private String id;
    private String name;
    private Color color;
    private boolean alive = true;
    private int score = 0;

    public Player(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
