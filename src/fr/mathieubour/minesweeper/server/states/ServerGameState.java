package fr.mathieubour.minesweeper.server.states;

import fr.mathieubour.minesweeper.client.states.GameState;
import fr.mathieubour.minesweeper.game.Field;
import fr.mathieubour.minesweeper.game.Level;
import fr.mathieubour.minesweeper.game.Player;

import java.util.UUID;

public class ServerGameState extends GameState {
    private static ServerGameState instance;

    private ServerGameState() {
        // Temporary state for testing
        for (int i = 1; i <= 3; i++) {
            this.players.put(UUID.randomUUID().toString(), new Player("Player " + i));
        }

        Field newField = new Field(Level.EASY);
        newField.placeMines();
        setField(newField);
    }

    public static ServerGameState getInstance() {
        if (instance == null) {
            instance = new ServerGameState();
        }
        return instance;
    }
}
