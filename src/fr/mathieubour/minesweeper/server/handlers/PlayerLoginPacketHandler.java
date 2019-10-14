package fr.mathieubour.minesweeper.server.handlers;

import fr.mathieubour.minesweeper.game.Level;
import fr.mathieubour.minesweeper.game.LevelDifficulty;
import fr.mathieubour.minesweeper.game.Player;
import fr.mathieubour.minesweeper.packets.*;
import fr.mathieubour.minesweeper.server.Server;
import fr.mathieubour.minesweeper.server.network.ServerInputThread;
import fr.mathieubour.minesweeper.server.network.ServerSocketHandler;
import fr.mathieubour.minesweeper.server.routines.ScheduleGame;
import fr.mathieubour.minesweeper.server.states.ServerGameState;
import fr.mathieubour.minesweeper.utils.Log;

import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class PlayerLoginPacketHandler {
    private static PlayerLoginPacketHandler instance;

    public static PlayerLoginPacketHandler getInstance() {
        if (instance == null) {
            instance = new PlayerLoginPacketHandler();
        }

        return instance;
    }

    public synchronized void handle(PlayerLoginPacket packet, ServerInputThread sourceThread) {
        // User attempt to join the game
        ServerGameState serverGameState = ServerGameState.getInstance();

        if (serverGameState.getPlayers().size() >= Server.MAX_PLAYERS) {
            // TODO: refuse the connection
        }

        sourceThread.setPlayer(packet.getPlayer());
        serverGameState.getPlayers().put(sourceThread.getPlayer().getId(), sourceThread.getPlayer());

        Log.info(serverGameState.getPlayers().size() + " players are connected");

        PlayerListPacket p = new PlayerListPacket(serverGameState.getPlayers());
        ServerSocketHandler.getInstance().broadcast(p);

        if (serverGameState.getPlayers().size() >= Server.MIN_PLAYERS) {
            ScheduleGame.getInstance().schedule();
        }
    }
}
