package fr.mathieubour.minesweeper.client.network;

import fr.mathieubour.minesweeper.client.panels.FieldPanel;
import fr.mathieubour.minesweeper.client.panels.GamePanel;
import fr.mathieubour.minesweeper.client.states.GameState;
import fr.mathieubour.minesweeper.client.states.PlayerState;
import fr.mathieubour.minesweeper.client.states.ServerState;
import fr.mathieubour.minesweeper.client.ui.GameFrame;
import fr.mathieubour.minesweeper.client.ui.TileButton;
import fr.mathieubour.minesweeper.game.Field;
import fr.mathieubour.minesweeper.game.Level;
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
        } else if (abstractPacket instanceof ServerConfigPacket) {
            // Received server config
            ServerConfigPacket packet = (ServerConfigPacket) abstractPacket;
            ServerState.getInstance().setMaxPlayers(packet.getMaxPlayers());
        } else if (abstractPacket instanceof PlayerLoggedPacket) {
            // Received player profile
            PlayerLoggedPacket playerLoggedPacket = (PlayerLoggedPacket) abstractPacket;
            PlayerState.getInstance().setPlayer(playerLoggedPacket.getPlayer());
        } else if (abstractPacket instanceof NewGamePacket) {
            // New game started
            NewGamePacket newGamePacket = (NewGamePacket) abstractPacket;
            GameState gameState = GameState.getInstance();
            Level level = new Level(
                newGamePacket.getRows(),
                newGamePacket.getColumns(),
                newGamePacket.getMineCount()
            );
            gameState.setField(new Field(level));
            gameState.setPlayers(newGamePacket.getPlayers());

            // Swap the panel
            GameFrame.getInstance().setContentPane(GamePanel.getInstance());
            GameFrame.getInstance().revalidate();
        } else if (abstractPacket instanceof TileRevealPacket) {
            TileRevealPacket tileRevealPacket = (TileRevealPacket) abstractPacket;
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
        } else if (abstractPacket instanceof PlayerScorePacket) {
            PlayerScorePacket playerScorePacket = (PlayerScorePacket) abstractPacket;
            Player packetPlayer = playerScorePacket.getPlayer();
            Player player = GameState.getInstance().getPlayers().get(packetPlayer.getId());
            player.setScore(packetPlayer.getScore());
        } else {
            Log.packet("Unhandled", abstractPacket);
        }

        Log.packet("Handling", abstractPacket);
    }
}
