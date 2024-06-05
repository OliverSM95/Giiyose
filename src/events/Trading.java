package events;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import player.Inventory;
import player.Player;

public class Trading extends Event {

    private Player player;

    public Trading(Player player) {
        this.player = player;
        setupUI();
    }

    private void setupUI() {
        JFrame tradingMenu = new JFrame("Trading Menu");
        tradingMenu.setSize(300, 300);
        tradingMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tradingMenu.setLayout(new GridLayout(3, 1));

        JLabel instructionLabel = new JLabel("Choose an action:");
        JButton buyButton = new JButton("Buy");
        JButton sellButton = new JButton("Sell");

        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performTrade(true);
            }
        });

        sellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performTrade(false);
            }
        });

        tradingMenu.add(instructionLabel);
        tradingMenu.add(buyButton);
        tradingMenu.add(sellButton);

        tradingMenu.setVisible(true);
    }

    private void performTrade(boolean isBuying) {
        Inventory inventory = player.getInventory();
        if (isBuying) {
            if (inventory.hasItem("trash", 2)) {
                inventory.removeItem("trash", 2);
                inventory.addItem("fish", 1);
                JOptionPane.showMessageDialog(null, "Traded 2 trash for 1 fish.");
            } else {
                JOptionPane.showMessageDialog(null, "Not enough trash to trade.");
            }
        } else {
            if (inventory.hasItem("fish", 1)) {
                inventory.removeItem("fish", 1);
                inventory.addItem("trash", 2);
                JOptionPane.showMessageDialog(null, "Traded 1 fish for 2 trash.");
            } else {
                JOptionPane.showMessageDialog(null, "Not enough fish to trade.");
            }
        }
    }

    public static void main(String[] args) {
        // For testing
        Player player = new Player("Efe");
        player.getInventory().addItem("trash", 5); // Adding some initial items for testing
        new Trading(player);
    }
}
