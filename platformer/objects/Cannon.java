package objects;

import main.Game;

public class Cannon extends GameObject { // Cannon class

    private int tileY;

    public Cannon(int x, int y, int objType) { // Constructor
        super(x, y, objType);
        tileY = y / Game.TILES_SIZE;
        initHitbox(40, 26);
//		hitbox.x -= (int) (1 * Game.SCALE);
        hitbox.y += (int) (6 * Game.SCALE);
    }

    // Updating animation
    public void update() {
        if (doAnimation)
            updateAnimationTick();
    }

    public int getTileY() { // Get ppos
        return tileY;
    }

}