package fr.mathieubour.minesweeper.client.listeners;

import fr.mathieubour.minesweeper.client.ui.TileButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TileButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        TileButton tileButton = (TileButton) actionEvent.getSource();

        System.out.println(tileButton.x + "," + tileButton.y);
    }
}
