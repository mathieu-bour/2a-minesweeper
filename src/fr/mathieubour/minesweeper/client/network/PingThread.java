package fr.mathieubour.minesweeper.client.network;

import fr.mathieubour.minesweeper.packets.PingPacket;

/**
 * Ping the server every second to compute the current ping.
 */
public class PingThread extends Thread {
    @Override
    public void run() {
        while (!interrupted()) {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            ClientSocketHandler.getInstance().send(new PingPacket());
        }
    }
}
