package events;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import player.Player;

public class Fishing extends Event {

    private Player player;
    private Random random;

    public Fishing(Player player) {
        this.player = player;
        this.random = new Random();
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
                performFishing();
            }
        });

        fishingMenu.add(fishButton);
        fishingMenu.setVisible(true);
    }

    private void performFishing() {
        int outcome = random.nextInt(3); // 0, 1, or 2
        if (outcome == 0 || outcome == 1) {
            JOptionPane.showMessageDialog(null, "You caught a fish!");
            player.getInventory().addItem("fish", 1);
        } else {
            JOptionPane.showMessageDialog(null, "You caught trash.");
            player.getInventory().addItem("trash", 1);
        }
    }

    public static void main(String[] args) {
        // For testing
        Player player = new Player("Efe");
        new Fishing(player);
    }
}
