package fr.mathieubour.minesweeper.client.network;

import fr.mathieubour.minesweeper.client.panels.FieldPanel;
import fr.mathieubour.minesweeper.client.panels.GamePanel;
import fr.mathieubour.minesweeper.client.states.GameState;
import fr.mathieubour.minesweeper.client.states.NetworkState;
import fr.mathieubour.minesweeper.client.states.PlayerState;
import fr.mathieubour.minesweeper.client.ui.GameFrame;
import fr.mathieubour.minesweeper.client.ui.TileButton;
import fr.mathieubour.minesweeper.game.Field;
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

    void handle(Packet packet) {
        Log.packet("Handling", packet);

        if (packet instanceof PingPacket) {
            // Handle ping packet
            PingPacket pingPacket = (PingPacket) packet;
            float ping = System.currentTimeMillis() - pingPacket.getCurrentTimeMillis();
            NetworkState.getInstance().setPing(ping);
            Log.info("Current ping " + ping + "ms");
        } else if (packet instanceof PlayerLoggedPacket) {
            PlayerLoggedPacket playerLoggedPacket = (PlayerLoggedPacket) packet;
            PlayerState.getInstance().setPlayer(playerLoggedPacket.getPlayer());
        } else if (packet instanceof NewGamePacket) {
            // New game started
            NewGamePacket newGamePacket = (NewGamePacket) packet;
            GameState gameState = GameState.getInstance();
            gameState.setField(new Field(
                newGamePacket.getRows(),
                newGamePacket.getColumns(),
                newGamePacket.getMinesCount()
            ));
            gameState.setPlayers(newGamePacket.getPlayers());

            // Swap the panel
            GameFrame.getInstance().setContentPane(GamePanel.getInstance());
            GameFrame.getInstance().revalidate();
        } else if (packet instanceof TileRevealPacket) {
            TileRevealPacket tileRevealPacket = (TileRevealPacket) packet;
            Tile tile = GameState.getInstance()
                .getField()
                .getTileMatrix()
                .get(tileRevealPacket.getX(), tileRevealPacket.getY());
            Player sweeper = GameState.getInstance().getPlayers().get(tileRevealPacket.getSweeperId());
            TileButton tileButton = FieldPanel.getInstance()
                .getTileButtonMatrix()
                .get(tileRevealPacket.getX(), tileRevealPacket.getY());

            tile.setStatus(tileRevealPacket.getStatus());
            tile.setBombsAround(tileRevealPacket.getBombsAround());
            tile.setSweeper(sweeper);
            tileButton.setTile(tile);
            tileButton.redraw();
        } else if (packet instanceof PlayerScorePacket) {
            PlayerScorePacket playerScorePacket = (PlayerScorePacket) packet;
            Player packetPlayer = playerScorePacket.getPlayer();
            Player player = GameState.getInstance().getPlayers().get(packetPlayer.getId());
            player.setScore(packetPlayer.getScore());
        } else {
            Log.packet("Unhandled", packet);
        }

        Log.packet("Handling", packet);
    }
}
