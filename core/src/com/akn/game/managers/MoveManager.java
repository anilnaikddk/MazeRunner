package com.akn.game.managers;

import com.akn.game.data.Constants;
import com.akn.game.entities.Destination;
import com.akn.game.entities.Player;
import com.akn.game.entities.Cell;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.action;

public class MoveManager {

    private final int LEFT = 0;
    private final int UP = 1;
    private final int RIGHT = 2;
    private final int DOWN = 3;

    private Player player;
    private Destination destination;
    private Cell[][] maze;
    private Action startRun, stopRun;

    public MoveManager(Player player, Destination destination, Cell[][] maze) {
        this.player = player;
        this.destination = destination;
        this.maze = maze;

        initActions();
    }

    private void initActions() {
        startRun = new Action() {
            @Override
            public boolean act(float delta) {
                player.run = true;
                return true;
            }
        };

        stopRun = new Action() {
            @Override
            public boolean act(float delta) {
                player.run = false;
                return true;
            }
        };
    }

    public void processMove(int move) {

        int moveCount = 0;
        boolean canautomove = false;
        SequenceAction sequenceAction = action(SequenceAction.class);
        sequenceAction.addAction(startRun);
        do {
            if (!canMove(move))
                break;
            Cell nextCell = getNextCell(move);
            if (nextCell == null) {
                break;
            }
            addMoveActions(move, sequenceAction);
            moveCount++;
            player.setCell(nextCell);

            boolean won = checkWin(nextCell);
            if(won){
                player.won = true;
                break;
            }

            canautomove = nextCell.openWallCount() == 2;
            if (canautomove) {
                move = nextMove(move);
            }
        } while (canautomove);
        if (moveCount > 0) {
            sequenceAction.addAction(stopRun);
            player.addAction(sequenceAction);
        }
    }

    private boolean checkWin(Cell nextCell) {
        Cell destCell = destination.getCell();
        boolean win = destCell.xi == nextCell.xi && destCell.yi == nextCell.yi;
        return win;
    }

    private boolean canMove(int dir) {
        if (player.hasActions())
            return false;
        if (dir == LEFT && !player.getCell().LEFT_WALL) {
            return true;
        }
        if (dir == RIGHT && !player.getCell().RIGHT_WALL) {
            return true;
        }
        if (dir == UP && !player.getCell().UP_WALL) {
            return true;
        }
        if (dir == DOWN && !player.getCell().DOWN_WALL) {
            return true;
        }
        return false;
    }

    private int nextMove(int move) {
        List<Character> allMoves = new ArrayList<>();
        allMoves.add('U');
        allMoves.add('D');
        allMoves.add('L');
        allMoves.add('R');
        switch (move) {
            case RIGHT:
                allMoves.remove(new Character('L'));
                break;
            case LEFT:
                allMoves.remove(new Character('R'));
                break;
            case UP:
                allMoves.remove(new Character('D'));
                break;
            case DOWN:
                allMoves.remove(new Character('U'));
                break;
        }
        for (Character c : allMoves) {
            if (player.getCell().LEFT_WALL == false && c == 'L') {
                return LEFT;
            } else if (player.getCell().RIGHT_WALL == false && c == 'R') {
                return RIGHT;
            } else if (player.getCell().UP_WALL == false && c == 'U') {
                return UP;
            } else if (player.getCell().DOWN_WALL == false && c == 'D') {
                return DOWN;
            }
        }
        return move;
    }


    private Cell getNextCell(int dir) {
        try {
            switch (dir) {
                case LEFT:
                    return maze[player.getCell().xi - 1][player.getCell().yi];
                case UP:
                    return maze[player.getCell().xi][player.getCell().yi + 1];
                case RIGHT:
                    return maze[player.getCell().xi + 1][player.getCell().yi];
                case DOWN:
                    return maze[player.getCell().xi][player.getCell().yi - 1];
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
        return null;
    }

    private void addMoveActions(int dir, SequenceAction seqAction) {
        MoveByAction mba = action(MoveByAction.class);
        switch (dir) {
            case 0:
                mba.setAmount(-player.getWidth(), 0f);
                break;
            case 1:
                mba.setAmount(0f, player.getHeight());
                break;
            case 2:
                mba.setAmount(player.getWidth(), 0f);
                break;
            case 3:
                mba.setAmount(0f, -player.getHeight());
                break;
        }
        mba.setDuration(Constants.moveTime);

        RunnableAction rna = action(RunnableAction.class);
        rna.setRunnable(() -> player.dir = dir);

        MoveToAction mta = Actions.action(MoveToAction.class);
        mta.setPosition(player.getCell().x, player.getCell().y);
        mta.setDuration(Constants.moveTime);

        seqAction.addAction(rna);
        seqAction.addAction(mba);
    }
}


