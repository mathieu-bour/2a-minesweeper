package fr.mathieubour.minesweeper.client.panels;

import fr.mathieubour.minesweeper.client.network.ClientSocketHandler;
import fr.mathieubour.minesweeper.client.states.PlayerState;
import fr.mathieubour.minesweeper.client.ui.InsetsUtils;
import fr.mathieubour.minesweeper.game.Level;
import fr.mathieubour.minesweeper.game.Player;
import fr.mathieubour.minesweeper.server.Server;
import fr.mathieubour.minesweeper.server.network.ServerSocketHandler;
import fr.mathieubour.minesweeper.server.states.ServerGameState;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class OfflinePanel extends JPanel {
    private static OfflinePanel instance;

    private OfflinePanel() {
        super(new GridBagLayout());
        draw();
    }

    public static OfflinePanel getInstance() {
        if (instance == null) {
            instance = new OfflinePanel();
        }

        return instance;
    }

    private void draw() {
        AtomicInteger y = new AtomicInteger(0);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridx = 0;

        constraints.gridy = y.getAndIncrement();
        JLabel usernameLabel = new JLabel("Username: ");
        add(usernameLabel, constraints);

        constraints.gridy = y.getAndIncrement();
        constraints.insets = InsetsUtils.MARGIN_BOTTOM_2;
        JTextField usernameText = new JTextField("mathieu", 20);
        add(usernameText, constraints);


        constraints.gridy = y.getAndIncrement();
        constraints.insets = InsetsUtils.MARGIN_BOTTOM_1;
        add(new JLabel("Select difficulty"), constraints);

        constraints.gridy = y.getAndIncrement();
        constraints.insets = InsetsUtils.MARGIN_BOTTOM_2;
        JComboBox<Level> comboBox = new JComboBox<>(Level.ALL);
        add(comboBox, constraints);

        constraints.gridy = y.getAndIncrement();
        JButton playOfflineButton = new JButton("Play offline");
        add(playOfflineButton, constraints);

        playOfflineButton.addActionListener(actionEvent -> {
            Level level = (Level) comboBox.getSelectedItem();
            Thread localServerThread = new Thread(() -> {
                try {
                    ServerSocketHandler.getInstance().start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            Server.WAITING_COUNTDOWN = 0;
            Server.MIN_PLAYERS = 1;
            Server.MAX_PLAYERS = 1;

            ServerGameState.getInstance().newGame(level);
            localServerThread.start();

            PlayerState.getInstance().setPlayer(new Player(usernameText.getText()));
            try {
                Thread.sleep(1000);
                ClientSocketHandler.getInstance().connect("localhost:4200");
            } catch (IOException | InterruptedException ignored) {
            }
        });
    }
}
