package objects;

import main.Game;

public class Potion extends GameObject { // Child class for potions

	private float hoverOffset;
	private int maxHoverOffset, hoverDir = 1;

	public Potion(int x, int y, int objType) { // Creating potions
		super(x, y, objType);
		doAnimation = true;

		initHitbox(7, 14);

		xDrawOffset = (int) (3 * Game.SCALE);
		yDrawOffset = (int) (2 * Game.SCALE);

		maxHoverOffset = (int) (10 * Game.SCALE);
	}

	public void update() { // Updating potions
		updateAnimationTick();
		updateHover();
	}

	private void updateHover() { // Hovering potions
		hoverOffset += (0.075f * Game.SCALE * hoverDir);

		if (hoverOffset >= maxHoverOffset)
			hoverDir = -1;
		else if (hoverOffset < 0)
			hoverDir = 1;

		hitbox.y = y + hoverOffset;
	}
}
