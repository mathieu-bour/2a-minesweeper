package fr.mathieubour.minesweeper.client.network;

import fr.mathieubour.minesweeper.client.handlers.*;
import fr.mathieubour.minesweeper.client.panels.FieldPanel;
import fr.mathieubour.minesweeper.client.states.ClientGameState;
import fr.mathieubour.minesweeper.client.states.ServerState;
import fr.mathieubour.minesweeper.client.ui.TileButton;
import fr.mathieubour.minesweeper.game.Player;
import fr.mathieubour.minesweeper.game.Tile;
import fr.mathieubour.minesweeper.packets.*;
import fr.mathieubour.minesweeper.utils.Log;

/**
 * Handle incoming packets.
 * Will always run in the ClientInputThread.
 *
 * @see ClientInputThread
 */
public class ClientPacketHandler {
    private static ClientPacketHandler instance;

    public static synchronized ClientPacketHandler getInstance() {
        if (instance == null) {
            instance = new ClientPacketHandler();
        }
        return instance;
    }

    void handle(Packet abstractPacket) {
        Log.packet("Handling", abstractPacket);

        if (abstractPacket instanceof PingPacket) {
            // Handle ping packet
            PingPacket pingPacket = (PingPacket) abstractPacket;
            float ping = System.currentTimeMillis() - pingPacket.getCurrentTimeMillis();
            ServerState.getInstance().setPing(ping);
            Log.info("Current ping " + ping + "ms");
        } else if (abstractPacket instanceof PlayerLoggedPacket) {
            PlayerLoggedPacketHandler.getInstance().handle((PlayerLoggedPacket) abstractPacket);
        } else if (abstractPacket instanceof PlayerListPacket) {
            PlayerListPacketHandler.getInstance().handle((PlayerListPacket) abstractPacket);
        } else if (abstractPacket instanceof GameStartDatePacket) {
            GameStartDatePacketHandler.getInstance().handle((GameStartDatePacket) abstractPacket);
        } else if (abstractPacket instanceof GameStartPacket) {
            GameStartPacketHandler.getInstance().handle((GameStartPacket) abstractPacket);
        } else if (abstractPacket instanceof TileRevealPacket) {
            TileRevealPacket tileRevealPacket = (TileRevealPacket) abstractPacket;
            Tile tile = ClientGameState.getInstance()
                .getField()
                .getTileMatrix()
                .get(tileRevealPacket.getX(), tileRevealPacket.getY());
            Player sweeper = ClientGameState.getInstance().getPlayers().get(tileRevealPacket.getSweeperId());
            TileButton tileButton = FieldPanel.getInstance()
                .getTileButtonMatrix()
                .get(tileRevealPacket.getX(), tileRevealPacket.getY());

            tile.setStatus(tileRevealPacket.getStatus());
            tile.setBombsAround(tileRevealPacket.getBombsAround());
            tile.setSweeper(sweeper);
            tileButton.setTile(tile);
            tileButton.redraw();
        } else if (abstractPacket instanceof PlayerScorePacket) {
            PlayerScorePacketHandler.getInstance().handle((PlayerScorePacket) abstractPacket);
        } else if (abstractPacket instanceof PlayerDeadPacket) {
            PlayerDeadPacketHandler.getInstance().handle((PlayerDeadPacket) abstractPacket);
        } else if (abstractPacket instanceof PlayerColorPacket) {
            PlayerColorPacketHandler.getInstance().handle((PlayerColorPacket) abstractPacket);
        } else if (abstractPacket instanceof PlayerMessagePacket) {
            PlayerMessagePacketHandler.getInstance().handle((PlayerMessagePacket) abstractPacket);
        } else {
            Log.packet("Unhandled", abstractPacket);
        }

        Log.packet("Handled", abstractPacket);
    }
}
