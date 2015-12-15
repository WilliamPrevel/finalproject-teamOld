/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itsjafer.model;

import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author haidj9901
 */
public class Mario extends Entity {

    private final float X_MAX_VEL = 2.0f;
    ;
    private final float Y_MAX_VEL = 4.0f;
    ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
    private final float DAMP = 0.9f;

    public enum State {

        STANDING, RUNNING, JUMPING
    };
    ;;;;;;;;;;; ;

    private State state;
    private Vector2 velocity;
    private Vector2 acceleration;
    private float stateTime;
    private boolean isFacingLeft;

    public Mario(float x, float y, float width, float height) {
        super(x, y, width, height);

        state = State.STANDING;
        isFacingLeft = false;
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 0);
        stateTime = 0;
    }

    public void update(float delta) {
        acceleration.y = -9.8f;
        velocity.mulAdd(acceleration, delta);
        velocity.x = velocity.x * DAMP;
        if (velocity.x < 0.01f && velocity.x > -0.01f) {
            velocity.x = 0;
        }
        addToPosition(velocity.x, velocity.y);
    }

    public void jump() {
        if (state != State.JUMPING && velocity.y == 0) {
            velocity.y = Y_MAX_VEL;
        }
    }

    public void setVelocityX(float x) {
        velocity.x = x;
    }

    public void setVelocityY(float y) {
        velocity.y = y;
    }

    public void setState(State s) {
        if (state != s) {
            stateTime = 0;
        }
        state = s;
    }

    public float getVelocityX() {
        return velocity.x;
    }

    public float getVelocityY() {
        return velocity.y;
    }

    public State getState() {
        return state;
    }

    public float getStateTime() {
        return stateTime;
    }

    public boolean isFacingLeft() {
        return isFacingLeft;
    }
}