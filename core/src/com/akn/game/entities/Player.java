package com.akn.game.entities;

import com.akn.game.data.Constants;
import com.akn.game.managers.AnimationManager;
import com.akn.game.managers.MoveManager;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Player extends Actor {

    private Sprite sprite;
    private String character;
    private AnimationManager animationManager;
    public int dir = 2;
    public boolean run = false;
    public boolean won = false;

    private MoveManager moveManager;
    private Destination destination;

    // speed = distance / time
//    public float moveSpeed;

    private Cell cell;

    public Player(float x, float y, float width, float height, String character, Destination destination, Cell[][] maze) {
        setBounds(x, y, width, height);
        this.character = character;
        this.destination = destination;
        animationManager = new AnimationManager();
        moveManager = new MoveManager(this, destination, maze);
//        moveSpeed = (width / 100) / moveTime;
    }

    public Player(Cell startingCell, String character, Destination destination, Cell[][] maze) {
        this(startingCell.x, startingCell.y, startingCell.width, startingCell.height, character, destination, maze);
        this.cell = startingCell;
        dir = startingCell.getOpenSide();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (run) {
            sprite = animationManager.getSprite(character, dir, delta);
        } else {
            sprite = animationManager.getFirstSprite(character, dir);
            if (won && !hasActions()) {
//                Gdx.app.log("Anil", "Win Win");
                destination.playerReached();
                won = false;
            }
        }
        sprite.setBounds(getX(), getY(), getWidth(), getHeight());
    }

    public void move(int direction) {
        moveManager.processMove(direction);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
//        super.draw(batch,parentAlpha);

        sprite.draw(batch);
        sprite.setOriginCenter();
        if(Constants.flipY) {
            sprite.setFlip(false,true);
        }
    }

    public void updateCharacter(String character) {
        this.character = character;
    }

    public void dispose() {
        animationManager.dispose();
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell nextCell) {
        this.cell = nextCell;
    }
}


