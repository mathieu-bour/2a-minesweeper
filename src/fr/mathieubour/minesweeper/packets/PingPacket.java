package fr.mathieubour.minesweeper.packets;

public class PingPacket extends Packet {
    private final long currentTimeMillis;

    public PingPacket() {
        this.currentTimeMillis = System.currentTimeMillis();
    }

    public long getCurrentTimeMillis() {
        return currentTimeMillis;
    }
}
