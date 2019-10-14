package fr.mathieubour.minesweeper.server.handlers;

import fr.mathieubour.minesweeper.packets.PlayerMessagePacket;
import fr.mathieubour.minesweeper.server.network.ServerSocketHandler;

public class PlayerMessagePacketHandler {
    private static PlayerMessagePacketHandler instance;

    private PlayerMessagePacketHandler() {
    }

    public static PlayerMessagePacketHandler getInstance() {
        if (instance == null) {
            instance = new PlayerMessagePacketHandler();
        }

        return instance;
    }

    public void handle(PlayerMessagePacket packet) {
        ServerSocketHandler.getInstance().broadcast(packet); // Simply re-send the message to everybody
    }
}
