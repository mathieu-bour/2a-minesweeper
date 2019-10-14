package fr.mathieubour.minesweeper.client.handlers;

import fr.mathieubour.minesweeper.client.states.ClientGameState;
import fr.mathieubour.minesweeper.client.states.PlayerState;
import fr.mathieubour.minesweeper.packets.PlayerLoggedPacket;

public class PlayerLoggedPacketHandler {
    private static PlayerLoggedPacketHandler instance;

    private PlayerLoggedPacketHandler() {
    }

    public static PlayerLoggedPacketHandler getInstance() {
        if (instance == null) {
            instance = new PlayerLoggedPacketHandler();
        }

        return instance;
    }

    /**
     * 1. Set the current user based on the packet information (id, color...)
     * 2. Link the player references
     *
     * @param packet The PlayerLoggedPacket received from the server.
     * @see PlayerLoggedPacket
     */
    public synchronized void handle(PlayerLoggedPacket packet) {
        PlayerState.getInstance()
            .getPlayer()
            .setFrom(packet.getPlayer());

        // Link the PlayerState <=> ClientGameState player references.
        ClientGameState.getInstance()
            .getPlayers()
            .put(packet.getPlayer().getId(), PlayerState.getInstance().getPlayer());
    }
}
