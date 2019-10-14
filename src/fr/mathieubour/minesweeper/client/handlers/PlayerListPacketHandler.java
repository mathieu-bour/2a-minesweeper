package fr.mathieubour.minesweeper.client.handlers;

import fr.mathieubour.minesweeper.client.Client;
import fr.mathieubour.minesweeper.client.panels.ScoreboardPanel;
import fr.mathieubour.minesweeper.client.panels.VotesPanel;
import fr.mathieubour.minesweeper.client.states.ClientGameState;
import fr.mathieubour.minesweeper.client.states.ClientState;
import fr.mathieubour.minesweeper.client.views.WaitingView;
import fr.mathieubour.minesweeper.packets.PlayerListPacket;

public class PlayerListPacketHandler {
    private static PlayerListPacketHandler instance;

    public static synchronized PlayerListPacketHandler getInstance() {
        if (instance == null) {
            instance = new PlayerListPacketHandler();
        }

        return instance;
    }

    public synchronized void handle(PlayerListPacket packet) {
        ClientGameState gameState = ClientGameState.getInstance();
        gameState.setPlayers(packet.getPlayers());

        switch (ClientState.getInstance().getView()) {
            case HOME:
                Client.getInstance().setView(WaitingView.getInstance());
                break;
            case GAME:
                ScoreboardPanel.getInstance().redraw();
                break;
            case WAITING:
                VotesPanel.getInstance().redraw();
                break;
        }

        // Client.getInstance().setView(WaitingView.getInstance());
    }
}
