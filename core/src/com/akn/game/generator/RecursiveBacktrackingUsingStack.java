package com.akn.game.generator;

import com.akn.game.entities.Cell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class RecursiveBacktrackingUsingStack extends MazeGenerator {

//	  Algorithm
//    1. Choose the initial cell, mark it as visited and push it to the stack
//    2. While the stack is not empty
//        1. Pop a cell from the stack and make it a current cell
//        2. If the current cell has any neighbors which have not been visited
//            1. Push the current cell to the stack
//            2. Choose one of the unvisited neighbors
//            3. Remove the wall between the current cell and the chosen cell
//            4. Mark the chosen cell as visited and push it to the stack

	public RecursiveBacktrackingUsingStack(int cols, int rows, int width, int height,int originX,int originY){
		super(cols,rows,width,height,originX,originY);
	}
//	public RecursiveBacktrackingUsingStack(int W, int H, int S) {
//		super(W, H, S);
//	}

	@Override
	public void generateMaze() {
		Stack<Cell> tmpStack = new Stack<>();
		initialCell = new Cell(0, 0, cellWidth,cellHeight,originX,originY);// Utils.getRandomCell(maze.length, maze[0].length, scale);
		// Choose the initial cell, mark it as visited and push it to the stack
		initialCell.markAsVisited();
//		maze[initialCell.xi][initialCell.yi] = initialCell;
		maze[initialCell.yi][initialCell.xi] = initialCell;
		tmpStack.push(initialCell);

		// While the stack is not empty
		while (!tmpStack.isEmpty()) {
			// Pop a cell from the stack and make it a current cell
			Cell currentCell = tmpStack.pop();
//			traversalList.add(currentCell);
			processCell(currentCell);


			// If the current cell has any neighbors which have not been visited
			if (currentCell.hasAnyUnvisitedNeighbourCell(maze)) {
				// Push the current cell to the stack
				tmpStack.push(currentCell);

				// Choose one of the unvisited neighbors
				ArrayList<Cell> n = currentCell.getUnvisitedNeighbourCells(maze);
				Collections.shuffle(n);
				Cell neighbourCell = n.get(0);

				// Remove the wall between the current cell and the chosen cell
				removeWall(currentCell, neighbourCell);

				// Mark the chosen cell as visited and push it to the stack
				makeCellVisited(neighbourCell);
				tmpStack.push(neighbourCell);
			}
		}
		// finalCell = Utils.getUniqueRandomCell(initialCell, maze.length,
		// maze[0].length);
	}
}
