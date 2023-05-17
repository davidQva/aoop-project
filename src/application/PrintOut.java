package application;

import framework.GameStateAndDirection;
import framework.GameObserver;

/**
 * PrintOut is an observer that prints the game board and the state of the game
 * to the console.
 */
public class PrintOut implements GameObserver {

    @Override
    public void notify(int[][] board, GameStateAndDirection direction) {
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
