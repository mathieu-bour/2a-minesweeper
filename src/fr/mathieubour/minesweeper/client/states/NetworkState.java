package fr.mathieubour.minesweeper.client.states;

public class NetworkState {
    private static NetworkState instance;
    private float ping;

    public static synchronized NetworkState getInstance() {
        if (instance == null) {
            instance = new NetworkState();
        }
        return instance;
    }

    public float getPing() {
        return ping;
    }

    public void setPing(float ping) {
        this.ping = ping;
    }

}
