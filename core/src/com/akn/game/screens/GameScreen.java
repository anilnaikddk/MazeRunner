package com.akn.game.screens;

import com.akn.game.data.PlayData;
import com.akn.game.managers.AnimationManager;
import com.akn.game.managers.InputManager;
import com.akn.game.managers.LevelManager;
import com.akn.game.managers.ScreenManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen extends BasicScreen {

    private Stage stage;
    private Table tableLayout;

    private PlayData playData;
    private AnimationManager animationManager;
    private LevelManager levelManager;

    public GameScreen(OrthographicCamera camera, SpriteBatch batch, Viewport viewport, ScreenManager screens) {
        super(camera, batch, viewport, screens);

        playData = new PlayData();
//        levelManager = new LevelManager(Constants.initialCols,Constants.initialRows,PlayData.characterImageName);
        animationManager = new AnimationManager();

        setupStage();
//        setInputProcessor();
    }

    private void setupStage() {
        stage = new Stage(viewport, batch);
        tableLayout = new Table();
        tableLayout.setFillParent(true);
//        tableLayout.padTop(0f).padBottom(0f).padLeft(2f).padRight(2f).row().row();
        tableLayout.pad(2f).row().row();
        tableLayout.add().expand();
        stage.addActor(tableLayout);
        stage.draw();

        // after stage.draw() all width and height are setup
        levelManager = new LevelManager(tableLayout);
    }

    private void setInputProcessor(){
        InputMultiplexer im = new InputMultiplexer();
        InputManager inputManager = new InputManager(levelManager);
        GestureDetector gd = new GestureDetector(inputManager);
        im.addProcessor(inputManager);
        im.addProcessor(gd);
        Gdx.input.setInputProcessor(im);
    }

    private void update(float delta) {
        if (playData.playerReached) {
            playData.playerReached = false;
            screens.showWinScreen();
        }

        if (levelManager.isNewLevel()) {
            stage.getActors().clear();

            tableLayout = new Table();
            tableLayout.setFillParent(true);
            tableLayout.padTop(50f).padBottom(50f).row().row();
            tableLayout.add().expand();

            levelManager.getCurrentLevel().getDestination().setPlayData(playData);

            tableLayout.add(levelManager.getCurrentLevel().getMazeDrawer());
            tableLayout.addActor(levelManager.getCurrentLevel().getDestination());
            tableLayout.addActor(levelManager.getCurrentLevel().getPlayer());
            stage.addActor(tableLayout);
            levelManager.levelAddedToStage();
        }
        camera.update();
    }

    @Override
    public void show() {
        setInputProcessor();
    }

    @Override
    public void render(float delta) {
        update(delta);
        clearScreen();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void dispose() {
        stage.dispose();
        animationManager.dispose();
        levelManager.dispose();
        Gdx.app.log(this.getClass().getSimpleName(), "Disposing");
    }

    public LevelManager getLevelManager(){
        return levelManager;
    }
}
