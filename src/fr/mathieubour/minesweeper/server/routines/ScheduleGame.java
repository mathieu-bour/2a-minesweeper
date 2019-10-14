package fr.mathieubour.minesweeper.server.routines;

import fr.mathieubour.minesweeper.game.Level;
import fr.mathieubour.minesweeper.game.LevelDifficulty;
import fr.mathieubour.minesweeper.game.Player;
import fr.mathieubour.minesweeper.packets.GameStartDatePacket;
import fr.mathieubour.minesweeper.packets.GameStartPacket;
import fr.mathieubour.minesweeper.server.Server;
import fr.mathieubour.minesweeper.server.network.ServerSocketHandler;
import fr.mathieubour.minesweeper.server.states.ServerGameState;

import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class ScheduleGame {
    private static ScheduleGame instance;
    private boolean scheduled = false;
    private Timer startTimer = new Timer();

    public synchronized static ScheduleGame getInstance() {
        if (instance == null) {
            instance = new ScheduleGame();
        }

        return instance;
    }

    /**
     * Schedule a new game.
     * 1. Broadcast the GameStartDatePacket
     * 2. After the delay, broadcast the new field.
     */
    public void schedule() {
        if (scheduled) {
            return;
        }

        scheduled = true;
        long delta = Server.WAITING_COUNTDOWN * 1000L;

        ServerSocketHandler
            .getInstance()
            .broadcast(new GameStartDatePacket(System.currentTimeMillis() + delta));

        startTimer = new Timer();
        TimerTask startTimerTask = new TimerTask() {
            @Override
            public void run() {
                // Choose the difficulty based on votes
                ServerGameState serverGameState = ServerGameState.getInstance();

                HashMap<String, Player> voters = new HashMap<>();
                serverGameState.getPlayers().forEach((uuid, player) -> {
                    if (player.getVote() != null) {
                        voters.put(uuid, player);
                    }

                    // Reset player
                    player.setAlive(true);
                    player.setScore(0);
                });

                if (voters.size() == 0) {
                    // Nobody votes, set game level to MEDIUM
                    serverGameState.newGame(Level.MEDIUM);
                } else {
                    // At least a player voted, randomly select one
                    Random generator = new Random();
                    int index = generator.nextInt(voters.keySet().size());
                    String id = (String) voters.keySet().toArray()[index];
                    LevelDifficulty difficulty = voters.get(id).getVote();
                    serverGameState.newGame(Level.fromDifficulty(difficulty));
                }

                ServerSocketHandler.getInstance().broadcast(new GameStartPacket(
                    serverGameState.getField(),
                    serverGameState.getPlayers()
                ));

                scheduled = false;
                startTimer.cancel();
            }
        };

        startTimer.schedule(startTimerTask, delta);
    }
}
