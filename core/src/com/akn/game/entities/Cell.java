package com.akn.game.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.GridPoint2;

import java.util.ArrayList;
import java.util.List;

import space.earlygrey.shapedrawer.ShapeDrawer;

public class Cell {

    public boolean LEFT_WALL = true;
    public boolean UP_WALL = true;
    public boolean RIGHT_WALL = true;
    public boolean DOWN_WALL = true;

    public float width;
    public float height;
    public float x;
    public float y;

    public int xi; // column index
    public int yi; // row index

    public int originX = 0;
    public int originY = 0;

    public boolean visited = false;
    public Color lineColor = Color.WHITE;

    public Cell(int xi, int yi, float width, float height) {
        this.xi = xi;
        this.yi = yi;
        this.width = width;
        this.height = height;
        x = xi * width;
        y = yi * height;
    }

    public Cell(int xi, int yi, float width, float height, int originX, int originY) {
        this(xi, yi, width, height);
        this.originX = originX;
        this.originY = originY;
        x = x + originX;
        y = y + originY;
    }

    public Cell(Cell cell) {
        this(cell.xi, cell.yi, cell.width, cell.height);
        x = cell.x;
        y = cell.y;
        originX = cell.originX;
        originY = cell.originY;
        visited = cell.visited;
    }

    public void render(ShapeRenderer renderer) {
        renderer.setColor(lineColor);
        if (UP_WALL) {
            renderer.line(x, y + height, x + width, y + height);
        }
        if (DOWN_WALL) {
            renderer.line(x, y, x + width, y);
        }
        if (LEFT_WALL) {
            renderer.line(x, y, x, y + height);
        }
        if (RIGHT_WALL) {
            renderer.line(x + width, y, x + width, y + height);
        }
    }

    public void render(ShapeDrawer renderer) {

        renderer.setColor(lineColor);
        if (UP_WALL) {
            renderer.line(x, y + height, x + width, y + height);
        }
        if (DOWN_WALL) {
            renderer.line(x, y, x + width, y);
        }
        if (LEFT_WALL) {
            renderer.line(x, y, x, y + height);
        }
        if (RIGHT_WALL) {
            renderer.line(x + width, y, x + width, y + height);
        }
    }

    public void drawFilled(ShapeRenderer renderer, int offset, Color color) {
        renderer.set(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(color);
//        renderer.rect(x + offset, y + offset, scale - offset * 2, scale - offset * 2);
    }

    public boolean hasAnyUnvisitedNeighbourCell(Cell[][] mazeGrid) {
        return isNeighbourUnvisited(xi + 1, yi, mazeGrid)
                || isNeighbourUnvisited(xi, yi + 1, mazeGrid)
                || isNeighbourUnvisited(xi - 1, yi, mazeGrid)
                || isNeighbourUnvisited(xi, yi - 1, mazeGrid);
    }

    private boolean isNeighbourUnvisited(int nX, int nY, Cell[][] grid) {
        try {
            return grid[nX][nY].visited = !true;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        } catch (NullPointerException e) {
            return true;
        }
    }

    public ArrayList<Cell> getUnvisitedNeighbourCells(Cell[][] grid) {
        ArrayList<Cell> n = new ArrayList<>();
        // RIGHT
        if (isNeighbourUnvisited(xi + 1, yi, grid)) {
            n.add(new Cell(xi + 1, yi, width, height, originX, originY));
        }
        // UP
        if (isNeighbourUnvisited(xi, yi + 1, grid)) {
            n.add(new Cell(xi, yi + 1, width, height, originX, originY));
        }
        //LEFT
        if (isNeighbourUnvisited(xi - 1, yi, grid)) {
            n.add(new Cell(xi - 1, yi, width, height, originX, originY));
        }
        // DOWN
        if (isNeighbourUnvisited(xi, yi - 1, grid)) {
            n.add(new Cell(xi, yi - 1, width, height, originX, originY));
        }
        return n;
    }

    public int getOpenSide() {
        if (!LEFT_WALL) {
            return 0;
        }
        if (!UP_WALL) {
            return 1;
        }
        if (!RIGHT_WALL) {
            return 2;
        }
        return 3;
    }

    public List<Cell> getNeighboursList(Cell[][] maze) {
        List<Cell> list = new ArrayList<>();
        if (!LEFT_WALL) {
            list.add(maze[xi - 1][yi]);
        }
        if (!UP_WALL) {
            list.add(maze[xi][yi + 1]);
        }
        if (!RIGHT_WALL) {
            list.add(maze[xi + 1][yi]);
        }
        if (!DOWN_WALL) {
            list.add(maze[xi][yi - 1]);
        }
        return list;
    }

    public int openWallCount() {
        int count = 0;
        if (LEFT_WALL) {
            count++;
        }
        if (RIGHT_WALL) {
            count++;
        }
        if (UP_WALL) {
            count++;
        }
        if (DOWN_WALL) {
            count++;
        }
        return 4 - count;
    }

    public void markAsVisited() {
        visited = true;
    }

    @Override
    public String toString() {
        return xi + "," + yi;
    }
}
