package fr.mathieubour.minesweeper.packets;

import fr.mathieubour.minesweeper.game.Player;

import java.util.HashMap;

public class PlayerListPacket extends Packet {
    private final HashMap<String, Player> players = new HashMap<>();

    public PlayerListPacket(HashMap<String, Player> players) {
        players.forEach((uuid, player) -> {
            try {
                this.players.put(uuid, player.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        });
    }

    public HashMap<String, Player> getPlayers() {
        return players;
    }
}
