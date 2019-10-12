package fr.mathieubour.minesweeper.client.states;

public class ServerState {
    private static ServerState instance;
    private String ip;
    private int port;
    private float ping;
    private int maxPlayers;

    public static synchronized ServerState getInstance() {
        if (instance == null) {
            instance = new ServerState();
        }
        return instance;
    }

    public float getPing() {
        return ping;
    }

    public void setPing(float ping) {
        this.ping = ping;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }
}
