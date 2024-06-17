package objects;

import static utilz.Constants.ObjectConstants.*;

import main.Game;

public class GameContainer extends GameObject { // Container class

	public GameContainer(int x, int y, int objType) { // Constructor
		super(x, y, objType);
		createHitbox();
	}

	private void createHitbox() { // Creating hitboxes
		if (objType == BOX) {
			initHitbox(25, 18);

			xDrawOffset = (int) (7 * Game.SCALE);
			yDrawOffset = (int) (12 * Game.SCALE);

		} else {
			initHitbox(23, 25);
			xDrawOffset = (int) (8 * Game.SCALE);
			yDrawOffset = (int) (5 * Game.SCALE);
		}

		hitbox.y += yDrawOffset + (int) (Game.SCALE * 2);
		hitbox.x += xDrawOffset / 2;
	}

	public void update() { // Updating animations
		if (doAnimation)
			updateAnimationTick();
	}
}
