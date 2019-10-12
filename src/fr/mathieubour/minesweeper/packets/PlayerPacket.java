package fr.mathieubour.minesweeper.packets;

import fr.mathieubour.minesweeper.game.Player;

public abstract class PlayerPacket extends Packet {
    private final Player player;

    PlayerPacket(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
