package com.akn.game.entities;

import com.akn.game.data.Constants;
import com.akn.game.data.PlayData;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Disposable;

import java.util.Random;

public class Destination extends Image implements Disposable {

    private Cell cell;
    private Sprite sprite;
    private TextureAtlas atlas;
    private boolean reached = false;
    private boolean finished = false;
    private PlayData playData;

    private String imageNames[];

    public Destination(Cell cell) {
        this.cell = cell;
        setBounds(cell.x, cell.y, cell.width, cell.height);
        atlas = new TextureAtlas(Gdx.files.internal("fruits.atlas"));
        imageNames = atlas.getRegions().toString(",").split(",");

        initSprite();
        setScale(0.8f);

        addStartEffectActions();
    }

    public Cell getCell() {
        return cell;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
//        super.draw(batch,parentAlpha);

        sprite.setRotation(getRotation());
        sprite.setColor(getColor());
        sprite.setScale(getScaleX(), getScaleY());
        if (Constants.flipY)
            sprite.setFlip(false, true);
        sprite.setOriginCenter();
        sprite.draw(batch);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (reached && !finished) {
            addFinishEffectActions();
            finished = true;
        }
        if (reached && finished && !hasActions()) {
            reached = false;
            finished = false;
            playData.playerReached = true;
        }
    }

    @Override
    public void dispose() {
        atlas.dispose();
        Gdx.app.log(this.getClass().getSimpleName(), "Disposing");
    }

    private void addFinishEffectActions() {
        SequenceAction sa = Actions.action(SequenceAction.class);
//        sa.addAction(Actions.fadeOut(1.0f));
        sa.addAction(Actions.repeat(4, Actions.scaleBy(-0.2f, -0.2f, 0.2f)));
        sa.addAction(Actions.scaleTo(0f, 0f));
        addAction(sa);

    }

    private void addStartEffectActions() {
        SequenceAction act = Actions.action(SequenceAction.class);
//        act.addAction(Actions.repeat(3,Actions.rotateBy(-360f,2f)));
//        act.addAction(Actions.rotateTo(0f));
        act.addAction(Actions.scaleTo(1.2f, 1.2f, 0.1f));
        act.addAction(Actions.scaleTo(0.9f, 0.9f, 0.1f));
        addAction(Actions.repeat(8, act));
    }

    private String getRandomFruit() {
        Random random = new Random();
        return imageNames[random.nextInt(imageNames.length)];
//        return "carrot";
    }

    public void initSprite() {
        sprite = new Sprite(atlas.findRegion(getRandomFruit()));

        float width;
        float height;
        // bigger is 100% and smaller will be scaled down
        if (sprite.getWidth() > sprite.getHeight()) {
            width = getWidth();
            height = sprite.getHeight() / sprite.getWidth() * getHeight();
        } else {
            height = getHeight();
            width = sprite.getWidth() / sprite.getHeight() * getWidth();
        }
        sprite.setBounds(getX(), getY(), width, height);
        sprite.setPosition(getX() + getWidth() / 2 - sprite.getWidth() / 2, getY() + getHeight() / 2 - sprite.getHeight() / 2);


    }

    public void setPlayData(PlayData playData) {
        this.playData = playData;
    }

    public void playerReached() {
        reached = true;
    }
}
