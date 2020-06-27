package com.akn.game.test;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class StageTest extends Stage {

    @Override
    public void draw() {
        for(Actor actor : getActors()){
            actor.draw(getBatch(),1f);
        }
        super.draw();
    }


}
