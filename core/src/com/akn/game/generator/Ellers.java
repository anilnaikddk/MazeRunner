package com.akn.game.generator;

import com.akn.game.entities.Cell;

import java.util.Set;

public class Ellers extends MazeGenerator {

    //    1.Initialize the cells of the first row to each exist in their own set.
    //    2.Now, randomly join adjacent cells, but only if they are not in the same set. When joining adjacent cells, merge the cells of both sets into a single set, indicating that all cells in both sets are now connected (there is a path that connects any two cells in the set).
    //    3.For each set, randomly create vertical connections downward to the next row. Each remaining set must have at least one vertical connection. The cells in the next row thus connected must share the set of the cell above them.
    //    4.Flesh out the next row by putting any remaining cells into their own sets.
    //    5.Repeat until the last row is reached.
    //    6.For the last row, join all adjacent cells that do not share a set, and omit the vertical connections, and you’re done!

    private Set<Cell> cellSet;
    public Ellers(int cols, int rows, int width, int height) {
        super(cols, rows, width, height);
    }

    @Override
    public void generateMaze() {
        int rowNo = 0;
        int cols = maze[0].length;
        Set<Cell>[] cellSetArray;
        while (rowNo < maze.length){
            cellSetArray = new Set[cols];
        }

    }
}
