package fr.mathieubour.minesweeper.server.handlers;

import fr.mathieubour.minesweeper.packets.NewGamePacket;
import fr.mathieubour.minesweeper.packets.PlayerLoginPacket;
import fr.mathieubour.minesweeper.server.Server;
import fr.mathieubour.minesweeper.server.network.ServerInputThread;
import fr.mathieubour.minesweeper.server.network.ServerSocketHandler;
import fr.mathieubour.minesweeper.server.states.ServerGameState;
import fr.mathieubour.minesweeper.utils.Log;

public class PlayerLoginPacketHandler {
    private static PlayerLoginPacketHandler instance;

    public static PlayerLoginPacketHandler getInstance() {
        if (instance == null) {
            instance = new PlayerLoginPacketHandler();
        }

        return instance;
    }

    public void handle(PlayerLoginPacket packet, ServerInputThread sourceThread) {
        // User attempt to join the game
        ServerGameState serverGameState = ServerGameState.getInstance();

        if (serverGameState.getPlayers().size() == Server.MAX_PLAYERS) {
            // TODO: refuse the connection
        }

        sourceThread.setPlayer(packet.getPlayer());
        serverGameState.getPlayers().put(sourceThread.getPlayer().getId(), sourceThread.getPlayer());

        Log.info(serverGameState.getPlayers().size() + " players are connected");

        if (serverGameState.getPlayers().size() == Server.MAX_PLAYERS) {
            // Start the game
            // serverGameState.setField(new Field(Level.MEDIUM));

            ServerSocketHandler.getInstance().broadcast(new NewGamePacket(
                serverGameState.getField(),
                serverGameState.getPlayers()
            ));
        }
    }
}
