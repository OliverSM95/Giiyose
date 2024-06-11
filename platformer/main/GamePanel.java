package main;


import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;


public class GamePanel extends JPanel {
    private MouseInputs mouseInputs;
    private Game game;

    public GamePanel(Game game) {
        mouseInputs = new MouseInputs(this);
        this.game = game;

        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

    }

    public void setPanelSize(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setMinimumSize(screenSize);
        setPreferredSize(screenSize);
        setMaximumSize(screenSize);

    }

    public void updateGame(){

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        game.render(g);
    }

    public Game getGame(){
        return game;
    }


}
