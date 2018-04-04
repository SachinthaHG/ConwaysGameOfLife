package com.bglcrop.game.client;

import com.bglcrop.game.models.Point;
import com.bglcrop.game.services.Game;

import java.util.ArrayList;
import java.util.List;

/**
 * This class includes the client side code
 */
public class GameClient {
    public static void main(String[] args) {
        /*Input set is given as a list of Point objects. You can modify this list and check for different test cases*/
        List<Point> initialLiveCells = new ArrayList<>();
        initialLiveCells.add(new Point(5, 5));
        initialLiveCells.add(new Point(6, 5));
        initialLiveCells.add(new Point(7, 5));
        initialLiveCells.add(new Point(5, 6));
        initialLiveCells.add(new Point(6, 6));
        initialLiveCells.add(new Point(7, 6));

        /*Initializing the game. You can change the parameters for different test cases*/
        Game game = new Game(200, 200, 100, initialLiveCells);

        /*Start the game*/
        game.start();

    }
}
