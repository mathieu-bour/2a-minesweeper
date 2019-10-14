package fr.mathieubour.minesweeper.packets;

import fr.mathieubour.minesweeper.game.Player;

import java.util.concurrent.ConcurrentHashMap;

public class PlayerListPacket extends Packet {
    private final ConcurrentHashMap<String, Player> players = new ConcurrentHashMap<>();

    public PlayerListPacket(ConcurrentHashMap<String, Player> players) {
        players.forEach((uuid, player) -> {
            try {
                this.players.put(uuid, player.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        });
    }

    public ConcurrentHashMap<String, Player> getPlayers() {
        return players;
    }
}
