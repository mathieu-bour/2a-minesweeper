package fr.mathieubour.minesweeper.client.panels;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class CountdownPanel extends JPanel {
    private static CountdownPanel instance;
    private boolean running;
    private long start;
    private long end;
    private Timer countdownTimer;
    private JLabel countdownLabel = new JLabel("", SwingConstants.CENTER);
    private JProgressBar countdownProgress = new JProgressBar();

    private CountdownPanel() {
        super(new GridBagLayout());
        redraw();
    }

    public static CountdownPanel getInstance() {
        if (instance == null) {
            instance = new CountdownPanel();
        }

        return instance;
    }

    public void redraw() {
        countdownLabel.setFont(countdownLabel.getFont().deriveFont(50.0F));
        countdownProgress.setStringPainted(true);
        countdownProgress.setIndeterminate(false);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(15, 0, 5, 0);
        add(countdownLabel, constraints);

        constraints.insets = new Insets(5, 0, 15, 0);
        constraints.gridy = 1;
        add(countdownProgress, constraints);
    }

    public void freezeCountdown() {
        stop();
        countdownProgress.setIndeterminate(true);
    }

    public void setCountdown(long end) {
        stop();
        start = System.currentTimeMillis();
        this.end = end;
        countdownProgress.setMaximum((int) (end - start));
        start();
    }

    public void setDescription(String description) {
        countdownProgress.setString(description);
    }

    private void start() {
        if (running) {
            stop();
        }

        countdownTimer = new Timer();
        countdownTimer.schedule(this.getTask(), 0, 200L);
        countdownProgress.setIndeterminate(false);
    }

    private void stop() {
        if (countdownTimer != null) {
            countdownTimer.cancel();
        }
    }

    private TimerTask getTask() {
        return new TimerTask() {
            @Override
            public void run() {
                running = true;
                long now = System.currentTimeMillis();

                if (now >= end) {
                    countdownTimer.cancel();
                }

                int deltaSeconds = (int) ((end - now) / 1000);

                if (deltaSeconds < 0) {
                    deltaSeconds = 0;
                }

                countdownLabel.setText(Integer.toString(deltaSeconds));
                countdownProgress.setValue((int) (now - start));
            }
        };
    }
}
