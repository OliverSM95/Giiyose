package Player;

import java.util.HashMap;

public class Inventory extends Player {


    protected HashMap<String,Integer> playerInventory = new HashMap<>();




    public Inventory(){



    }



    public  void addPlayerInventory(String item, int Quantity){

        playerInventory.put(item,(playerInventory.get(item)+Quantity));

    }


}
