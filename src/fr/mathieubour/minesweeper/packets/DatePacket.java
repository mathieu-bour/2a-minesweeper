package fr.mathieubour.minesweeper.packets;

abstract class DatePacket extends Packet {
    private final long timestampMs;

    DatePacket(long timestampMs) {
        this.timestampMs = timestampMs;
    }

    public long getTimestampMs() {
        return timestampMs;
    }
}
