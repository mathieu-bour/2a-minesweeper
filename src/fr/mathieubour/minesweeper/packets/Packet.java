package fr.mathieubour.minesweeper.packets;

import java.io.Serializable;
import java.util.UUID;

public abstract class Packet implements Serializable {
    private String id = UUID.randomUUID().toString();

    public String getId() {
        return id;
    }
}
