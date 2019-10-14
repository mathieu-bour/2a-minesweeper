package fr.mathieubour.minesweeper.packets;

import fr.mathieubour.minesweeper.game.Field;
import fr.mathieubour.minesweeper.game.Player;

import java.util.HashMap;

public class GameStartPacket extends Packet {
    private final int rows;
    private final int columns;
    private final int mineCount;
    private final HashMap<String, Player> players;

    public GameStartPacket(Field field, HashMap<String, Player> players) {
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

    public HashMap<String, Player> getPlayers() {
        return players;
    }
}
