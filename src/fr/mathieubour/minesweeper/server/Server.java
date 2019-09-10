package fr.mathieubour.minesweeper.server;

import fr.mathieubour.minesweeper.server.network.ClientThread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        int PORT = 8888;
        ServerSocket serverSocket = new ServerSocket(PORT);

        System.out.println("Starting Mineseeper on port " + PORT);

        while (true) {
            Socket clientSocket = null;
            try {
                clientSocket = serverSocket.accept();
                DataInputStream input = new DataInputStream(clientSocket.getInputStream());
                DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());

                Thread clientThread = new ClientThread(clientSocket, input, output);
                clientThread.start();
            } catch (Exception e) {
                if (clientSocket != null) {
                    clientSocket.close();
                }
                e.printStackTrace();
            }
        }
    }
}
