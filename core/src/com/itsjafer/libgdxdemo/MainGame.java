/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itsjafer.libgdxdemo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.itsjafer.model.Block;
import com.itsjafer.model.Mario;
import com.itsjafer.model.World;
import com.itsjafer.screens.WorldRenderer;

/**
 *
 * @author haidj9901
 */
public class MainGame implements Screen {

    private World world;
    private Mario player;
    private WorldRenderer renderer;
     private TiledMap map;

    public MainGame() {
        world = new World();
        player = world.getPlayer();
        renderer = new WorldRenderer(world);
        map = new TmxMapLoader().load("map.tmx");
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float deltaTime) {
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.setState(Mario.State.RUNNING);
            player.setVelocityX(2f);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            player.setVelocityX(-2f);
            player.setState(Mario.State.RUNNING);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            player.jump();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
        }
        player.update(deltaTime);
        //collisions
        // go through each block
        for (Block b : world.getBlocks()) {
            // if player is hitting a block
            if (player.isColliding(b)) {
                // get overlapping amount
                float overX = player.getOverlapX(b);
                float overY = player.getOverlapY(b);

                //just fixing y if not moving
                if (player.getVelocityX() == 0) {
                    // player is above the block
                    if (player.getY() > b.getY()) {
                        player.addToPosition(0, overY);
                        player.setState(Mario.State.STANDING);
                    } else {
                        player.addToPosition(0, -overY);
                    }
                    player.setVelocityY(0);
                } else {
                    // fix the smallest overlap
                    if (overX < overY) {
                        // left of the block
                        if (player.getX() < b.getX()) {
                            player.addToPosition(-overX, 0);
                        } else {
                            player.addToPosition(overX, 0);
                        }
                    } else {
                        // above the block
                        if (player.getY() > b.getY()) {
                            player.addToPosition(0, overY);
                            if (player.getState() == Mario.State.JUMPING) {
                                player.setState(Mario.State.STANDING);
                            }
                        } else {
                            player.addToPosition(0, -overY);
                        }
                        player.setVelocityY(0);
                    }
                }
            }
        }

        renderer.render(deltaTime);
    }

    @Override
    public void resize(int i, int i1) {
        renderer.resize(i, i1);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }
}
