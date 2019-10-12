package fr.mathieubour.minesweeper.client.network;

import fr.mathieubour.minesweeper.packets.Packet;
import fr.mathieubour.minesweeper.utils.Log;

import java.io.IOException;
import java.io.ObjectInputStream;

class ClientInputThread extends Thread {
    private static final boolean ENABLE_PING = false;
    private final ObjectInputStream inputStream;

    ClientInputThread(ObjectInputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public void run() {
        Log.info("ClientInputThread started");

        if (ENABLE_PING) {
            PingThread pingThread = new PingThread();
            pingThread.start();
        }

        while (!interrupted()) {
            try {
                Packet packet = (Packet) this.inputStream.readObject();
                ClientPacketHandler.getInstance().handle(packet);
            } catch (IOException | ClassNotFoundException e) {
                // Server disconnected
                e.printStackTrace();
                interrupt();
            }
        }
    }
}
