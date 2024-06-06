package events;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import player.Inventory;
import player.Player;

public class Trading extends Event {

    private Player player;
    private JFrame tradingMenu;
    private JFrame tradeOptionsMenu;
    private JTextArea inventoryDisplay;

    public Trading(Player player) {
        this.player = player;
        setupUI();
    }

    private void setupUI() {
        tradingMenu = new JFrame("Trading Menu");
        tradingMenu.setSize(500, 500);
        tradingMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tradingMenu.setLayout(new BorderLayout());
        tradingMenu.setLocationRelativeTo(null); // Center the window

        inventoryDisplay = new JTextArea();
        inventoryDisplay.setEditable(false);
        updateInventoryDisplay();

        JPanel buttonPanel = new JPanel(new GridLayout(4, 1));

        JLabel instructionLabel = new JLabel("Choose an action:");
        JButton buyButton = new JButton("Buy");
        JButton sellButton = new JButton("Sell");
        JButton quitButton = new JButton("Quit");

        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTradeOptions(true);
            }
        });

        sellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTradeOptions(false);
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tradingMenu.dispose();
            }
        });

        buttonPanel.add(instructionLabel);
        buttonPanel.add(buyButton);
        buttonPanel.add(sellButton);
        buttonPanel.add(quitButton);

        tradingMenu.add(buttonPanel, BorderLayout.CENTER);
        tradingMenu.add(new JScrollPane(inventoryDisplay), BorderLayout.EAST);

        tradingMenu.setVisible(true);
    }

    private void showTradeOptions(boolean isBuying) {
        tradeOptionsMenu = new JFrame(isBuying ? "Buy Options" : "Sell Options");
        tradeOptionsMenu.setSize(500, 500);
        tradeOptionsMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        tradeOptionsMenu.setLayout(new BorderLayout());
        tradeOptionsMenu.setLocationRelativeTo(null); // Center the window

        JTextArea optionsInventoryDisplay = new JTextArea();
        optionsInventoryDisplay.setEditable(false);
        optionsInventoryDisplay.setText(inventoryDisplay.getText());

        JPanel buttonPanel = new JPanel(new GridLayout(4, 1));

        JLabel instructionLabel = new JLabel(isBuying ? "Select an item to buy:" : "Select an item to sell:");
        JButton tradeButton = new JButton(isBuying ? "Buy 1 Fish for 2 Trash" : "Sell 1 Fish for 2 Trash");
        JButton backButton = new JButton("Back");

        tradeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performTrade(isBuying);
                optionsInventoryDisplay.setText(inventoryDisplay.getText());
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tradeOptionsMenu.dispose();
                tradingMenu.setVisible(true);
            }
        });

        buttonPanel.add(instructionLabel);
        buttonPanel.add(tradeButton);
        buttonPanel.add(backButton);

        tradeOptionsMenu.add(buttonPanel, BorderLayout.CENTER);
        tradeOptionsMenu.add(new JScrollPane(optionsInventoryDisplay), BorderLayout.EAST);

        tradeOptionsMenu.setVisible(true);
        tradingMenu.setVisible(false);
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
        updateInventoryDisplay();
    }

    private void updateInventoryDisplay() {
        StringBuilder inventoryText = new StringBuilder("Inventory:\n");
        for (Map.Entry<String, Integer> entry : player.getInventory().getItems().entrySet()) {
            inventoryText.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        inventoryDisplay.setText(inventoryText.toString());
    }

    public static void main(String[] args) {
        // For testing
        Player player = new Player("Efe");
        player.getInventory().addItem("trash", 5); // Adding some initial items for testing
        new Trading(player);
    }
}
