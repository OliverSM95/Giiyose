package player;

import java.util.HashMap;
import java.util.Map;

public class Inventory {

    private Map<String, Integer> items;

    public Inventory() {
        items = new HashMap<>();
        initializeDefaultItems();
    }

    private void initializeDefaultItems() {
        items.put("coins", 25);
        items.put("wood", 10);
        items.put("stone", 10);
        items.put("gold", 5);
    }

    public void addItem(String item, int quantity) {
        items.put(item, items.getOrDefault(item, 0) + quantity);
    }

    public void removeItem(String item, int quantity) {
        items.put(item, items.getOrDefault(item, 0) - quantity);
        if (items.get(item) <= 0) {
            items.remove(item);
        }
    }

    public boolean hasItem(String item, int quantity) {
        return items.getOrDefault(item, 0) >= quantity;
    }

    public Map<String, Integer> getItems() {
        return items;
    }
}
