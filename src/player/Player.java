package player;

public class Player {

    public String location;
    public Inventory inventory;
    public static int coins;
    public int fish;
    public int trash;
    public int bisonMeatFur;
    public  int wolfMeatFur;
    public int gold;
    public String name;

    public Player() { // Default Constructor for Player
        this.inventory = new Inventory();
        this.coins = 0; // or any starting amount
        this.fish = 0;
        this.trash = 0;
        this.gold = 0;
        this.bisonMeatFur = 0;
        this.wolfMeatFur = 0;
        this.name = name;
        this.location = "Ontario";
    }
    public void setLocation(String location) {this.location = location;}
    public String getLocation() {return location;}

    public Inventory getInventory() {
        return inventory;
    }

    public String getName(){
        return name;
    }

    public void setGold(int gold){
        this.gold += gold;
    }

    public int getGold(){
        return gold;
    }



    public int getBisonMeatFur(){
        return bisonMeatFur;
    }
    public void addBisonMeatFur(int num){
        this.bisonMeatFur+=num;
    }

    public int getWolfMeatFur(){
        return wolfMeatFur;
    }
    public void addWolfMeatFur(int num){
        this.wolfMeatFur+=num;
    }

    public  int getCoins() {
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