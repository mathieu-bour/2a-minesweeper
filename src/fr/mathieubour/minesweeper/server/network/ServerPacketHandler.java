package fr.mathieubour.minesweeper.server.network;

import fr.mathieubour.minesweeper.packets.*;
import fr.mathieubour.minesweeper.server.handlers.PlayerLoginPacketHandler;
import fr.mathieubour.minesweeper.server.handlers.PlayerMessagePacketHandler;
import fr.mathieubour.minesweeper.server.handlers.PlayerVotePacketHandler;
import fr.mathieubour.minesweeper.server.handlers.TileRequestPacketHandler;
import fr.mathieubour.minesweeper.utils.Log;

public class ServerPacketHandler {
    private static ServerPacketHandler instance;

    public static synchronized ServerPacketHandler getInstance() {
        if (instance == null) {
            instance = new ServerPacketHandler();
        }
        return instance;
    }

    synchronized void handle(Packet packet, ServerInputThread sourceThread) {
        if (sourceThread.getPlayer() != null) {
            Log.packet("[" + sourceThread.getPlayer().getName() + "] Handling", packet);
        } else {
            Log.packet("Handling", packet);
        }

        if (packet instanceof PingPacket) {
            sourceThread.send(packet); // Re-send the pack as-is
        } else if (packet instanceof PlayerLoginPacket) {
            PlayerLoginPacketHandler.getInstance().handle((PlayerLoginPacket) packet, sourceThread);
        } else if (packet instanceof PlayerMessagePacket) {
            PlayerMessagePacketHandler.getInstance().handle((PlayerMessagePacket) packet);
        } else if (packet instanceof TileRequestPacket) {
            TileRequestPacketHandler.getInstance().handle((TileRequestPacket) packet, sourceThread);
        } else if (packet instanceof PlayerVotePacket) {
            PlayerVotePacketHandler.getInstance().handle((PlayerVotePacket) packet, sourceThread);
        } else {
            Log.packet("Unhandled", packet);
        }
    }
}
