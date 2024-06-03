package Player;

import java.util.HashMap;
import java.util.Scanner;

public class Player {

    Scanner sc = new Scanner(System.in);
protected int daysRemaining;

protected String playerName;
protected int playerCoins;

protected Inventory playerInventory = new Inventory();


public Player(){// default constructor

    playerCoins=0;
    daysRemaining=30;

}
// class methods

public void changePlayerName(){
    playerName = sc.next();
}
public String getPlayerName(){return playerName;}


    public void addPlayerCoins(int coins){
        playerCoins += coins;
    }
    public void removePlayerCoins(int coins){
        playerCoins -= coins;
    }

    public int getPlayerCoins(){return playerCoins;}




}
