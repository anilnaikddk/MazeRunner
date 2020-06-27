package com.akn.game.utils;

import com.akn.game.entities.Cell;

import java.util.Random;

public class Utils {

    public static Cell getRandomCellFromMaze(Cell[][] maze) {
        Random rand = new Random();
        int x = rand.nextInt(maze.length);
        int y = rand.nextInt(maze[0].length);
        return maze[x][y];
    }

    public static Cell getRandomUniqueCellFromMaze(Cell cell, Cell[][] maze) {
        Cell c;
        do {
            c = getRandomCellFromMaze(maze);
        } while (c.x == cell.x && c.y == cell.y);
        return c;
    }

    public static void pause(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

}
