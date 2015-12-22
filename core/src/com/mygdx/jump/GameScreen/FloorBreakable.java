package com.mygdx.jump.GameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.mygdx.jump.Resource.Assets;
import com.mygdx.jump.Settings;

/**
 * Created by Yao on 15/12/21.
 */
public class FloorBreakable extends Floor {

    public static final int STATUS_NORMAL = 0;
    public static final int STATUS_BREAKING = 1;
    public static final float FLOOR_BREAKING_TIME = Settings.ANIMATION_INTERVAL * 3;
    public static final float RATE_BASE = 0.1f;
    private Sound brokesound = Gdx.audio.newSound(Gdx.files.internal("data/sound/hit.wav"));

    private Animation breakingAnim;

    public FloorBreakable(float x, float y){
        super(x,y);
        status = STATUS_NORMAL;
        keyFrame= Assets.getFloorBreakable();
        breakingAnim = Assets.getFloorBreaking();
        velocity.set(0, 0);
    }

    @Override
    public void update(float deltaTime){
        if (isBreaking()){
            keyFrame = breakingAnim.getKeyFrame(stateTime);
        }
        stateTime += deltaTime;
    }

    @Override
    public void hitDoctor(){
        floorBreak();
    }

    /**
     * Calls when the floor is broken
     */
    public void floorBreak() {
        status = STATUS_BREAKING;
        brokesound.play(1.0f);
        stateTime = 0;
    }

    /**
     * return true if the floor type is breakable
     */
    public boolean isBreakable() {
        return true;
    }

    /**
     * return true if the floor is broken and needs to be deleted
     */
    public boolean isBroken() {
        return status == STATUS_BREAKING && stateTime > FLOOR_BREAKING_TIME;
    }

    /**
     * return true if the floor is now breaking
     */
    public boolean isBreaking() {
        return status == STATUS_BREAKING;
    }
}