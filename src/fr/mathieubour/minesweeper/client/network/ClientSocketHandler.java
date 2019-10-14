package fr.mathieubour.minesweeper.client.network;

import fr.mathieubour.minesweeper.client.states.PlayerState;
import fr.mathieubour.minesweeper.client.states.ServerState;
import fr.mathieubour.minesweeper.packets.Packet;
import fr.mathieubour.minesweeper.packets.PlayerLoginPacket;
import fr.mathieubour.minesweeper.utils.Log;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientSocketHandler {
    private static ClientSocketHandler instance = null;

    private Socket socket = null;
    private ObjectInputStream inputStream = null;
    private ObjectOutputStream outputStream = null;
    private ClientInputThread clientInputThread;

    private ClientSocketHandler() {
        ClientSocketHandler.instance = this;
    }

    public static synchronized ClientSocketHandler getInstance() {
        if (instance == null) {
            instance = new ClientSocketHandler();
        }

        return instance;
    }

    /**
     * Create the socket and the object in/out streams.
     * ObjectOutputStream must be instantiated <b>before</b> the ObjectInputStream otherwise it cause a deadlock.
     *
     * @param ip The server IP address.
     * @link https://stackoverflow.com/a/21864408
     */
    public void connect(String ip) throws IOException {
        // Parse IP:PORT
        int port = 4200;

        if (ip.indexOf(':') > -1) {
            // ip has embedded port (IP:PORT)
            String[] arr = ip.split(":");
            ip = arr[0];
            try {
                port = Integer.parseInt(arr[1]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        ServerState.getInstance().setIp(ip);
        ServerState.getInstance().setPort(port);

        if (socket == null) {
            socket = new Socket(ip, port);
            Log.info("Created socket");
        }

        if (outputStream == null) {
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            Log.info("Created out stream");
        }

        if (inputStream == null) {
            inputStream = new ObjectInputStream(socket.getInputStream());
            Log.info("Created in stream");
        }

        if (clientInputThread == null) {
            clientInputThread = new ClientInputThread(inputStream);
            Log.info("Created input thread");
            clientInputThread.start();
            Log.info("Started input thread");
        }

        send(new PlayerLoginPacket(PlayerState.getInstance().getPlayer()));
    }

    public void disconnect() {
        try {
            clientInputThread.interrupt();
            inputStream.close();
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(Packet packet) {
        if (outputStream == null) {
            Log.info("ClientSocketHandler.outputStream is null, aborting");
            return;
        }

        try {
            Log.packet("Sending", packet);
            outputStream.writeObject(packet);
            Log.packet("Sent", packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
