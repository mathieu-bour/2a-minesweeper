package fr.mathieubour.minesweeper.packets;

abstract class CoordinatePacket extends Packet {
    private int x;
    private int y;

    CoordinatePacket(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
