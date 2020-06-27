package com.akn.game.screens;

import com.akn.game.managers.FontManager;
import com.akn.game.managers.ScreenManager;
import com.akn.game.utils.Dimension;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.Viewport;

public class WinScreen extends BasicScreen {

    private FontManager fontManager;
    private Table table;
    private Stage stage;
    private TextureAtlas atlas;

    private final int noRows;
    private final float rowHeight;

    private final String winText = "Level Complete";
    private final String menuText = "Menu";
    private final String replayText = "Replay";
    private final String nextText = "Next";

    private boolean menuButtonPressed = false;
    private boolean replayButtonPressed = false;
    private boolean nextButtonPressed = false;

    public WinScreen(OrthographicCamera camera, SpriteBatch batch, Viewport viewport, ScreenManager screens) {
        super(camera, batch, viewport, screens);
        fontManager = new FontManager();
        noRows = 12;
        rowHeight = HEIGHT / noRows;

        stage = new Stage(viewport, batch);
        table = new Table();
        table.setFillParent(true);
        setupFontAndStyle(table);

        stage.addActor(table);
//        Gdx.input.setInputProcessor(null);
//        Gdx.input.setInputProcessor(stage);
//        stage.setDebugAll(true);
    }

    private void setupFontAndStyle(Table table) {
        atlas = new TextureAtlas(Gdx.files.internal("ui/ui_icons.atlas"));
        int colsNo = 4;
        int fontSize = (int) (WIDTH / winText.length() * 1.5f);
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = fontManager.getFreeTypeFont(fontSize, Color.WHITE, null);
        Label mainTitleLabel = new Label(winText, labelStyle);

        fontSize = (int) (WIDTH / 5 / menuText.length());
        labelStyle.font = fontManager.getFreeTypeFont(fontSize, Color.WHITE, null);

        Label menuLabel = new Label(menuText, labelStyle);
        Label replayLabel = new Label(replayText, labelStyle);
        Label nextLabel = new Label(nextText, labelStyle);
        menuLabel.setAlignment(Align.center);
        replayLabel.setAlignment(Align.center);
        nextLabel.setAlignment(Align.center);

        Dimension fitDim;
        TextureRegion menuTexture = new TextureRegion(atlas.findRegion("menu"));
        menuTexture.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Image menuButton = new Image(menuTexture);
        fitDim = getFactoredDimension(menuTexture.getRegionWidth(), menuTexture.getRegionHeight(), WIDTH / colsNo, rowHeight * 2f);
        menuButton.setSize(fitDim.WIDTH, fitDim.HEIGHT);

        TextureRegion replayTexture = new TextureRegion(atlas.findRegion("replay"));
        replayTexture.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Image replayButton = new Image(replayTexture);
        fitDim = getFactoredDimension(replayTexture.getRegionWidth(), replayTexture.getRegionHeight(), WIDTH / colsNo, rowHeight * 2f);
        replayButton.setSize(fitDim.WIDTH, fitDim.HEIGHT);

        TextureRegion nextTexture = new TextureRegion(atlas.findRegion("next"));
        nextTexture.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Image nextButton = new Image(nextTexture);
        fitDim = getFactoredDimension(nextTexture.getRegionWidth(), nextTexture.getRegionHeight(), WIDTH / colsNo, rowHeight * 2f);
        nextButton.setSize(fitDim.WIDTH, fitDim.HEIGHT);

        menuButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                menuButtonPressed = true;
            }
        });
        replayButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                replayButtonPressed = true;
            }
        });
        nextButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                nextButtonPressed = true;
            }
        });

        table.top().add(mainTitleLabel).size(mainTitleLabel.getWidth(), mainTitleLabel.getHeight()).padTop(rowHeight * 4).colspan(3).row();
        table.row().padTop(20);
        table.add(menuButton).size(menuButton.getWidth(), menuButton.getHeight());
        table.add(replayButton).size(replayButton.getWidth(), replayButton.getHeight());
        table.add(nextButton).size(nextButton.getWidth(), nextButton.getHeight());
        table.row().padTop(2);
        table.add(menuLabel).size(menuLabel.getWidth(), menuLabel.getHeight());
        table.add(replayLabel).size(replayLabel.getWidth(), replayLabel.getHeight());
        table.add(nextLabel).size(nextLabel.getWidth(), nextLabel.getHeight());
    }

    private void update() {
        if (menuButtonPressed) {
            menuButtonPressed = false;
            screens.showWelcomeScreen();
        } else if (replayButtonPressed) {
            replayButtonPressed = false;
            screens.replayLevel();
        } else if (nextButtonPressed) {
            nextButtonPressed = false;
            screens.nextLevel();
        }
    }

    @Override
    public void render(float delta) {
        update();
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
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void dispose() {
        stage.dispose();
        atlas.dispose();
        Gdx.app.log(this.getClass().getSimpleName(), "Disposing");
    }
}
