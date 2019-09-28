package fr.mathieubour.minesweeper.client.panels;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {
    public HomePanel() {
        draw();
    }

    void draw() {
        setLayout(new BorderLayout());
        add(new JLabel("Enter the IP address"), BorderLayout.CENTER);
        add(new JTextField("127.0.0.1:8080"), BorderLayout.CENTER);
    }
}
