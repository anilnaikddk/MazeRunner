package com.akn.game.data;

import com.akn.game.utils.DirPair;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Constants {

    public static final String gameTitle = "The Maze Runner";
    public static float moveTime = 0.15f;
    public static float initialFactor = 10f;
    public static boolean flipY = false;

    public static final int DESKTOP_WIDTH = 1080 / 3;
    public static final int DESKTOP_HEIGHT = 1920 / 3;

    public static final int WIDTH = Gdx.graphics.getWidth();
    public static final int HEIGHT = Gdx.graphics.getHeight();

    private static DirPair[] directions = new DirPair[]{
            new DirPair("left", 180f),
            new DirPair("up", 90f),
            new DirPair("right", 0f),
            new DirPair("down", 270f),
    };

    public static int getDirLength() {
        return directions.length;
    }

    public static float getDegree(int i) {
        return directions[i].degree;
    }

    public static String getDirectionName(int i) {
        if (flipY && i == 1) {
            return directions[3].name;
        } else if (flipY && i == 3) {
            return directions[1].name;
        }
        return directions[i].name;
    }

    public static int getDir(int keycode) {
        if (flipY && keycode == Input.Keys.UP) {
            return 3;
        } else if (flipY && keycode == Input.Keys.DOWN) {
            return 1;
        }
        switch (keycode) {
            case Input.Keys.LEFT:
                return 0;
            case Input.Keys.UP:
                return 1;
            case Input.Keys.RIGHT:
                return 2;
            case Input.Keys.DOWN:
                return 3;
        }
        return -1;
    }
}
