package com.akn.game.test;

import com.akn.game.managers.ScreenManager;
import com.akn.game.screens.BasicScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;

public class PixelArrayTest extends BasicScreen {

    Texture texture;
    Sprite sprite;
    boolean[][] pixArray;
    float cellWidth;
    float cellHeight;

    public PixelArrayTest(OrthographicCamera camera, SpriteBatch batch, Viewport viewport, ScreenManager screens) {
        super(camera, batch, viewport, screens);
        texture = new Texture(Gdx.files.internal("test2.png"));
//        sprite.setOriginCenter();
//        sprite.setSize(300, 300);

        test1();
    }

    void test1() {
        Pixmap pixmap = new Pixmap(Gdx.files.internal("test/heart.png"));
        System.out.println(pixmap.getWidth() + "," + pixmap.getHeight());
        int w = pixmap.getWidth();
        int h = pixmap.getHeight();
        pixArray = new boolean[w][h];
        cellWidth = WIDTH / w;
        cellHeight = HEIGHT / h;

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                System.out.println(new Color(pixmap.getPixel(i, j)).a);
                if (new Color(pixmap.getPixel(i, j)).a == 0) {
                    pixArray[i][j] = true;
//                    Gdx.app.log("Alpha", "" + count);
                }
            }
        }
//        int count = 0;
//        String str = "\n";
//        for (int i = 0; i < h; i++) {
//            for (int j = 0; j < w; j++) {
//                if(!pixArray[j][i]){
//                    str += "xxx";
//                }else{
//                    str += "---";
//                }
//                count++;
//            }
//            str += "\n";
//        }
//        System.out.println(str);
//        System.out.println(count);
    }

    @Override
    public void render(float delta) {
        clearScreen();
        batch.begin();
        for (int i = 0; i < pixArray[0].length; i++) {
            for (int j = 0; j < pixArray.length; j++) {
                if(!pixArray[j][i]){
                    batch.draw(texture,i*cellWidth,j*cellHeight);
                }
            }
        }
        batch.end();
    }

    @Override
    public void dispose() {

    }
}
