package fr.mathieubour.minesweeper.server.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ClientThread extends Thread {
    Socket socket;
    DataInputStream input;
    DataOutputStream output;

    public ClientThread(Socket socket, DataInputStream input, DataOutputStream output) {
        this.socket = socket;
        this.input = input;
        this.output = output;
    }
}
