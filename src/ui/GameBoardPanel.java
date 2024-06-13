package ui;

import player.Player;

import javax.swing.*;
import java.awt.*;

public class GameBoardPanel extends JPanel {
     Image backgroundImage = new ImageIcon("src/Images/GiiyoseBackgrounds/canada blank.png").getImage();
     Player player;


     public GameBoardPanel(Player player) {
         this.player = player;

     }

    public void paintComponent(Graphics g) {

        // Set background color
//        setBackground(new Color(173, 216, 230)); // Light blue background
        super.paintComponent(g);

        switch (player.getLocation()){
            case "Ontario":
                backgroundImage = new ImageIcon("src/Images/GiiyoseBackgrounds/Ontario.png").getImage();
                break;
            case "Quebec":
                backgroundImage = new ImageIcon("src/Images/GiiyoseBackgrounds/Quebec.png").getImage();
                break;
            case "Newfoundland":
                backgroundImage = new ImageIcon("src/Images/GiiyoseBackgrounds/NewfoundLand.png").getImage();
                break;
            case "BC":
                backgroundImage = new ImageIcon("src/Images/GiiyoseBackgrounds/BC.png").getImage();
                break;
                case "Alberta":
                backgroundImage = new ImageIcon("src/Images/GiiyoseBackgrounds/Alberta.png").getImage();
                    break;
            case "Saskatchewan":
                backgroundImage = new ImageIcon("src/Images/GiiyoseBackgrounds/Saskatchewan.png").getImage();
                break;
            case "Nunavut":
                backgroundImage = new ImageIcon("src/Images/GiiyoseBackgrounds/Nunavut.png").getImage();
                break;
            case "Yukon":
                backgroundImage = new ImageIcon("src/Images/GiiyoseBackgrounds/Yukon.png").getImage();
                break;
            default:
                backgroundImage = new ImageIcon("src/Images/GiiyoseBackgrounds/Ontario.png").getImage();
                System.out.println(player.getLocation());
                break;

        }



        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);





        // Draw areas for fishing, gathering, hunting, and trading
        drawAreas(g);
    }

    private void drawAreas(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        // Set area dimensions
        int areaWidth = panelWidth / 4;
        int areaHeight = panelHeight / 2;


    }
}