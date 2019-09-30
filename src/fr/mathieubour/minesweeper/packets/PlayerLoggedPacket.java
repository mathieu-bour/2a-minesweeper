package fr.mathieubour.minesweeper.packets;

import fr.mathieubour.minesweeper.game.Player;

public class PlayerLoggedPacket extends PlayerPacket {
    public PlayerLoggedPacket(Player player) {
        super(player);
    }
}
