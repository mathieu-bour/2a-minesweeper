package fr.mathieubour.minesweeper.client.network;

import fr.mathieubour.minesweeper.game.Player;
import fr.mathieubour.minesweeper.packets.Packet;

import java.util.Objects;

public class ClientPacketHandler {
    private static ClientPacketHandler instance = null;
    private Player player;

    private ClientPacketHandler() {
        ClientPacketHandler.instance = this;
    }

    public static ClientPacketHandler getInstance() {
        return Objects.requireNonNullElseGet(ClientPacketHandler.instance, ClientPacketHandler::new);
    }

    public void send(Packet packet) {
        String payload = packet.toString();
    }
}
