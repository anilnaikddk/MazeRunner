package com.akn.game.managers;

import com.akn.game.Main;
import com.akn.game.screens.GameScreen;
import com.akn.game.screens.WelcomeScreen;
import com.akn.game.screens.WinScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class ScreenManager implements Disposable {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Viewport viewport;
    private AnimationManager animationManager;

    private Main main;

    private WelcomeScreen welcomeScreen;
    private GameScreen gameScreen;
    private WinScreen winScreen;

    public ScreenManager(Main main) {
        this.main = main;
        camera = new OrthographicCamera();
        batch = new SpriteBatch();
        viewport = new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        animationManager = new AnimationManager();

        // initially add welcome screen
        welcomeScreen = new WelcomeScreen(camera, batch, viewport, this, animationManager);
        add(welcomeScreen);
    }

    private void add(Screen screen) {
        main.setScreen(screen);
    }

    public void showWelcomeScreen() {
        if (welcomeScreen == null) {
            welcomeScreen = new WelcomeScreen(camera, batch, viewport, this, animationManager);
        }
        add(welcomeScreen);
    }

    public void showNewPlayScreen() {
        if (gameScreen == null)
            gameScreen = new GameScreen(camera, batch, viewport, this, animationManager);
        else {
            gameScreen.getLevelManager().increaseLevel();
        }
        add(gameScreen);
    }

    public void showWinScreen() {
        if (winScreen == null) {
            winScreen = new WinScreen(camera, batch, viewport, this);
        }
        add(winScreen);
    }

    public void replayLevel() {
        gameScreen.getLevelManager().replayLevel();
        add(gameScreen);
    }

    public void nextLevel() {
        gameScreen.getLevelManager().increaseLevel();
        add(gameScreen);
    }

    @Override
    public void dispose() {
        batch.dispose();
        animationManager.dispose();
        if (gameScreen != null)
            gameScreen.dispose();
        if (welcomeScreen != null)
            welcomeScreen.dispose();
        if (winScreen != null)
            winScreen.dispose();
        Gdx.app.log(this.getClass().getSimpleName(), "Disposing");
    }
}
