package player;

public class Player {
    private Inventory inventory;
    private static int coins;
    private int fish;
    private int trash;
    private int wood;
    private int stone;
    private int bisonMeatFur;
    private  int wolfMeatFur;
    protected int gold;
    private String name;

    public Player(String name) { // Default Constructor for Player
        this.inventory = new Inventory();
        this.coins = 0; // or any starting amount
        this.fish = 0;
        this.trash = 0;
        this.wood = 0;
        this.stone = 0;
        this.gold = 0;
        this.bisonMeatFur = 0;
        this.wolfMeatFur = 0;
        this.name = name;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public String getName(){
        return name;
    }

    public int getGold(){
        return gold;
    }

    public int getWood(){
        return wood;
    }

    public int getStone(){
        return stone;
    }

    public int getBisonMeatFur(){
        return bisonMeatFur;
    }

    public int getWolfMeatFur(){
        return wolfMeatFur;
    }

    public static int getCoins() {
        return coins;
    }

    public int getFish(){
        return fish;
    }

    public int getTrash(){
        return trash;
    }

    public void addCoins(int amount) {
        coins += amount;
    }

    public static void removeCoins(int amount) {
        if (coins >= amount) {
            coins -= amount;
        } else {
            coins = 0;
        }
    }
}