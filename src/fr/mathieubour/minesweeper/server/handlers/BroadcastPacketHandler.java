package fr.mathieubour.minesweeper.server.handlers;

import fr.mathieubour.minesweeper.packets.Packet;
import fr.mathieubour.minesweeper.server.network.ServerSocketHandler;

/**
 * Handle a packet which is meant to be send to everybody.
 */
public class BroadcastPacketHandler {
    private static BroadcastPacketHandler instance;

    public static BroadcastPacketHandler getInstance() {
        if (instance == null) {
            instance = new BroadcastPacketHandler();
        }

        return instance;
    }

    public void handle(Packet packet) {
        ServerSocketHandler.getInstance().broadcast(packet);
    }
}
