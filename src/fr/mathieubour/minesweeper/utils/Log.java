package fr.mathieubour.minesweeper.utils;

import fr.mathieubour.minesweeper.packets.Packet;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {
    private static String getDatePrefix() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return "[" + formatter.format(now) + "]";
    }

    public static void info(String text) {
        System.out.println(Log.getDatePrefix() + " [INFO] " + text);
    }

    public static void packet(String prefix, Packet packet) {
        Log.info(prefix + " " + packet.getClass().getSimpleName());
    }
}
