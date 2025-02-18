package com.akn.game.screens;

import com.akn.game.data.GraphicsData;
import com.akn.game.data.PlayData;
import com.akn.game.managers.AnimationManager;
import com.akn.game.managers.ScreenManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;

public abstract class BasicScreen extends GraphicsData implements Screen {

    protected OrthographicCamera camera;
    protected SpriteBatch batch;
    protected Viewport viewport;
    protected ScreenManager screens;
    protected AnimationManager animationManager;
    protected PlayData playData;

    protected final float WIDTH = Gdx.graphics.getWidth();
    protected final float HEIGHT = Gdx.graphics.getHeight();

    public BasicScreen(){
    }

    public BasicScreen(OrthographicCamera camera, SpriteBatch batch, Viewport viewport, ScreenManager screens) {
        this.camera = camera;
        this.batch = batch;
        this.viewport = viewport;
        this.screens = screens;

        playData = new PlayData();
    }

    public BasicScreen(OrthographicCamera camera, SpriteBatch batch, Viewport viewport, ScreenManager screens, AnimationManager animationManager) {
        this(camera, batch, viewport, screens);
        this.animationManager = animationManager;
    }

    protected void clearScreen() {
        Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void show() {}

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }
}
