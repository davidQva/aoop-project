package application;

import framework.StateAndDirection;
import framework.Observer;

/**
 * PrintOut is an observer that prints the game board and the state of the game
 * to the console.
 */
public class PrintOut implements Observer {

    @Override
    public void notify(int[][] board, StateAndDirection direction) {
        for (int i = 0; i < board.length; i++) {
            System.out.println();
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j]);
            }
        }
        System.out.println();
        System.out.println(direction);
    }
}
