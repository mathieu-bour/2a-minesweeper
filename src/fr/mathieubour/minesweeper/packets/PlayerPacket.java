package fr.mathieubour.minesweeper.packets;

import fr.mathieubour.minesweeper.game.Player;

public abstract class PlayerPacket extends Packet {
    private Player player;

    PlayerPacket(Player player) {
        try {
            this.player = player.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public Player getPlayer() {
        return player;
    }
}
