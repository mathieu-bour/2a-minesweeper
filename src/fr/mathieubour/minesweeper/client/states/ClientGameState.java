package fr.mathieubour.minesweeper.client.states;

import fr.mathieubour.minesweeper.game.Field;
import fr.mathieubour.minesweeper.game.Player;
import fr.mathieubour.minesweeper.utils.Log;

import java.util.HashMap;

public class ClientGameState {
    private static ClientGameState instance;
    protected final HashMap<String, Player> players = new HashMap<>();
    private Field field;

    public static synchronized ClientGameState getInstance() {
        if (instance == null) {
            instance = new ClientGameState();
        }

        return instance;
    }

    public HashMap<String, Player> getPlayers() {
        return players;
    }

    public synchronized void setPlayers(HashMap<String, Player> serverPlayers) {
        getPlayers().forEach((uuid, player) -> {
            if (!serverPlayers.containsKey(uuid)) {
                getPlayers().remove(uuid);
            }
        });

        serverPlayers.forEach((uuid, player) -> {
            if (getPlayers().containsKey(uuid)) {
                getPlayers().get(uuid).setFrom(player); // Keep reference
            } else {
                getPlayers().put(uuid, player);
            }
        });
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }
}
