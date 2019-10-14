package fr.mathieubour.minesweeper.client.panels;

import fr.mathieubour.minesweeper.client.network.ClientSocketHandler;
import fr.mathieubour.minesweeper.client.states.PlayerState;
import fr.mathieubour.minesweeper.client.states.ServerState;
import fr.mathieubour.minesweeper.client.ui.InsetsUtils;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class LoginPanel extends JPanel {
    private static LoginPanel instance;
    private final JLabel errorLabel = new JLabel("");

    private LoginPanel() {
        super(new GridBagLayout());
        draw();
    }

    public static synchronized LoginPanel getInstance() {
        if (instance == null) {
            instance = new LoginPanel();
        }

        return instance;
    }

    private void draw() {
        // Create GridBagConstraints
        AtomicInteger y = new AtomicInteger(0);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = InsetsUtils.MARGIN_BOTTOM_1;
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
        JLabel serverIpLabel = new JLabel("Server IP: ");
        add(serverIpLabel, constraints);

        constraints.gridy = y.getAndIncrement();
        JTextField serverIpText = new JTextField("127.0.0.1:4200", 20);
        add(serverIpText, constraints);

        constraints.gridy = y.getAndIncrement();
        constraints.insets = InsetsUtils.MARGIN_BOTTOM_2;
        errorLabel.setForeground(Color.RED);
        add(errorLabel, constraints);

        constraints.gridy = y.getAndIncrement();
        constraints.insets = InsetsUtils.MARGIN_BOTTOM_2;
        JButton loginButton = new JButton("Login");
        add(loginButton, constraints);

        loginButton.addActionListener(actionEvent -> {
            errorLabel.setText("");

            PlayerState.getInstance().getPlayer().setName(usernameText.getText());

            if (!ServerState.getInstance().isConnected()) {
                try {
                    ClientSocketHandler.getInstance().connect(serverIpText.getText());
                } catch (IOException exception) {
                    errorLabel.setText(exception.getMessage());
                }
            }
        });
    }
}
