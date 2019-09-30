package fr.mathieubour.minesweeper.client.panels;

import fr.mathieubour.minesweeper.client.network.ClientSocketHandler;
import fr.mathieubour.minesweeper.client.states.PlayerState;
import fr.mathieubour.minesweeper.game.Player;
import fr.mathieubour.minesweeper.utils.Log;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    private static LoginPanel instance;
    private JTextField usernameText = new JTextField("mathieu", 20);
    private JTextField serverIpText = new JTextField("127.0.0.1:4200", 20);

    public LoginPanel() {
        super(new GridBagLayout());

        // Create GridBagConstraints
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        // Add components to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        JLabel usernameLabel = new JLabel("Username: ");
        add(usernameLabel, constraints);

        constraints.gridx = 1;
        add(usernameText, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        JLabel serverIpLabel = new JLabel("Server IP: ");
        add(serverIpLabel, constraints);

        constraints.gridx = 1;
        add(serverIpText, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        JButton loginButton = new JButton("Login");
        add(loginButton, constraints);

        loginButton.addActionListener(actionEvent -> {
            Log.info("lol");
            String ip = serverIpText.getText();
            int port = 4200; // Default port

            if (ip.indexOf(':') > -1) {
                // ip has embedded port (IP:PORT)
                String[] arr = ip.split(":");
                ip = arr[0];
                try {
                    port = Integer.parseInt(arr[1]);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }

            PlayerState.getInstance().setPlayer(new Player(usernameText.getText()));
            ClientSocketHandler.getInstance().connect(ip, port);
        });
    }

    public static synchronized LoginPanel getInstance() {
        if (instance == null) {
            instance = new LoginPanel();
        }

        return instance;
    }
}
