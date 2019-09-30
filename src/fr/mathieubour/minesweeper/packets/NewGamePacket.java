package fr.mathieubour.minesweeper.packets;

import fr.mathieubour.minesweeper.game.Field;
import fr.mathieubour.minesweeper.game.Player;

import java.util.HashMap;

public class NewGamePacket extends Packet {
    private int rows;
    private int columns;
    private int minesCount;
    private HashMap<String, Player> players;

    public NewGamePacket(Field field, HashMap<String, Player> players) {
        rows = field.getRows();
        columns = field.getColumns();
        minesCount = field.getMinesCount();
        this.players = players;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getMinesCount() {
        return minesCount;
    }

    public HashMap<String, Player> getPlayers() {
        return players;
    }
}
