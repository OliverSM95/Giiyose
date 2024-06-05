package player;

import java.util.HashMap;
import java.util.Map;

public class Inventory {

    private Map<String, Integer> items;

    public Inventory() {
        items = new HashMap<>();
    }

    public void addItem(String item, int quantity) {
        items.put(item, items.getOrDefault(item, 0) + quantity);
    }

    public void removeItem(String item, int quantity) {
        if (items.containsKey(item)) {
            int currentQuantity = items.get(item);
            if (currentQuantity <= quantity) {
                items.remove(item);
            } else {
                items.put(item, currentQuantity - quantity);
            }
        }
    }

    public boolean hasItem(String item, int quantity) {
        return items.getOrDefault(item, 0) >= quantity;
    }

    public Map<String, Integer> getItems() {
        return new HashMap<>(items);
    }
}
