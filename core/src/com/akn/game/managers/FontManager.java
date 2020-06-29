package com.akn.game.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class FontManager {

    public enum TYPE {F1, F3, Default}

    private final int defaultSize = 16;
    private final int defaultBorderWidth = 1;
    private final Color defaultFontColor = Color.WHITE;
    private final Color defaultShadowColor = new Color(0, 0.7f, 0, 0.6f);
    private final int defaultShadowOffsetX = 2;
    private final int defaultShadowOffsetY = 2;

    public BitmapFont getFont(TYPE type) {
        if (type == TYPE.F1) {
            return new BitmapFont(Gdx.files.internal("fonts/font1.fnt"));
        }
        if (type == TYPE.F3) {
            return new BitmapFont(Gdx.files.internal("fonts/font3.fnt"));
        }
        return new BitmapFont(Gdx.files.internal("fonts/font.fnt"));
    }

    public BitmapFont getFreeTypeFont(int size) {
        return getFreeTypeFont(size, defaultFontColor);
    }

    public BitmapFont getFreeTypeFont(int size, Color color) {
        return getFreeTypeFont(size, color, defaultShadowColor);
    }

    public BitmapFont getFreeTypeFont(int size, Color color, Color shadowColor) {
        return getFreeTypeFont(size, color, shadowColor, defaultBorderWidth, defaultShadowOffsetX, defaultShadowOffsetY);
    }

    public BitmapFont getFreeTypeFont(int size, Color color, Color shadowColor, int borderWidth, int shadowOffsetX, int shadowOffsetY) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/z_bold.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        parameter.borderWidth = borderWidth;
        parameter.color = color;
        parameter.shadowOffsetX = shadowOffsetX;
        parameter.shadowOffsetY = shadowOffsetY;
        if (shadowColor != null)
            parameter.shadowColor = shadowColor;
        BitmapFont font = generator.generateFont(parameter); // font size 24 pixels
        generator.dispose();
        return font;
    }

}
