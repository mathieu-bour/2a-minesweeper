package fr.mathieubour.minesweeper.packets;

abstract class DatePacket extends Packet {
    private final float timestampMs;

    DatePacket(float timestampMs) {
        this.timestampMs = timestampMs;
    }

    public float getTimestampMs() {
        return timestampMs;
    }
}
