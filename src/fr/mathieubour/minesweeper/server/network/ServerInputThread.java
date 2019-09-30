package fr.mathieubour.minesweeper.server.network;

import fr.mathieubour.minesweeper.game.Player;
import fr.mathieubour.minesweeper.packets.Packet;
import fr.mathieubour.minesweeper.packets.PlayerLoggedPacket;
import fr.mathieubour.minesweeper.utils.Log;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.UUID;

public class ServerInputThread extends Thread {
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private Player player;

    ServerInputThread(Socket socket, ObjectInputStream input, ObjectOutputStream output) {
        this.input = input;
        this.output = output;
    }

    public void run() {
        while (!interrupted()) {
            try {
                Packet packet = (Packet) this.input.readObject();
                ServerPacketHandler.getInstance().handle(packet, this);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                // Disconnected
                interrupt();
                Log.info("Disconnected!");
            }
        }
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        if (player.getId() == null) {
            player.setId(UUID.randomUUID().toString());
            player.setColor(Color.BLUE);
        }

        this.player = player;

        this.send(new PlayerLoggedPacket(this.player));
    }

    void send(Packet packet) {
        Log.packet("Sending", packet);
        try {
            this.output.writeObject(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.packet("Sent", packet);
    }
}
