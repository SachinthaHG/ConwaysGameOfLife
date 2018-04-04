package com.bglcrop.game.services;

import com.bglcrop.game.models.Point;

import java.util.List;


/**
 * This class includes the core business logics for the Conway's Game of Life
 */
public class Game {
    private final int NUMBER_OF_STAGES;
    private final int NUMBER_OF_ROWS;
    private final int NUMBER_OF_COLS;

    private int[][] gridOfSquareCells;
    private List<Point> inputLiveCells;

    /**
     * @param rows             number of rows of the grid
     * @param cols             number of columns of the grid
     * @param numberOfStages   number of stages for the game
     * @param initialLiveCells list of coordinates of initial live cells
     */
    public Game(int rows, int cols, int numberOfStages, List<Point> initialLiveCells) {
        this.NUMBER_OF_ROWS = rows;
        this.NUMBER_OF_COLS = cols;
        this.NUMBER_OF_STAGES = numberOfStages;
        this.inputLiveCells = initialLiveCells;

        gridOfSquareCells = new int[NUMBER_OF_ROWS][NUMBER_OF_COLS];
    }


    private void processInput(List<Point> pointList) {
        inputLiveCells.forEach(cell -> gridOfSquareCells[cell.getX()][cell.getY()] = 1);
    }

}
