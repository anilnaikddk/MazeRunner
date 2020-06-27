package com.akn.game.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class FontTest implements Screen {
    private Stage stage;

    public FontTest(){
        stage = new Stage(new ScreenViewport());
        int Help_Guides = 12;
        int row_height = Gdx.graphics.getWidth() / Help_Guides;
        int col_width = Gdx.graphics.getWidth() / Help_Guides;

        Label.LabelStyle label1Style = new Label.LabelStyle();
        BitmapFont myFont = new BitmapFont(Gdx.files.internal("fonts/zeit.fnt"));
        label1Style.font = myFont;
        label1Style.fontColor = Color.WHITE;

        Label label1 = new Label("Title (BitmapFont)",label1Style);
        label1.setBounds(0,Gdx.graphics.getHeight()-row_height*12,100,100);
//        label1.setSize(Gdx.graphics.getWidth(),row_height);
//        label1.setPosition(0,Gdx.graphics.getHeight()-row_height*12);
        label1.setAlignment(Align.center);
        stage.addActor(label1);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/zeit.otf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 30;
        parameter.borderWidth = 1;
        parameter.color = Color.YELLOW;
        parameter.shadowOffsetX = 3;
        parameter.shadowOffsetY = 3;
        parameter.shadowColor = new Color(0, 0.5f, 0, 0.75f);
        BitmapFont font24 = generator.generateFont(parameter); // font size 24 pixels
        generator.dispose();

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font24;

        Label label2 = new Label("True Type Font (.ttf) - Gdx FreeType",labelStyle);
        label2.setSize(Gdx.graphics.getWidth()/Help_Guides*5,row_height);
        label2.setPosition(col_width*2,Gdx.graphics.getHeight()-row_height*4);
        stage.addActor(label2);

    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {
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
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
