package fr.mathieubour.minesweeper.server.network;

import fr.mathieubour.minesweeper.game.Player;
import fr.mathieubour.minesweeper.packets.Packet;
import fr.mathieubour.minesweeper.packets.PlayerListPacket;
import fr.mathieubour.minesweeper.packets.PlayerLoggedPacket;
import fr.mathieubour.minesweeper.server.states.ServerGameState;
import fr.mathieubour.minesweeper.utils.Log;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;
import java.util.UUID;

public class ServerInputThread extends Thread {
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private Player player;

    ServerInputThread(Socket socket) throws IOException {
        this.output = new ObjectOutputStream(socket.getOutputStream());
        this.input = new ObjectInputStream(socket.getInputStream());
    }

    @Override
    public void run() {
        while (!interrupted()) {
            try {
                Packet packet = (Packet) this.input.readObject();
                ServerPacketHandler.getInstance().handle(packet, this);
            } catch (IOException e) {
                Log.info("Player " + player.getId() + " disconnected");

                ServerSocketHandler.getInstance()
                    .getClientThreads()
                    .remove(this);

                ServerGameState.getInstance()
                    .getPlayers()
                    .remove(player.getId());

                Log.info("Removed player " + player.getId());

                ServerSocketHandler.getInstance()
                    .broadcast(new PlayerListPacket(ServerGameState.getInstance().getPlayers()));
                interrupt();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        if (player.getId() == null) {
            player.setId(UUID.randomUUID().toString());
            Random random = new Random(System.currentTimeMillis());
            player.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
        }

        this.player = player;

        send(new PlayerLoggedPacket(this.player));
    }

    void send(Packet packet) {
        Log.packet("Sending to " + getPlayer().getId(), packet);
        try {
            this.output.writeObject(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.packet("Sent to " + getPlayer().getId(), packet);
    }
}
