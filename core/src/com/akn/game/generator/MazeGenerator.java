package com.akn.game.generator;

import com.akn.game.entities.Cell;
import com.akn.game.utils.Utils;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public abstract class MazeGenerator {

    protected Cell maze[][];
    protected Stack<Cell> stackToPublish;
    protected Queue<Cell> queueToPublish;
    protected Set<Cell> setToPublish;
    protected Cell initialCell;
    protected Cell finalCell;
    protected int scale;
    protected float cellWidth;
    protected float cellHeight;
    protected int originX = 0;
    protected int originY = 0;

    private MazeGenerator(int cols, int rows) {
        maze = new Cell[cols][rows];
        stackToPublish = new Stack<>();
        queueToPublish = new LinkedList<>();
        setToPublish = new LinkedHashSet<>();
    }

    public MazeGenerator(int cols, int rows, int width, int height) {
        this(cols, rows);
        cellWidth = (float) width / (float) cols;
        cellHeight = (float) height / (float) rows;
    }

    public MazeGenerator(int cols, int rows, int width, int height, int originX, int originY){
        this(cols,rows,width,height);
        this.originX = originX;
        this.originY = originY;
    }

    protected void processCell(Cell cell) {
        stackToPublish.push(cell);
        if (!queueToPublish.contains(cell))
            queueToPublish.add(cell);
        setToPublish.add(cell);
    }

    public abstract void generateMaze();

    protected void removeWall(Cell c1, Cell c2) {
        if (c1.yi - 1 == c2.yi) {
            c1.DOWN_WALL = false;
            c2.UP_WALL = false;
        } else if (c1.yi + 1 == c2.yi) {
            c1.UP_WALL = false;
            c2.DOWN_WALL = false;
        } else if (c1.xi - 1 == c2.xi) {
            c1.LEFT_WALL = false;
            c2.RIGHT_WALL = false;
        } else if (c1.xi + 1 == c2.xi) {
            c1.RIGHT_WALL = false;
            c2.LEFT_WALL = false;
        }
        maze[c1.xi][c1.yi] = c1;
    }

    protected void makeCellVisited(Cell cell) {
        if (maze[cell.xi][cell.yi] == null) {
            maze[cell.xi][cell.yi] = cell;
        }
        maze[cell.xi][cell.yi].visited = true;
    }

    public void initialCellAtRandom() {
        initialCell = Utils.getRandomCellFromMaze(maze);
    }

    public void initialCellAtTop(boolean left) {
        if (left)
            initialCell = maze[0][maze[0].length - 1];
        else
            finalCellAtEnd();
    }

    public void finalCellAtRandom() {
        finalCell = Utils.getRandomCellFromMaze(maze);
    }

    public void finalCellAtUniqueRandom() {
        finalCell = Utils.getRandomUniqueCellFromMaze(initialCell, maze);
    }

    public void initialCellAtStart() {
        initialCell = maze[0][0];
    }

    public void finalCellAtEnd() {
        finalCell = maze[maze.length - 1][maze[0].length - 1];
    }

    public Cell[][] getMaze() {
        return maze;
    }

    public Cell getInitialCell() {
        return initialCell;
    }

    public Cell getFinalCell() {
        return finalCell;
    }

    public Stack<Cell> getTraversalStack() {
        return stackToPublish;
    }

    public Queue<Cell> getTraversalQueue() {
        return queueToPublish;
    }

    public Set<Cell> getTraversalSet() {
        return setToPublish;
    }

    public void setFinalCell(Cell cell) {
        finalCell = cell;
    }
}
