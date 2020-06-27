package com.akn.game.entities;

import com.akn.game.managers.AnimationManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class WelcomeScreenRunner extends Actor {

    private AnimationManager animationManager;
    //    private Animation<Sprite> animation;
    private Sprite sprite;
    private String characterName;
    private int dir = 2;
    private float xPos;
    private boolean move = true;

    public WelcomeScreenRunner(AnimationManager animationManager, String characterName) {
        this.animationManager = animationManager;
//        animation = animationManager.getAnimation(characterName,dir);
//        animation.setFrameDuration(1f / 8f);
        this.characterName = characterName;
        sprite = new Sprite(animationManager.getSprite(characterName, dir, 0));

        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                move = false;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                move = true;
                super.touchUp(event, x, y, pointer, button);
            }
        });
    }

    @Override
    public void setBounds(float x, float y, float width, float height) {
        xPos = x;
        super.setBounds(x, y, width, height);
    }

    @Override
    public void act(float delta) {
        if (move) {
            xPos += 3;
            xPos = xPos % Gdx.graphics.getWidth();
            setX(xPos);
        }
        super.act(delta);
        sprite = animationManager.getSprite(characterName, dir, delta);
//        sprite = animation.getKeyFrame(delta);
        sprite.setBounds(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        sprite.draw(batch);
    }
}
