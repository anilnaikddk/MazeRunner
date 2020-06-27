package com.akn.game.screens;

import com.akn.game.data.PlayData;
import com.akn.game.entities.WelcomeScreenRunner;
import com.akn.game.managers.AnimationManager;
import com.akn.game.managers.FontManager;
import com.akn.game.managers.ScreenManager;
import com.akn.game.utils.Dimension;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.Viewport;

public class WelcomeScreen extends BasicScreen {

    private final String title = "The Maze Runner";

    private FontManager fontManager;
    private Table table;
    private Stage stage;

    private final int noRows;
    private final float rowHeight;

    private boolean playButtonPressed = false;

    public WelcomeScreen(OrthographicCamera camera, SpriteBatch batch, Viewport viewport, ScreenManager screens, AnimationManager animationManager) {
        super(camera, batch, viewport, screens, animationManager);
        fontManager = new FontManager();
        noRows = 12;
        rowHeight = HEIGHT / noRows;

        stage = new Stage(viewport, batch);
        table = new Table();
        table.setFillParent(true);
        setupFontAndStyle(table);
        stage.addActor(table);
        stage.setDebugAll(true);
    }

    private void setupFontAndStyle(Table table) {
        int fontSize = (int) (WIDTH / title.length() * 1.5f);
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = fontManager.getFreeTypeFont(fontSize, Color.WHITE, new Color(0.2f, 0.8f, 0.7f, 0.7f));
        Label mainTitleLabel = new Label(title, labelStyle);

        Texture playButtonTexture = new Texture(Gdx.files.internal("ui/play.png"));
        playButtonTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Image playButton = new Image(playButtonTexture);
        Dimension fitDim = getFactoredDimension(playButtonTexture.getWidth(), playButtonTexture.getHeight(), WIDTH / 3, rowHeight * 2);
        playButton.setSize(fitDim.WIDTH, fitDim.HEIGHT);
        playButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                playButtonPressed = true;
                return true;
            }
        });

        WelcomeScreenRunner runner = new WelcomeScreenRunner(animationManager, PlayData.characterImageName);
        float animWidth = animationManager.getSprite(PlayData.characterImageName,2,0).getWidth();
        float animHeight = animationManager.getSprite(PlayData.characterImageName,2,0).getHeight();
        fitDim = getFactoredDimension(animWidth,animHeight,WIDTH / 5,rowHeight);
        runner.setBounds(WIDTH / 2, rowHeight * 3, fitDim.WIDTH,fitDim.HEIGHT);
        table.top().padTop(rowHeight * 3);
        table.add(runner).row();
        table.add(mainTitleLabel).row();
        table.add(playButton).size(playButton.getWidth(), playButton.getHeight()).padTop(rowHeight);

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        if (playButtonPressed) {
            playButtonPressed = false;
            screens.showNewPlayScreen();
        }
        clearScreen();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        Gdx.app.log(this.getClass().getSimpleName(), "Disposing");
    }
}
