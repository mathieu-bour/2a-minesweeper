package fr.mathieubour.minesweeper.server.states;

import fr.mathieubour.minesweeper.client.states.ClientGameState;
import fr.mathieubour.minesweeper.game.Field;
import fr.mathieubour.minesweeper.game.Level;
import fr.mathieubour.minesweeper.utils.Log;

import java.util.concurrent.atomic.AtomicBoolean;

public class ServerGameState extends ClientGameState {
    private static ServerGameState instance;

    private ServerGameState() {
        newGame(Level.MEDIUM);
    }

    public static synchronized ServerGameState getInstance() {
        if (instance == null) {
            instance = new ServerGameState();
        }

        return instance;
    }

    public void newGame(Level level) {
        Field newField = new Field(level);
        newField.placeMines();
        setField(newField);
    }

    /**
     * Check if the game is finished.
     *
     * @return if the game is finished
     */
    public boolean isGameFinished() {
        // Check that at least one player is alive
        AtomicBoolean atLeastOneAlive = new AtomicBoolean(false);
        players.forEach((uuid, player) -> {
            atLeastOneAlive.set(atLeastOneAlive.get() || player.isAlive());
        });
        boolean fieldFinished = getField().isFinished();
        boolean shouldRestart = !atLeastOneAlive.get() && fieldFinished;

        System.out.println(atLeastOneAlive);
        System.out.println(fieldFinished);
        System.out.println(shouldRestart);

        return shouldRestart;
    }
}
