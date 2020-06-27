package com.akn.game.test;

import com.akn.game.managers.ScreenManager;
import com.akn.game.screens.BasicScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.viewport.Viewport;

public class ImageButtonTest extends BasicScreen {

    Stage stage;

    public ImageButtonTest(OrthographicCamera camera, SpriteBatch batch, Viewport viewport, ScreenManager screens) {
        super(camera, batch, viewport, screens);
        stage = new Stage(viewport,batch);

        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("ui/ui_icons.atlas"));
        Skin skin = new Skin(atlas);

        Drawable drawable = skin.getDrawable("play");
        ImageButton button = new ImageButton(drawable);
        button.getImageCell().size(100);


        stage.addActor(button);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        clearScreen();
        stage.act();
        stage.draw();
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
