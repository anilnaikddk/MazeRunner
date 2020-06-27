package com.akn.game.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class AnimationTest implements Screen {

    SpriteBatch batch;
    Animation<Sprite> animation;
    Camera camera;

    public AnimationTest(){
        batch = new SpriteBatch();
//        camera = new OrthographicCamera(new ScreenViewport());
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("dog_jay.atlas"));
        Sprite sprites[] = new Sprite[3];
        for(int i = 0; i< 3; i++){
            sprites[i] = new Sprite(atlas.findRegion("up",i+1));
        }
        animation = new Animation<>(1f/6f, sprites);
    }

    @Override
    public void show() {

    }

    float timePassed = 0;
    @Override
    public void render(float delta) {
//        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        timePassed += delta;
        batch.begin();
        animation.getKeyFrame(timePassed,true).draw(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

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
        batch.dispose();
    }
}
