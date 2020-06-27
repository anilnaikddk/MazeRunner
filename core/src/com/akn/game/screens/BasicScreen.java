package com.akn.game.screens;

import com.akn.game.data.GraphicsData;
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

    protected final float WIDTH;
    protected final float HEIGHT;

    public BasicScreen(OrthographicCamera camera, SpriteBatch batch, Viewport viewport, ScreenManager screens){
        this.camera = camera;
        this.batch = batch;
        this.viewport = viewport;
        this.screens = screens;

        WIDTH = Gdx.graphics.getWidth();
        HEIGHT = Gdx.graphics.getHeight();
    }

    protected void clearScreen(){
        Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);
    }
}
