package fr.mathieubour.minesweeper.packets;

public class GameStartDatePacket extends DatePacket {
    public GameStartDatePacket(long timestampMs) {
        super(timestampMs);
    }
}
