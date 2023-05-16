package framework;

import java.io.Serializable;

public class GameState implements Serializable {
    
    int[][] board;
    String game;
    String level;
    
    public GameState(int[][] board) {
        this.board = board;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            System.arraycopy(board[i], 0, this.board[i], 0, board[0].length);
        }
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }

}
