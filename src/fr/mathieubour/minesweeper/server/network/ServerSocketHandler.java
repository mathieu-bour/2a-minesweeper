package fr.mathieubour.minesweeper.server.network;

import fr.mathieubour.minesweeper.packets.Packet;
import fr.mathieubour.minesweeper.server.Server;
import fr.mathieubour.minesweeper.utils.Log;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class ServerSocketHandler {
    private static ServerSocketHandler instance;
    private final Vector<ServerInputThread> clientThreads = new Vector<>();

    public static ServerSocketHandler getInstance() {
        if (instance == null) {
            instance = new ServerSocketHandler();
        }

        return instance;
    }

    public Vector<ServerInputThread> getClientThreads() {
        return clientThreads;
    }

    /**
     * Start the server and listen for client socket connections.
     *
     * @throws IOException When a the connection to a client is lost.
     */
    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(Server.PORT);

        Log.info("Starting Minesweeper on port " + Server.PORT);

        Socket clientSocket = null;
        ServerInputThread clientThread = null;

        while (true) {
            try {
                clientSocket = serverSocket.accept();
                Log.info("Accepting new connection");

                clientThread = new ServerInputThread(clientSocket);
                clientThreads.add(clientThread);
                clientThread.start();

                Log.info("There are " + clientThreads.size() + " client threads");
            } catch (IOException e) {
                if (clientSocket != null) {
                    clientSocket.close();
                }

                if (clientThread != null) {
                    clientThread.interrupt();
                    clientThreads.remove(clientThread);
                }
            }
        }
    }

    /**
     * Broadcast a packet to all connected clients.
     *
     * @param packet The packet to broadcast
     */
    public synchronized void broadcast(Packet packet) {
        Log.packet("Broadcasting to " + clientThreads.size() + " clients", packet);
        clientThreads.forEach(serverInputThread -> serverInputThread.send(packet));
        Log.packet("Broadcasted", packet);
    }
}
