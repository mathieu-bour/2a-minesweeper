package fr.mathieubour.minesweeper.client.handlers;

import fr.mathieubour.minesweeper.client.panels.ChatPanel;
import fr.mathieubour.minesweeper.client.states.ClientState;
import fr.mathieubour.minesweeper.client.states.ClientView;
import fr.mathieubour.minesweeper.packets.PlayerMessagePacket;

public class PlayerMessagePacketHandler {
    private static PlayerMessagePacketHandler instance;

    private PlayerMessagePacketHandler() {
    }

    public static PlayerMessagePacketHandler getInstance() {
        if (instance == null) {
            instance = new PlayerMessagePacketHandler();
        }

        return instance;
    }

    public void handle(PlayerMessagePacket packet) {
        if (ClientState.getInstance().getView() != ClientView.WAITING) {
            // Drop the packet
            return;
        }

        ChatPanel.getInstance().appendMessage(packet.getMessage());
    }
}
