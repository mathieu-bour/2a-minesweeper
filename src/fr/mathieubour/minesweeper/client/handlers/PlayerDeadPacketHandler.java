package fr.mathieubour.minesweeper.client.handlers;

import fr.mathieubour.minesweeper.client.panels.ScoreboardPanel;
import fr.mathieubour.minesweeper.client.states.ClientState;
import fr.mathieubour.minesweeper.client.states.ClientView;
import fr.mathieubour.minesweeper.client.states.PlayerState;
import fr.mathieubour.minesweeper.packets.PlayerDeadPacket;

public class PlayerDeadPacketHandler {
    private static PlayerDeadPacketHandler instance;

    private PlayerDeadPacketHandler() {
    }

    public static PlayerDeadPacketHandler getInstance() {
        if (instance == null) {
            instance = new PlayerDeadPacketHandler();
        }

        return instance;
    }

    public synchronized void handle(PlayerDeadPacket packet) {
        if (ClientState.getInstance().getView() == ClientView.GAME) {
            String playerId = packet.getPlayer().getId();

            ScoreboardPanel.getInstance()
                .getScorePanels()
                .get(playerId)
                .setAlive(false);

            if (PlayerState.getInstance().getPlayer().getId().equals(playerId)) {
                // The current player is dead
                PlayerState.getInstance().getPlayer().setAlive(false);
            }
        }
    }
}
