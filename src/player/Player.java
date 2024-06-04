package player;

public class Player {
    private String name;
    private int money;
    private Inventory inventory;

    public Player(String name) {
        this.name = name;
        this.money = 0;
        this.inventory = new Inventory();
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Inventory getInventory() {
        return inventory;
    }
}