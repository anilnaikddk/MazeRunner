package com.akn.game.generator;

import com.akn.game.entities.Cell;

public class Node extends Cell {

    private Node parent;
    private Node child1;
    private Node child2;
    private Node child3;

    public Node(Cell cell, Node parent) {
        super(cell);
        if (parent == null)
            parent = this;
        else
            this.parent = parent;
    }

//    public setChild

}
