package com.akn.game.managers;

import com.akn.game.data.Constants;
import com.akn.game.entities.Player;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

public class InputManager extends InputAdapter implements GestureDetector.GestureListener {

    private LevelManager levelManager;

    public InputManager(LevelManager levelManager) {
        this.levelManager = levelManager;
    }

    @Override
    public boolean keyDown(int keycode) {

        Player bunny = levelManager.getCurrentLevel().getPlayer();
        int dir = Constants.getDir(keycode);
        if (dir != -1) bunny.move(dir);
        return true;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        if (Math.abs(velocityX) > Math.abs(velocityY)) {
            if (velocityX > 0) {
                keyDown(Input.Keys.RIGHT);
            } else {
                keyDown(Input.Keys.LEFT);
            }
        } else {
            if (velocityY > 0) {
                keyDown(Input.Keys.DOWN);
            } else {
                keyDown(Input.Keys.UP);
            }
        }
        return true;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }
}
