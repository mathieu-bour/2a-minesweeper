package fr.mathieubour.minesweeper.client.handlers;

import fr.mathieubour.minesweeper.client.panels.VotesPanel;
import fr.mathieubour.minesweeper.client.states.ClientGameState;
import fr.mathieubour.minesweeper.game.Player;
import fr.mathieubour.minesweeper.packets.PlayerColorPacket;

public class PlayerColorPacketHandler {
    private static PlayerColorPacketHandler instance;

    private PlayerColorPacketHandler() {
    }

    public static PlayerColorPacketHandler getInstance() {
        if (instance == null) {
            instance = new PlayerColorPacketHandler();
        }

        return instance;
    }

    public void handle(PlayerColorPacket packet) {
        Player player = ClientGameState.getInstance().getPlayers().get(packet.getPlayer().getId());
        player.setColor(packet.getPlayer().getColor());
        VotesPanel.getInstance().redraw();
    }
}
