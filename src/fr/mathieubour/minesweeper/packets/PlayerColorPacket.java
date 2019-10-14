package fr.mathieubour.minesweeper.packets;

import fr.mathieubour.minesweeper.game.Player;

public class PlayerColorPacket extends PlayerPacket {
    PlayerColorPacket(Player player) {
        super(player);
    }
}
