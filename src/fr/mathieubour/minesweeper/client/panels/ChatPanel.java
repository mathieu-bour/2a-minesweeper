package fr.mathieubour.minesweeper.client.panels;

import fr.mathieubour.minesweeper.client.network.ClientSocketHandler;
import fr.mathieubour.minesweeper.client.states.PlayerState;
import fr.mathieubour.minesweeper.packets.PlayerMessagePacket;

import javax.swing.*;
import java.awt.*;

public class ChatPanel extends JPanel {
    private static ChatPanel instance;

    private JTextArea chatTextArea = new JTextArea();
    private JScrollPane chatPane = new JScrollPane(chatTextArea);
    private JTextField messageForm = new JTextField();

    private ChatPanel() {
        super(new GridBagLayout());
        redraw();
        register();
    }

    public static ChatPanel getInstance() {
        if (instance == null) {
            instance = new ChatPanel();
        }

        return instance;
    }

    public void redraw() {
        removeAll();
        setBackground(Color.RED);
        chatTextArea.setEditable(false);
        chatTextArea.setLineWrap(true);
        chatPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        add(chatPane, constraints);

        constraints.gridy = 1;
        constraints.weightx = 1;
        constraints.weighty = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        add(messageForm, constraints);
    }

    private void register() {
        messageForm.addActionListener(actionEvent -> {
            String message = messageForm.getText();

            if (message.length() == 0) {
                return;
            }

            ClientSocketHandler.getInstance().send(new PlayerMessagePacket(
                PlayerState.getInstance().getPlayer(),
                message
            ));

            messageForm.setText("");
        });
    }


    public void clearChat() {
        chatTextArea.setText("");
    }

    public void appendMessage(String message) {
        chatTextArea.append(message + "\n");
    }
}
