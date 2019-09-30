package fr.mathieubour.minesweeper.packets;

public class TileRequestPacket extends CoordinatePacket {
    public TileRequestPacket(int x, int y) {
        super(x, y);
    }
}
