package fr.mathieubour.minesweeper.server;

import fr.mathieubour.minesweeper.server.network.ServerSocketHandler;

import java.io.IOException;

public class Server {
    public static int WAITING_COUNTDOWN = 60; // seconds
    public static int MIN_PLAYERS = 2;
    public static int MAX_PLAYERS = 4;
    public static int PORT = 4200;

    public static void main(String[] args) throws IOException {
        ServerSocketHandler.getInstance().start();
    }
}
