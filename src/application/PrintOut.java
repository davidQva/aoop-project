package application;

import framework.GameStateAndDirection;

import java.io.Serializable;

import framework.GameObserver;

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
