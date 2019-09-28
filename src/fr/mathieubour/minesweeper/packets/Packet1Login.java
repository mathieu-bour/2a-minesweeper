package fr.mathieubour.minesweeper.packets;

import fr.mathieubour.minesweeper.game.Player;

public class Packet1Login implements Packet {
    private Player player;

    Packet1Login(Player player) {
        this.player = player;
    }
}
