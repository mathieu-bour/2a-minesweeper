package fr.mathieubour.minesweeper.game;

import java.io.Serializable;
import java.util.Map;

public class Player implements Serializable {
    private String id;
    private String name;
    private String color;

    Player(String name) {
        this.name = name;
        this.autoDiscoverId();
    }

    Player(String name, String id) {
        this.name = name;
        this.id = id;
    }

    private void autoDiscoverId() {
        Map<String, String> env = System.getenv();

        if (env.containsKey("COMPUTERNAME")) {
            this.id = env.get("COMPUTERNAME").toLowerCase();
        } else {
            this.id = env.getOrDefault("HOSTNAME", "unknown");
        }
    }
}
