package events;

import javax.swing.*;

public class Trading extends Event {

    public static void main(String[] args){
        JFrame tradingMenu = new JFrame("Trading Menu");
        tradingMenu.setSize(300,300);
        tradingMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tradingMenu.setVisible(true);
    }
}
