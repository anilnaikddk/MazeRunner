package com.akn.game.generator;

import com.akn.game.entities.Cell;
import com.badlogic.gdx.math.GridPoint2;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class PathFinder {

    private ArrayList<Path> pathList;
    private Stack<Path> pathStack;
//    private Set<Cell> list;
    private Queue<Cell> queue;

    public PathFinder(){
//        list = new LinkedHashSet<>();
        queue = new LinkedList<>();
        pathList = new ArrayList<>();
        pathStack = new Stack<>();
    }
    public PathFinder(Queue<Cell> queue) {
        this();
//        this.list = list;
        this.queue = queue;
        populatePaths();
    }

    private void populatePaths() {
    	pathList.clear();
    	pathStack.clear();
        boolean first = true;
//        for (Cell c : ) {
//            if (first) {
//                for(int k = 1; k <= c.openWallCount(); k++){
//                    pathStack.push(new Path(c));
//                }
//                first = false;
//                continue;
//            }
//            processCell(c);
//        }
        while (!queue.isEmpty()){
            if (first) {
                for(int k = 1; k <= queue.peek().openWallCount(); k++){
                    pathStack.push(new Path(queue.remove()));
                }
                first = false;
                continue;
            }
            processCell(queue.remove());
        }
    }

    public Cell getLongestPathCell() {
        return getLongestPathCellFrom().last().get();
    }

    public Path getLongestPathCellFrom() {
        pathList.sort((path1,path2) -> path1.size() - path2.size());
        return pathList.get(pathList.size() - 1);
    }

    public Cell getLongestPathCellFrom(Cell from, Cell[][] maze) {
        queue.clear();
        Stack<Cell> tmpStack = new Stack<>();
        tmpStack.push(from);
        while (!tmpStack.isEmpty()) {
            queue.add(tmpStack.peek());
            for (GridPoint2 p : tmpStack.pop().getOpenSidesList()) {
                if (!queue.contains(maze[p.x][p.y]))
                    tmpStack.add(maze[p.x][p.y]);
            }
        }
        populatePaths();
        return getLongestPathCell();
    }

    private void processCell(Cell c) {
        try {
            if (c.openWallCount() == 1) {
                Path p = pathStack.pop();
                p.add(c);
                pathList.add(p);
            } else if (c.openWallCount() == 2) {
                    pathStack.peek().add(c);
            } else if (c.openWallCount() == 3) {
                Path p = pathStack.pop();
                p.add(c);
                Path p1 = new Path(p);
                Path p2 = new Path(p);
                pathStack.push(p1);
                pathStack.push(p2);
            }
        } catch (EmptyStackException e) {
//			System.out.println(e.getLocalizedMessage());
        }
    }

//	private void printPath(int type) {
//		// print all paths
//		if (type == 1) {
//			for (Path p : pathList) {
//				System.out.println(p);
//			}
//		}
//		// print only lengths
//		else if (type == 2) {
//			String str = "";
//			for (Path p : pathList) {
//				str = str + p.length + ",";
//			}
//			System.out.println(String.join(", ", str.split(",")));
//		}
//	}
}
