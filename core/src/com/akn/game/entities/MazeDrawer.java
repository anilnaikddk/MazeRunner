package com.akn.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

public class MazeDrawer extends Actor implements Disposable {

    private ShapeRenderer shapeRenderer;
    private Cell[][] maze;

    public MazeDrawer(Cell[][] maze) {
        this.maze = maze;
        shapeRenderer = new ShapeRenderer();
        Gdx.gl.glLineWidth(1.5f);

        setSize(maze.length * maze[0][0].width, maze[0].length * maze[0][0].height);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.end();

        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        for (Cell[] rows : maze) {
            for (Cell cell : rows) {
                cell.render(shapeRenderer);
            }
        }
        shapeRenderer.end();
        batch.begin();
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
        Gdx.app.log(this.getClass().getSimpleName(), "Disposing");
    }
}
