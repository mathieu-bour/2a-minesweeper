package fr.mathieubour.minesweeper.client.handlers;

import fr.mathieubour.minesweeper.client.Client;
import fr.mathieubour.minesweeper.client.panels.ScoreboardPanel;
import fr.mathieubour.minesweeper.client.states.ClientGameState;
import fr.mathieubour.minesweeper.client.views.GameView;
import fr.mathieubour.minesweeper.packets.PlayerScorePacket;

public class PlayerScorePacketHandler {
    private static PlayerScorePacketHandler instance;

    private PlayerScorePacketHandler() {
    }

    public static PlayerScorePacketHandler getInstance() {
        if (instance == null) {
            instance = new PlayerScorePacketHandler();
        }

        return instance;
    }

    public synchronized void handle(PlayerScorePacket packet) {
        String playerId = packet.getPlayer().getId();
        int playerScore = packet.getPlayer().getScore();
        ClientGameState.getInstance()
            .getPlayers()
            .get(playerId)
            .setScore(playerScore);

        if (Client.getInstance().getContentPane() == GameView.getInstance()) {
            ScoreboardPanel.getInstance()
                .getScorePanels()
                .get(playerId)
                .setPlayerScore(playerScore);
        }
    }
}
