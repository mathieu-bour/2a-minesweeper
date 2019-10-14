package fr.mathieubour.minesweeper.server.handlers;

import fr.mathieubour.minesweeper.packets.PlayerVotePacket;
import fr.mathieubour.minesweeper.server.network.ServerInputThread;

public class PlayerVotePacketHandler {
    private static PlayerVotePacketHandler instance;

    private PlayerVotePacketHandler() {
    }

    public static PlayerVotePacketHandler getInstance() {
        if (instance == null) {
            instance = new PlayerVotePacketHandler();
        }

        return instance;
    }

    public void handle(PlayerVotePacket packet, ServerInputThread sourceThread) {
        sourceThread.getPlayer().setVote(packet.getDifficulty());
    }
}
