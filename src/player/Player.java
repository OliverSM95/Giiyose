package player;

public class Player {

    public String location;
    public Inventory inventory;
    public static int coins;

    public int dungeonsCounter;



    public int gold;
    public String name;

    public Player() { // Default Constructor for Player
        this.dungeonsCounter =0;
        this.inventory = new Inventory();
        this.coins = 0; // or any starting amount


        this.gold = 0;


        this.name = name;
        this.location = "basic";
    }
    public void setLocation(String location) {this.location = location;}
    public String getLocation() {return location;}

    public int getDungeonsCounter(){return dungeonsCounter;}
    public void increaseDungeonCounter(){
        this.dungeonsCounter++;
    }

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


    public  int getCoins() {
        return coins;
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