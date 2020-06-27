package com.akn.game.managers;

import com.akn.game.data.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

public class AnimationManager implements Disposable {

    private TextureAtlas atlas;
    private Animation<Sprite> ballAnimations[];
    private Animation<Sprite> bunnyAnimations[];
    private float timePassed = 0;
    private float animSpeed = 19f / 5f;

    public AnimationManager() {
        initBunnyAnimations();
        initBallAnimations();
    }

    private void initBallAnimations() {
//        float dirs[] = {180, 90, 0, 270};
        ballAnimations = new Animation[Constants.getDirLength()];
        atlas = new TextureAtlas(Gdx.files.internal("ball.atlas"));

        for (int i = 0; i < ballAnimations.length; i++) {
            Array<Sprite> sprites = new Array<>();
            for (int frameNo = 1; frameNo <= atlas.findRegions("ball").size; frameNo++) {
                Sprite sprite = new Sprite(atlas.findRegion("ball", frameNo));
                sprite.rotate(Constants.getDegree(i));
                sprite.setScale(0.9f);
                sprites.add(sprite);
            }
            float speed = 1f / (sprites.size * animSpeed);
            ballAnimations[i] = new Animation(speed, sprites, Animation.PlayMode.LOOP);
        }
    }

    private void initBunnyAnimations() {
//        String dirs[] = {"left", "down", "right", "up"};
        bunnyAnimations = new Animation[Constants.getDirLength()];
        atlas = new TextureAtlas(Gdx.files.internal("bunny.atlas"));

        for (int i = 0; i < bunnyAnimations.length; i++) {
            Array sprites = new Array<Sprite>();
            for (int frameNo = 1; frameNo <= atlas.findRegions(Constants.getDirectionName(i)).size; frameNo++) {
                Sprite sprite = new Sprite(atlas.findRegion(Constants.getDirectionName(i), frameNo));
                sprites.add(sprite);
            }
            float speed = 1f / (sprites.size * animSpeed);
            bunnyAnimations[i] = new Animation(speed, sprites, Animation.PlayMode.LOOP);
        }
    }

    public Animation<Sprite> getAnimation(String character, int dir) {
        switch (character) {
            case "ball":
                return ballAnimations[dir];
            case "bunny":
            default:
                return bunnyAnimations[dir];
        }
    }

    public Sprite getSprite(String character, int dir, float delta){
        timePassed += delta;
        return getAnimation(character,dir).getKeyFrame(timePassed);
    }

    public Sprite getFirstSprite(String character, int dir){
        return getAnimation(character,dir).getKeyFrame(0);
    }

    public boolean isAnimationFinished(String character, int dir,float delta) {
        boolean r =  getAnimation(character,dir).isAnimationFinished(delta);
        return r;
    }

    @Override
    public void dispose() {
        atlas.dispose();
    }
}
