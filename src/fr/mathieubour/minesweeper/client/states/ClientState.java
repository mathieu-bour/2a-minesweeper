package fr.mathieubour.minesweeper.client.states;

public class ClientState {
    private static ClientState instance;
    private ClientView view = ClientView.HOME;

    public synchronized static ClientState getInstance() {
        if (instance == null) {
            instance = new ClientState();
        }

        return instance;
    }

    public ClientView getView() {
        return view;
    }

    public void setView(ClientView view) {
        this.view = view;
    }
}
