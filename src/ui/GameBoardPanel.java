package ui;

import player.Player;

import javax.swing.*;
import java.awt.*;

public class GameBoardPanel extends JPanel {
     Image backgroundImage = new ImageIcon("src/Images/GiiyoseBackgrounds/canada blank.png").getImage();
     Player player = new Player();
    @Override
    protected void paintComponent(Graphics g) {


        // Set background color
//        setBackground(new Color(173, 216, 230)); // Light blue background
        super.paintComponent(g);

        switch (player.location){
            case "Ontario":
                backgroundImage = new ImageIcon("src/Images/GiiyoseBackgrounds/Ontario.png").getImage();
            case "Quebec":
                backgroundImage = new ImageIcon("src/Images/GiiyoseBackgrounds/Quebec.png").getImage();
            case "NewfoundLand":
                backgroundImage = new ImageIcon("src/Images/GiiyoseBackgrounds/NewfoundLand.png").getImage();
            case "BC":
                backgroundImage = new ImageIcon("src/Images/GiiyoseBackgrounds/BC.png").getImage();
            case "Alberta":
                backgroundImage = new ImageIcon("src/Images/GiiyoseBackgrounds/Alberta.png").getImage();
            case "Saskatchewan":
                backgroundImage = new ImageIcon("src/Images/GiiyoseBackgrounds/Saskatchewan.png").getImage();
            case "Nunavut":
                backgroundImage = new ImageIcon("src/Images/GiiyoseBackgrounds/Nunavut.png").getImage();
            case "Yukon":
                backgroundImage = new ImageIcon("src/Images/GiiyoseBackgrounds/Yukon.png").getImage();

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

        /*
        // Draw Fishing area
        g2d.setColor(new Color(70, 130, 180)); // Steel blue
        g2d.fillRect(0, 0, areaWidth, areaHeight);
        g2d.setColor(Color.WHITE);
        g2d.drawString("Fishing Area", areaWidth / 2 - 30, areaHeight / 2);

        // Draw Gathering area
        g2d.setColor(new Color(34, 139, 34)); // Forest green
        g2d.fillRect(areaWidth, 0, areaWidth, areaHeight);
        g2d.setColor(Color.WHITE);
        g2d.drawString("Gathering Area", areaWidth + areaWidth / 2 - 40, areaHeight / 2);

        // Draw Hunting area
        g2d.setColor(new Color(139, 69, 19)); // Saddle brown
        g2d.fillRect(2 * areaWidth, 0, areaWidth, areaHeight);
        g2d.setColor(Color.WHITE);
        g2d.drawString("Hunting Area", 2 * areaWidth + areaWidth / 2 - 40, areaHeight / 2);

        // Draw Trading area
        g2d.setColor(new Color(255, 215, 0)); // Gold
        g2d.fillRect(3 * areaWidth, 0, areaWidth, areaHeight);
        g2d.setColor(Color.BLACK);
        g2d.drawString("Trading Area", 3 * areaWidth + areaWidth / 2 - 40, areaHeight / 2);

         */
    }
}