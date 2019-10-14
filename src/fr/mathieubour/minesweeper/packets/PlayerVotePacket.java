package fr.mathieubour.minesweeper.packets;

import fr.mathieubour.minesweeper.game.LevelDifficulty;

public class PlayerVotePacket extends Packet {
    private LevelDifficulty difficulty;

    public PlayerVotePacket(LevelDifficulty difficulty) {
        this.difficulty = difficulty;
    }

    public LevelDifficulty getDifficulty() {
        return difficulty;
    }
}
