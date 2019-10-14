package fr.mathieubour.minesweeper.packets;

import fr.mathieubour.minesweeper.game.Player;

public class PlayerMessagePacket extends PlayerPacket {
    private String message;

    public PlayerMessagePacket(Player player, String message) {
        super(player);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
