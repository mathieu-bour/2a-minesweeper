package fr.mathieubour.minesweeper.packets;

import fr.mathieubour.minesweeper.game.Player;

import java.util.HashMap;

public class PlayerListPacket extends Packet {
    private HashMap<String, Player> players;

    public PlayerListPacket(HashMap<String, Player> players) {
        this.players = players;
    }
}
