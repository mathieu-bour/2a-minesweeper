package fr.mathieubour.minesweeper.client.ui;

import javax.swing.*;
import java.util.HashMap;

public class GameMenu extends JMenuBar {
    public static GameMenu INSTANCE;
    HashMap<String, JMenuItem> triggers = new HashMap<>();

    GameMenu() {
        GameMenu.INSTANCE = this;
        JMenu serverMenu = new JMenu("Server");
        JMenuItem connectMenuItem = new JMenuItem("Connect");
        JMenuItem disconnectMenuItem = new JMenuItem("Disconnect");

        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItemMenu = new JMenuItem("Exit");

        serverMenu.add(connectMenuItem);
        serverMenu.add(disconnectMenuItem);
        fileMenu.add(exitItemMenu);

        this.add(serverMenu);
        this.add(fileMenu);

        this.triggers.put("connect", connectMenuItem);
        this.triggers.put("disconnect", disconnectMenuItem);
        this.triggers.put("exit", exitItemMenu);
    }
}
