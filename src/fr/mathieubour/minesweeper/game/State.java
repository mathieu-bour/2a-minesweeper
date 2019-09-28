package fr.mathieubour.minesweeper.game;

import java.io.Serializable;
import java.util.HashMap;

public class State implements Serializable {
    HashMap<String, Player> players;
    Field field;
}
