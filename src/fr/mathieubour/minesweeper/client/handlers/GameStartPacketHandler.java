package fr.mathieubour.minesweeper.client.handlers;

import fr.mathieubour.minesweeper.client.Client;
import fr.mathieubour.minesweeper.client.panels.FieldPanel;
import fr.mathieubour.minesweeper.client.states.ClientGameState;
import fr.mathieubour.minesweeper.client.views.GameView;
import fr.mathieubour.minesweeper.game.Field;
import fr.mathieubour.minesweeper.game.Level;
import fr.mathieubour.minesweeper.packets.GameStartPacket;

public class GameStartPacketHandler {
    private static GameStartPacketHandler instance;

    private GameStartPacketHandler() {
    }

    public static GameStartPacketHandler getInstance() {
        if (instance == null) {
            instance = new GameStartPacketHandler();
        }

        return instance;
    }

    public void handle(GameStartPacket packet) {
        ClientGameState gameState = ClientGameState.getInstance();
        Level level = new Level(
            "Custom",
            packet.getRows(),
            packet.getColumns(),
            packet.getMineCount()
        );
        gameState.setField(new Field(level));
        gameState.setPlayers(packet.getPlayers());

        // Swap the panel
        FieldPanel.getInstance().redraw();
        Client.getInstance().setView(GameView.getInstance());
    }
}
