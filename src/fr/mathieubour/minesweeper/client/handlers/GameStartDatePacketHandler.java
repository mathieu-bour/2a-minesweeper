package fr.mathieubour.minesweeper.client.handlers;

import fr.mathieubour.minesweeper.client.Client;
import fr.mathieubour.minesweeper.client.panels.CountdownPanel;
import fr.mathieubour.minesweeper.client.states.ClientState;
import fr.mathieubour.minesweeper.client.states.ClientView;
import fr.mathieubour.minesweeper.client.views.WaitingView;
import fr.mathieubour.minesweeper.packets.GameStartDatePacket;

public class GameStartDatePacketHandler {
    private static GameStartDatePacketHandler instance;

    private GameStartDatePacketHandler() {
    }

    public static GameStartDatePacketHandler getInstance() {
        if (instance == null) {
            instance = new GameStartDatePacketHandler();
        }

        return instance;
    }

    public void handle(GameStartDatePacket packet) {
        CountdownPanel.getInstance().setCountdown(packet.getTimestampMs());
        CountdownPanel.getInstance().setDescription("Waiting for other players");

        if (ClientState.getInstance().getView() == ClientView.GAME) {
            // Game ended
            Client.getInstance().setView(WaitingView.getInstance());
        }
    }
}
