package com.akn.game.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class TableTest implements Screen {

    private Stage stage;
    private Table tableLayout;
    private OrthographicCamera cam;

    public TableTest() {
        cam = new OrthographicCamera();
        stage = new Stage(new ScreenViewport(cam));
        stage.setDebugAll(true);

        cam.zoom = 1.05f;
        stage.getBatch().setProjectionMatrix(cam.combined);

        tableLayout = new Table();
        tableLayout.setFillParent(true);

//        table.row().width(10f);
//        table.top().left().add(l1).expandX();
        tableLayout.padTop(50f).padBottom(50f).row().expandX().expandY();
        tableLayout.add();

        stage.addActor(tableLayout);
        stage.draw();

        Gdx.app.log("T-Height", tableLayout.getPadLeft() + "");
        Gdx.app.log("T-Width", tableLayout.getRowHeight(0) + "");

        Container<Table> tableContainer = new Container<>();


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        stage.act();
        stage.draw();

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

    }
}
