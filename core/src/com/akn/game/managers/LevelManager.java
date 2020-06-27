package com.akn.game.managers;

import com.akn.game.data.Constants;
import com.akn.game.data.GraphicsData;
import com.akn.game.data.PlayData;
import com.akn.game.entities.Level;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;

public class LevelManager extends GraphicsData implements Disposable {

    //    private LevelData levelData;
//    private final int DRAWABLE_WIDTH;
//    private final int DRAWABLE_HEIGHT;
//    private final int gcdOfWH;
//    private final float divideBy;
    public final float increaseBy = 0.4f;
    private final float initialFactor = 5f;
    private int ROWS;
    private int COLS;
    private int originX;
    private int originY;
    private Level level;
    private String characterName;
    private boolean newLevel;
    private float levelFactor;

    public LevelManager(int COLS, int ROWS, String characterName) {
        super();
        this.COLS = COLS;
        this.ROWS = ROWS;
        this.characterName = characterName;
        originX = 0;
        originY = 0;
        levelFactor = Constants.initialFactor;
//        DRAWABLE_WIDTH = Constants.WIDTH;
//        DRAWABLE_HEIGHT = Constants.HEIGHT;
//        gcdOfWH = Utils.gcd(DRAWABLE_WIDTH, DRAWABLE_HEIGHT);
//        divideBy = Math.min(DRAWABLE_WIDTH / gcdOfWH, DRAWABLE_HEIGHT / gcdOfWH);
        createLevel();
    }

    public LevelManager(Table tableLayout) {
        super(tableLayout);
        characterName = PlayData.characterImageName;
        levelFactor = Constants.initialFactor;
        COLS = getColsFactored(initialFactor);
        ROWS = getRowsFactored(initialFactor);
        originX = (int) tableLayout.getPadLeft();
        originY = (int) tableLayout.getPadBottom();
        createLevel();
    }

    public void replayLevel(){
        newLevel = true;
        level.replay();
    }

    public Level getSameNewLevel() {
        createLevel();
        return level;
    }

    public Level getCurrentLevel() {
        return level;
    }

//    public Level increaseLevel(boolean increaseRow) {
//        if (increaseRow)
//            increaseLevel(true, false);
//        else
//            increaseLevel(false, true);
//        return level;
//    }
//
//    public void increaseLevel(boolean increaseRow, boolean increaseColumn) {
//        if (increaseRow) {
//            ROWS++;
//        }
//        if (increaseColumn) {
//            COLS++;
//        }
//        createLevel();
//    }

    public void increaseLevel(){
        float factor = (float) COLS / (float) ROWS;
        Gdx.app.log(getClass().getSimpleName(),"factor-" + factor);
        increaseLevelBy(factor);
    }

    public void increaseLevelBy(float factor) {
        levelFactor += factor;
        COLS = getColsFactored(levelFactor);
        ROWS = getRowsFactored(levelFactor);
        Gdx.app.log("COLS,ROWS", COLS + "," + ROWS);
        createLevel();
    }

    private void createLevel() {
        level = new Level(COLS, ROWS, DRAWABLE_WIDTH, DRAWABLE_HEIGHT, originX, originY, characterName);
        newLevel = true;
    }

    public boolean isNewLevel() {
        return newLevel;
    }

    public void levelAddedToStage() {
        newLevel = false;
    }

    @Override
    public void dispose() {
        level.dispose();
        Gdx.app.log(getClass().getSimpleName(), "Disposing");
    }
}
