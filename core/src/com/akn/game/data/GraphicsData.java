package com.akn.game.data;

import com.akn.game.utils.Dimension;
import com.akn.game.utils.Utils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public abstract class GraphicsData {

    protected final int DRAWABLE_WIDTH;
    protected final int DRAWABLE_HEIGHT;
    protected final int gcdOfWH;
    protected final float ratioDivisor;

    public GraphicsData(){
        DRAWABLE_WIDTH = Gdx.graphics.getWidth();
        DRAWABLE_HEIGHT = Gdx.graphics.getHeight();
        gcdOfWH = Utils.gcd(DRAWABLE_WIDTH, DRAWABLE_HEIGHT);
        ratioDivisor = Math.min(DRAWABLE_WIDTH / gcdOfWH, DRAWABLE_HEIGHT / gcdOfWH);
    }

    public GraphicsData(Table table){
        DRAWABLE_WIDTH = (int) table.getColumnWidth(0);
        DRAWABLE_HEIGHT = (int) table.getRowHeight(0);
        gcdOfWH = Utils.gcd(DRAWABLE_WIDTH, DRAWABLE_HEIGHT);
        ratioDivisor = Math.min(DRAWABLE_WIDTH / gcdOfWH, DRAWABLE_HEIGHT / gcdOfWH);
    }

    protected int getColsFactored(float factor) {
        return (int) ((DRAWABLE_WIDTH / gcdOfWH) / ratioDivisor * factor);
    }

    protected int getRowsFactored(float factor) {
        return (int) ((DRAWABLE_HEIGHT / gcdOfWH) / ratioDivisor * factor);
    }

    protected Dimension getFactoredDimension(float fromWidth, float fromHeight, float toWidth, float toHeight){
        float ratioOriginal = fromWidth / fromHeight;
        float ratioNew = toWidth / toHeight;
        float newWidth;
        float newHeight;
        if(ratioNew > ratioOriginal){
            newHeight = toHeight;
            newWidth = fromWidth * toHeight / fromHeight;
        }else{
            newWidth = toWidth;
            newHeight = fromHeight * toWidth / fromWidth;
        }
        return new Dimension(newWidth,newHeight);
    }
}
