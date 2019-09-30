package fr.mathieubour.minesweeper.packets;

import fr.mathieubour.minesweeper.game.Player;

public class PlayerScorePacket extends PlayerPacket {
    public PlayerScorePacket(Player player) {
        super(player);
    }
}
