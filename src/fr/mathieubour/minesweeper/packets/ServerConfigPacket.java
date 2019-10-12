package fr.mathieubour.minesweeper.packets;

public class ServerConfigPacket extends Packet {
    private final int maxPlayers;

    public ServerConfigPacket(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }
}
