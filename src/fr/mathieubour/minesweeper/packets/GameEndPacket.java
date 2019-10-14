package fr.mathieubour.minesweeper.packets;

import fr.mathieubour.minesweeper.game.Player;

import java.util.HashMap;

public class GameEndPacket extends Packet {
    private HashMap<String, Player> players;

    public GameEndPacket(HashMap<String, Player> players) {
        this.players = players;
    }

    public HashMap<String, Player> getPlayers() {
        return players;
    }
}
