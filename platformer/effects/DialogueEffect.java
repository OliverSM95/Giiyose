
package effects;

import static utilz.Constants.ANI_SPEED;
import static utilz.Constants.Dialogue.*;

public class DialogueEffect {

    // class variables
    private int x, y, type;
    private int aniIndex, aniTick;
    private boolean active = true;

    // class constructor
    public DialogueEffect(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public void update() {// update  dialogue effects
        aniTick++;
        if (aniTick >= ANI_SPEED) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(type)) {
                active = false;
                aniIndex = 0;
            }
        }
    }

    public void deactive() {
        active = false;
    }

    public void reset(int x, int y) {
        this.x = x;
        this.y = y;
        active = true;
    }


    // Set and get effects
    public int getAniIndex() {
        return aniIndex;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getType() {
        return type;
    }

    public boolean isActive() {
        return active;
    }
}
