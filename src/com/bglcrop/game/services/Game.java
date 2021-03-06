package com.bglcrop.game.services;

import com.bglcrop.game.models.Point;
import com.bglcrop.game.utils.GameUtilities;

import java.util.ArrayList;
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

    /**
     * Starting point of the game
     */
    public void start() {
        processInput();
        processGridOfCells();
    }

    private void processInput() {
        /*Mark live cells on the grid*/
        inputLiveCells.forEach(cell -> gridOfSquareCells[cell.getX()][cell.getY()] = 1);
    }

    private void processGridOfCells() {
        int[][] tempGridOfSquareCells = new int[NUMBER_OF_ROWS][NUMBER_OF_COLS];

        GameUtilities.gridCopy(tempGridOfSquareCells, gridOfSquareCells); //Copy the grid into a temporary grid

        for (int k = 0; k < NUMBER_OF_STAGES; k++) {
            List<Point> currentStageLiveCells = new ArrayList<>();

            /*Iterating through each row in the grid*/
            for (int i = 0; i < NUMBER_OF_ROWS; i++) {
                /*Iterating through each column*/
                for (int j = 0; j < NUMBER_OF_COLS; j++) {
                    List<Point> neighbours = GameUtilities.findNeighbours(i, j, NUMBER_OF_COLS, NUMBER_OF_ROWS);

                    int liveNeighbourCount = 0;
                    /*Find the number of live neighbour cells*/
                    for (Point neighbour : neighbours) {
                        if (gridOfSquareCells[neighbour.getX()][neighbour.getY()] == 1) {
                            liveNeighbourCount++;
                        }
                    }

                    /*Update the temporary grid cell states based on the given rules*/
                    if (liveNeighbourCount < 2 && gridOfSquareCells[i][j] == 1) {
                        tempGridOfSquareCells[i][j] = 0;
                    } else if (liveNeighbourCount <= 3 && liveNeighbourCount >= 2 && gridOfSquareCells[i][j] == 1) {
                        tempGridOfSquareCells[i][j] = 1;
                    } else if (liveNeighbourCount > 3 && gridOfSquareCells[i][j] == 1) {
                        tempGridOfSquareCells[i][j] = 0;
                    } else if (liveNeighbourCount == 3 && gridOfSquareCells[i][j] == 0) {
                        tempGridOfSquareCells[i][j] = 1;
                    }

                    /*If the current cell is live add it to a separate list*/
                    if (tempGridOfSquareCells[i][j] == 1)
                        currentStageLiveCells.add(new Point(i, j));
                }
            }

            processOutput(currentStageLiveCells);

            /*Copy temporary grid into the original grid*/
            GameUtilities.gridCopy(gridOfSquareCells, tempGridOfSquareCells);
        }
    }

    private void processOutput(List<Point> liveCellList) {
        /*Format and print the output*/
        GameUtilities.printCellList(liveCellList);
    }
}
