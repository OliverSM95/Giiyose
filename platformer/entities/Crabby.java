package entities;

import static utilz.Constants.EnemyConstants.*;
import static utilz.HelpMethods.IsFloor;
import static utilz.Constants.Dialogue.*;

import gamestates.Playing;

public class Crabby extends Enemy {

    // crab entity constructor
    public Crabby(float x, float y) {
        super(x, y, CRABBY_WIDTH, CRABBY_HEIGHT, CRABBY);
        initHitbox(22, 19);
        initAttackBox(82, 19, 30);
    }


    public void update(int[][] lvlData, Playing playing) {
        updateBehavior(lvlData, playing);
        updateAnimationTick();
        updateAttackBox();
    }
    // update crab behavor
    private void updateBehavior(int[][] lvlData, Playing playing) {
        if (firstUpdate)
            firstUpdateCheck(lvlData);

        if (inAir) {// if the crab is in the air
            inAirChecks(lvlData, playing);
        } else {
            switch (state) {
                case IDLE:// if crab is standing still
                    if (IsFloor(hitbox, lvlData))
                        newState(RUNNING);// start running
                    else
                        inAir = true;
                    break;
                case RUNNING:// if crab is running
                    if (canSeePlayer(lvlData, playing.getPlayer())) {
                        turnTowardsPlayer(playing.getPlayer());
                        if (isPlayerCloseForAttack(playing.getPlayer()))
                            newState(ATTACK);// attack
                    }
                    move(lvlData);

                    if (inAir)// if in air display dialogue
                        playing.addDialogue((int) hitbox.x, (int) hitbox.y, EXCLAMATION);

                    break;
                case ATTACK:// attack animation
                    if (aniIndex == 0)
                        attackChecked = false;
                    if (aniIndex == 3 && !attackChecked)
                        checkPlayerHit(attackBox, playing.getPlayer());
                    break;
                case HIT:// if crab is hit
                    if (aniIndex <= GetSpriteAmount(enemyType, state) - 2)
                        pushBack(pushBackDir, lvlData, 2f);
                    updatePushBackDrawOffset();
                    break;
            }
        }
    }

}