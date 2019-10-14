package fr.mathieubour.minesweeper.packets;

import fr.mathieubour.minesweeper.game.Field;
import fr.mathieubour.minesweeper.game.Player;

import java.util.concurrent.ConcurrentHashMap;

public class GameStartPacket extends Packet {
    private final int rows;
    private final int columns;
    private final int mineCount;
    private final ConcurrentHashMap<String, Player> players;

    public GameStartPacket(Field field, ConcurrentHashMap<String, Player> players) {
        rows = field.getLevel().getRows();
        columns = field.getLevel().getColumns();
        mineCount = field.getLevel().getMineCount();
        this.players = players;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getMineCount() {
        return mineCount;
    }

    public ConcurrentHashMap<String, Player> getPlayers() {
        return players;
    }
}
