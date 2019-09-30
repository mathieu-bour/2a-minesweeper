package fr.mathieubour.minesweeper.packets;

public class PingPacket extends Packet {
    private long currentTimeMillis;

    public PingPacket() {
        this.currentTimeMillis = System.currentTimeMillis();
    }

    public long getCurrentTimeMillis() {
        return currentTimeMillis;
    }
}
