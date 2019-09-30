package fr.mathieubour.minesweeper.packets;

import fr.mathieubour.minesweeper.game.Player;

/**
 * Sent when a player is dead
 */
public class PlayerDeadPacket extends Packet {
    private Player player;

    public PlayerDeadPacket(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
