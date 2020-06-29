package com.akn.game.generator;

import com.akn.game.entities.Cell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;


public class PathFinder {

    private enum Status {Visited, Unvisited, NotAvailable}

    private final ArrayList<Path> pathList;
    //    private final Stack<Path> tmpPathStack;
//    private Queue<Cell> queue;
    private Cell[][] maze;
    ArrayList<Cell> visited;

//    public PathFinder() {
//        queue = new LinkedList<>();
//        pathList = new ArrayList<>();
////        tmpPathStack = new Stack<>();
//        visited = new ArrayList<>();
//    }

    public PathFinder(Cell[][] maze) {
        this.maze = maze;
//        queue = new LinkedList<>();
        pathList = new ArrayList<>();
        visited = new ArrayList<>();
//        tmpPathStack = new Stack<>();
//        populatePaths();
    }

//    private void populatePaths() {
//        pathList.clear();
//        tmpPathStack.clear();
//        boolean first = true;
//        while (!queue.isEmpty()) {
//            if (first) {
//                for (int k = 1; k <= queue.peek().openWallCount(); k++) {
//                    tmpPathStack.push(new Path(queue.remove()));
//                }
//                first = false;
//                continue;
//            }
//            processCell(queue.remove());
//        }
//    }

//    private void processCell(Cell c) {
//        try {
//            if (c.openWallCount() == 1) {
//                Path p = tmpPathStack.pop();
//                p.add(c);
//                pathList.add(p);
//            } else if (c.openWallCount() == 2) {
//                tmpPathStack.peek().add(c);
//            } else if (c.openWallCount() == 3) {
//                Path p = tmpPathStack.pop();
//                p.add(c);
//                Path p1 = new Path(p);
//                Path p2 = new Path(p);
//                tmpPathStack.push(p1);
//                tmpPathStack.push(p2);
//            }
//        } catch (EmptyStackException e) {
////			System.out.println(e.getLocalizedMessage());
//        }
//    }

    private Cell getLongestPathCell() {
        Collections.sort(pathList);
        printPathSizes();
        System.out.println("Returning : " + pathList.get(pathList.size() - 1).size());
        return pathList.get(pathList.size() - 1).last();
    }


    public Cell longestPathCell(Cell from) {
//        tmpPathStack.clear();
        pathList.clear();
        visited.clear();

        Stack<Cell> stack = new Stack<>();
        stack.push(from);
        visited.add(from);

        DFS_Search(from, stack);
        System.out.println("List :" + pathList);
        return getLongestPathCell();
    }

    private void DFS_Search(Cell c, Stack<Cell> stack) {
        if (c.openWallCount() == 1 && stack.size() > 1) {
            pathList.add(new Path(stack));
            System.out.println(c + " - " + stack.size());
        }
        for (Cell nei : c.getNeighboursList(maze)) {
            if (stack.contains(nei) == false) {
                visited.add(nei);
                stack.push(nei);
                DFS_Search(nei, stack);
            }
        }
        stack.pop();
    }

    private void printPathSizes() {
        String str = "";
        for (Path path : pathList) {
            str += path.size() + ",";
        }
        System.out.println(str);
    }
}
