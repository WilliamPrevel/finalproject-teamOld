/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itsjafer.model;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

/**
 * The actual level
 *
 * @author kobed6328
 */
public class GameWorld {

    private Mario mario;
    // The blocks with which Mario can collide
    private TiledMapTileLayer collisionBlocks;
    // The global gravity "constant" (it can change)

    // The physics environment of the game
    World physicsWorld;
    /**
     * Creates the World object
     *
     * @param mario
     * @param collisionBlocks
     * @param gravity
     */
    public GameWorld(Mario mario, TiledMapTileLayer collisionBlocks, Vector2 gravity) {
        this.mario = mario;
        this.collisionBlocks = collisionBlocks;

        physicsWorld = new World(gravity, true);
        // mario should accelerate according to the global gravity as soon as he spawns
        mario.setAcceleration(gravity);
    }

    /**
     * Updates all of the updateable components.
     *
     * @param delta the time factor by which the game runs.
     */
    public void update(float delta) {
        mario.update(delta);

        // starting collision
//        System.out.println(collisionBlocks.getCell((int)mario.getX()/GameLoader.PPU, (int)mario.getY()/GameLoader.PPU));
    }

    public Mario getMario() {
        return mario;
    }

    public TiledMapTileLayer getCollisionBlocks() {
        return collisionBlocks;
    }

}