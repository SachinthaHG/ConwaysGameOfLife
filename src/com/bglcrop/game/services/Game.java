package com.bglcrop.game.services;

import com.bglcrop.game.models.Point;

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


    private void processInput(List<Point> pointList) {
        inputLiveCells.forEach(cell -> gridOfSquareCells[cell.getX()][cell.getY()] = 1);
    }

    private void processGridOfCells() {
        int[][] tempGridOfSquareCells = new int[NUMBER_OF_ROWS][NUMBER_OF_COLS];

        gridCopy(tempGridOfSquareCells, gridOfSquareCells);

        for (int k = 0; k < NUMBER_OF_STAGES; k++) {
            List<Point> currentStageLiveCells = new ArrayList<>();

            for (int i = 0; i < NUMBER_OF_ROWS; i++) {
                for (int j = 0; j < NUMBER_OF_COLS; j++) {
                    List<Point> neighbours = findNeighbours(i, j, NUMBER_OF_COLS, NUMBER_OF_ROWS);

                    int liveNeighbourCount = 0;
                    for (Point neighbour : neighbours) {
                        if (gridOfSquareCells[neighbour.getX()][neighbour.getY()] == 1) {
                            liveNeighbourCount++;
                        }
                    }

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
            gridCopy(gridOfSquareCells, tempGridOfSquareCells);
        }
    }

    private void gridCopy(int[][] oldArray, int[][] newArray) {
        for (int i = 0; i < oldArray.length; i++) {
            for (int j = 0; j < oldArray[i].length; j++) {
                oldArray[i][j] = newArray[i][j];
            }
        }
    }

    private List<Point> findNeighbours(int x, int y, int cols, int rows) {
        final Point[] NEIGHBOURS_LIST = new Point[]{new Point(-1, -1), new Point(-1, 0),
                new Point(-1, 1), new Point(0, -1), new Point(0, 1), new Point(1, -1), new Point(1, 0), new Point(1, 1)};

        List<Point> neighbours = new ArrayList<Point>();
        for (Point neighbour : NEIGHBOURS_LIST) {
            if (isOnGrid(x + neighbour.getX(), y + neighbour.getY(), cols, rows)) {
                neighbours.add(new Point(x + neighbour.getX(), y + neighbour.getY()));
            }
        }

        return neighbours;
    }

    private boolean isOnGrid(int x, int y, int cols, int rows) {
        if (x >= 0 && y >= 0 && x < cols && y < rows)
            return true;
        else
            return false;
    }

    private void processOutput(List<Point> pointList) {
        System.out.print("[");
        for (int i = 0; i < pointList.size(); i++) {
            if (i != 0)
                System.out.print("," + pointList.get(i).toString());
            else
                System.out.print(pointList.get(i).toString());
        }
        System.out.print("]");
        System.out.println();
    }

}
