package com.akn.game.generator;

import com.akn.game.entities.Cell;

import java.util.LinkedHashSet;
import java.util.Set;

public class RandomizedKruskal extends MazeGenerator {

    //algorithm Kruskal(G) is
    //    A := ∅
    //    for each v ∈ G.V do
    //        MAKE-SET(v)
    //    for each (u, v) in G.E ordered by weight(u, v), increasing do
    //        if FIND-SET(u) ≠ FIND-SET(v) then
    //           A := A ∪ {(u, v)}
    //           UNION(FIND-SET(u), FIND-SET(v))
    //    return A

    //      Maze Algorithm
    //    Create a list of all walls, and create a set for each cell, each containing just that one cell.
    //    For each wall, in some random order:
    //          If the cells divided by this wall belong to distinct sets:
    //                  Remove the current wall.
    //                  Join the sets of the formerly divided cells.

    private Set<Cell> bag;

    public RandomizedKruskal(int cols, int rows, int width, int height) {
        super(cols, rows, width, height);
        bag = new LinkedHashSet<>();
        for (int y = 0; y < cols; y++) {
            for (int x = 0; x < rows; x++) {
                bag.add(new Cell(x, y, cellWidth, cellHeight, originX, originY));
            }
        }
    }

    @Override
    public void generateMaze() {

    }

}
