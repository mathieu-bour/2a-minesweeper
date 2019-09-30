package fr.mathieubour.minesweeper.packets;

import fr.mathieubour.minesweeper.game.TileStatus;

public class TileRevealPacket extends Packet {
    private int x;
    private int y;
    private TileStatus status;
    private int bombsAround;
    private String sweeperId;

    public TileRevealPacket(int x, int y, TileStatus status, int bombsAround, String sweeperId) {
        this.x = x;
        this.y = y;
        this.status = status;
        this.bombsAround = bombsAround;
        this.sweeperId = sweeperId;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public TileStatus getStatus() {
        return status;
    }

    public int getBombsAround() {
        return bombsAround;
    }

    public String getSweeperId() {
        return sweeperId;
    }
}
