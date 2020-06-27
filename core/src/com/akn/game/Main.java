package com.akn.game;

import com.akn.game.data.Constants;
import com.akn.game.managers.ScreenManager;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class Main extends Game {

    private ScreenManager screens;

    @Override
    public void create() {
        Gdx.graphics.setTitle(Constants.gameTitle);
        screens = new ScreenManager(this);
    }

    @Override
    public void render() {
        super.render();
        Gdx.graphics.setTitle(Constants.gameTitle + ", FPS : " + Gdx.graphics.getFramesPerSecond());
    }

    @Override
    public void dispose() {
        screens.dispose();
    }
}
