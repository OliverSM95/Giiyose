package player;

public class Player {

    private Inventory inventory;
    private String name;

    public Player(String name) { // Default Constructor for Player
        this.inventory = new Inventory();
        this.name = name;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public String getName(){
        return name;
    }
}
