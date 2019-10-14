package fr.mathieubour.minesweeper.client;

import fr.mathieubour.minesweeper.client.states.ClientState;
import fr.mathieubour.minesweeper.client.states.ClientView;
import fr.mathieubour.minesweeper.client.ui.GameMenu;
import fr.mathieubour.minesweeper.client.views.GameView;
import fr.mathieubour.minesweeper.client.views.HomeView;
import fr.mathieubour.minesweeper.client.views.WaitingView;

import javax.swing.*;

public class Client extends JFrame {
    private static Client instance;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        Client frame = new Client();
    }

    private Client() {
        super("Minesweeper - Home");

        Client.instance = this;

        // Preload icons
        configure();
        setJMenuBar(new GameMenu());

        setContentPane(HomeView.getInstance());
        setVisible(true);
    }

    public static synchronized Client getInstance() {
        if (instance == null) {
            instance = new Client();
        }

        return instance;
    }

    private void configure() {
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    public void setView(JPanel view) {
        if (getContentPane() != view) {
            if (view instanceof HomeView) {
                setSize(800, 600);
                ClientState.getInstance().setView(ClientView.HOME);
            } else if (view instanceof WaitingView) {
                setSize(800, 600);
                ClientState.getInstance().setView(ClientView.WAITING);
            } else if (view instanceof GameView) {
                GameView.getInstance().redraw();
                ClientState.getInstance().setView(ClientView.GAME);
            }

            setContentPane(view);
            revalidate();
        }
    }
}
