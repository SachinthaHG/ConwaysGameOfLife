package com.bglcrop.game.utils;


import com.bglcrop.game.models.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * This class includes the utility methods to help the game's business logic
 */
public final class GameUtilities {
    private GameUtilities() {
    }

    /**
     * Method to print a given list of points
     *
     * @param pointList list of coordinates to be print
     */
    public static void printCellList(List<Point> pointList) {
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

    /**
     * Method to find neighbour points for a given point
     *
     * @param x    x coordinate of the cell
     * @param y    y coordinate of the cell
     * @param cols number of columns in the grid
     * @param rows number of rows in the grid
     * @return list of neighbour points to the given point
     */
    public static List<Point> findNeighbours(int x, int y, int cols, int rows) {
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

    /**
     * Method to find if a point is on the grid or not
     *
     * @param x    x coordinate of the point
     * @param y    y coordinate of the point
     * @param cols number of columns in the grid
     * @param rows number of rows in the grid
     * @return boolean value indicating whether the point is on the grid
     */
    public static boolean isOnGrid(int x, int y, int cols, int rows) {
        if (x >= 0 && y >= 0 && x < cols && y < rows)
            return true;
        else
            return false;
    }

    /**
     * Method to copy newArray into the oldArray
     *
     * @param oldArray array with the previous state of the grid
     * @param newArray array with the latest state of the grid
     */
    public static void gridCopy(int[][] oldArray, int[][] newArray) {
        for (int i = 0; i < oldArray.length; i++) {
            for (int j = 0; j < oldArray[i].length; j++) {
                oldArray[i][j] = newArray[i][j];
            }
        }
    }
}
