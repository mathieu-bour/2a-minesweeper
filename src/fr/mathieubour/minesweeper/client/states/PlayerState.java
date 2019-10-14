package fr.mathieubour.minesweeper.client.states;

import fr.mathieubour.minesweeper.game.Player;

public class PlayerState {
    private static PlayerState instance;
    private Player player = new Player("default");

    public static synchronized PlayerState getInstance() {
        if (instance == null) {
            instance = new PlayerState();
        }
        return instance;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player.setFrom(player);
    }
}
