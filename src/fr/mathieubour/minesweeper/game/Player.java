package fr.mathieubour.minesweeper.game;

import java.awt.*;
import java.io.Serializable;
import java.util.Random;

public class Player implements Serializable, Cloneable {
    private String id;
    private String name;
    private Color color;
    private LevelDifficulty vote;
    private boolean alive = true;
    private int score = 0;

    public Player(String name) {
        Random random = new Random();
        this.name = name;
        this.color = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
    }

    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public LevelDifficulty getVote() {
        return vote;
    }

    public void setVote(LevelDifficulty vote) {
        this.vote = vote;
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

    public void setFrom(Player player) {
        id = player.getId();
        name = player.getName();
        color = player.getColor();
        vote = player.getVote();
        alive = player.isAlive();
        score = player.getScore();
    }

    public Player clone() throws CloneNotSupportedException {
        return (Player) super.clone();
    }
}
