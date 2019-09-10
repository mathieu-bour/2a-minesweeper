package fr.mathieubour.minesweeper.client.network;

import fr.mathieubour.minesweeper.packets.Packet;

public class ClientPacketHandler {
    public static void send(Packet packet) {
        String payload = packet.toString();
    }
}
