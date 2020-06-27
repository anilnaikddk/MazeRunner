package com.akn.game.entities;

import com.akn.game.generator.MazeGenerator;
import com.akn.game.generator.PathFinder;
import com.akn.game.generator.RecursiveBacktrackingUsingStack;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Disposable;

public class Level implements Disposable {

    private int ROWS;
    private int COLS;
    private int SCALE;
    private int width;
    private int height;

    private Player player;
    private Destination destination;
    private MazeDrawer mazeDrawer;
    private Cell[][] maze;
    private MazeGenerator mazeGenerator;
    private PathFinder pathFinder;
    private String characterName;

    public Level(int COLS, int ROWS, int WIDTH, int HEIGHT, int originX, int originY, String characterName) {
        this.COLS = COLS;
        this.ROWS = ROWS;
        this.characterName = characterName;
        SCALE = Math.min(WIDTH / COLS, HEIGHT / ROWS);
        width = COLS * SCALE;
        height = ROWS * SCALE;

        mazeGenerator = new RecursiveBacktrackingUsingStack(COLS, ROWS, WIDTH, HEIGHT, originX, originY);
        mazeGenerator.generateMaze();
        maze = mazeGenerator.getMaze();
        pathFinder = new PathFinder(mazeGenerator.getTraversalQueue());

        mazeGenerator.initialCellAtTop(true);
        mazeGenerator.setFinalCell(pathFinder.getLongestPathCellFrom(mazeGenerator.getInitialCell(), maze));
        destination = new Destination(mazeGenerator.getFinalCell());
        player = new Player(mazeGenerator.getInitialCell(), characterName, destination, maze);
        mazeDrawer = new MazeDrawer(maze);
    }

    public Player getPlayer() {
        return player;
    }

    public Destination getDestination() {
        return destination;
    }

    public MazeDrawer getMazeDrawer() {
        return mazeDrawer;
    }

    public Cell[][] getMaze() {
        return maze;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Level replay(){
        destination = new Destination(mazeGenerator.getFinalCell());
        player = new Player(mazeGenerator.getInitialCell(), characterName, destination, maze);
        mazeDrawer = new MazeDrawer(maze);
        return this;
    }

    @Override
    public void dispose() {
        player.dispose();
        destination.dispose();
        mazeDrawer.dispose();
        Gdx.app.log(getClass().getSimpleName(), "Disposing");
    }

    //    public void calculateScale() {
//        SCALE = Math.min(WIDTH / COLS, HEIGHT / ROWS);
//        Gdx.app.log("SCALE", SCALE + "");
//    }


}
