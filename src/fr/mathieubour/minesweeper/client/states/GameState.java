package fr.mathieubour.minesweeper.client.states;

import fr.mathieubour.minesweeper.game.Field;
import fr.mathieubour.minesweeper.game.Player;

import java.util.HashMap;

public class GameState {
    private static GameState instance;
    protected HashMap<String, Player> players = new HashMap<>();
    protected Field field;

    public static synchronized GameState getInstance() {
        if (instance == null) {
            instance = new GameState();
        }

        return instance;
    }

    public HashMap<String, Player> getPlayers() {
        return players;
    }

    public void setPlayers(HashMap<String, Player> players) {
        this.players = players;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }
}
