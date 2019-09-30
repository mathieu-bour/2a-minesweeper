package fr.mathieubour.minesweeper.packets;

import fr.mathieubour.minesweeper.game.Player;

public class PlayerLoginPacket extends PlayerPacket {
    public PlayerLoginPacket(Player player) {
        super(player);
    }
}
