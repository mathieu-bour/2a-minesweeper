package fr.mathieubour.minesweeper.packets;

import fr.mathieubour.minesweeper.game.Player;

public class PlayerColorPacket extends PlayerPacket {
    public PlayerColorPacket(Player player) {
        super(player);
    }
}
