package events;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import player.Player;

public class Fishing extends Event {

    private Player player;
    private Random random;
    private int fishAttempts;
    private static final int MAX_ATTEMPTS = 5;

    public Fishing(Player player) {
        this.player = player;
        this.random = new Random();
        this.fishAttempts = 0;
        setupUI();
    }

    private void setupUI() {
        JFrame fishingMenu = new JFrame("Fishing Minigame");
        fishingMenu.setSize(300, 300);
        fishingMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton fishButton = new JButton("Go Fishing");
        fishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performFishing(fishButton);
            }
        });

        fishingMenu.add(fishButton);
        fishingMenu.setVisible(true);
    }

    private void performFishing(JButton fishButton) {
        if (fishAttempts >= MAX_ATTEMPTS) {
            JOptionPane.showMessageDialog(null, "You have reached the maximum fishing attempts for today.");
            fishButton.setEnabled(false);
            return;
        }

        int outcome = random.nextInt(3); // 0, 1, or 2
        if (outcome == 0) {
            JOptionPane.showMessageDialog(null, "You caught a fish!");
            player.getInventory().addItem("fish", 1);
        } else {
            JOptionPane.showMessageDialog(null, "You didn't catch anything.");
        }

        fishAttempts++;
    }

    public static void main(String[] args) {
        // For testing
        Player player = new Player();
        new Fishing(player);
    }
}
