package application;

import framework.GameStateAndDiraction;
import framework.GameObserver;

public class PrintOut implements GameObserver {

    @Override
    public void updateGame(int[][] board, GameStateAndDiraction direction) {
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
