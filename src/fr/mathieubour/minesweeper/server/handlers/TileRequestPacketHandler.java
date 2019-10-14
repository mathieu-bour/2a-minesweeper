package fr.mathieubour.minesweeper.server.handlers;

import fr.mathieubour.minesweeper.game.Field;
import fr.mathieubour.minesweeper.game.Player;
import fr.mathieubour.minesweeper.game.Tile;
import fr.mathieubour.minesweeper.game.TileStatus;
import fr.mathieubour.minesweeper.packets.PlayerDeadPacket;
import fr.mathieubour.minesweeper.packets.PlayerScorePacket;
import fr.mathieubour.minesweeper.packets.TileRequestPacket;
import fr.mathieubour.minesweeper.packets.TileRevealPacket;
import fr.mathieubour.minesweeper.server.network.ServerInputThread;
import fr.mathieubour.minesweeper.server.network.ServerSocketHandler;
import fr.mathieubour.minesweeper.server.routines.ScheduleGame;
import fr.mathieubour.minesweeper.server.states.ServerGameState;
import fr.mathieubour.minesweeper.utils.Log;

public class TileRequestPacketHandler {
    private static TileRequestPacketHandler instance;

    private TileRequestPacketHandler() {
    }

    public static synchronized TileRequestPacketHandler getInstance() {
        if (instance == null) {
            instance = new TileRequestPacketHandler();
        }

        return instance;
    }

    public synchronized void handle(TileRequestPacket packet, ServerInputThread sourceThread) {
        Player player = sourceThread.getPlayer();

        if (player == null) {
            // Player is dead so he cannot play at this moment.
            return;
        } else if (!player.isAlive()) {
            Log.info("Ignoring: " + player.getName() + " is dead");
        }

        ServerGameState serverGameState = ServerGameState.getInstance();

        int x = packet.getX();
        int y = packet.getY();

        Field serverField = serverGameState.getField();
        Tile serverTile = serverField.getTileMatrix().get(x, y);

        if (serverTile.getSweeper() != null) {
            // Tile has already been revealed, ignore.
            Log.info("Ignoring: status is " + serverTile.getStatus());
            return;
        }

        ServerSocketHandler serverSocketHandler = ServerSocketHandler.getInstance();

        serverTile.setSweeper(player);

        TileRevealPacket tileRevealPacket = new TileRevealPacket(
            x,
            y,
            serverTile.getStatus(),
            serverTile.getBombsAround(),
            player.getId()
        );

        serverSocketHandler.broadcast(tileRevealPacket);

        if (serverTile.getStatus() == TileStatus.MINED) {
            // There was a mine, player is dead
            player.setAlive(false);
            serverSocketHandler.broadcast(new PlayerDeadPacket(player));
        } else {
            // There was no mine, player is score is incremented
            player.setScore(player.getScore() + 1);
            serverSocketHandler.broadcast(new PlayerScorePacket(player));
        }

        if (serverGameState.isGameFinished()) {
            ScheduleGame.getInstance().schedule();
        }
    }
}
