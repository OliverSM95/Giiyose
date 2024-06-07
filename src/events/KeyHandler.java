package events;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = true;
            System.out.println("W");
        }else if (code == KeyEvent.VK_S) {
            downPressed = true;
            System.out.println("S");
        }else if (code == KeyEvent.VK_A) {
            leftPressed = true;
            System.out.println("A");
        }else if (code == KeyEvent.VK_D) {
            rightPressed = true;
            System.out.println("D");
        }

    }


    @Override public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;

        }else if (code == KeyEvent.VK_S) {
            downPressed = false;
        }else if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }else if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }
}
