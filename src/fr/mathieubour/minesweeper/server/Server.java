package fr.mathieubour.minesweeper.server;

import fr.mathieubour.minesweeper.server.network.ServerSocketHandler;

import java.io.IOException;

public class Server {
    public static final int MAX_PLAYERS = 4;
    public static final int PORT = 4200;

    public static void main(String[] args) throws IOException {
        ServerSocketHandler.getInstance().start();
    }
}
