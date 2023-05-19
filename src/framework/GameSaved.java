package framework;

import java.io.Serializable;

/**
 * GameState is a class that contains the state of the game. This class is used
 * to save the state of the game the board matrix and level to a file.
 * 
 * The class is Serializable so that it can be saved to a file.
 * And uses the memento pattern to save the state of the game.
 */
public class GameSaved implements Serializable {

    int[][] board;
    String score;
    String level;

    /**
     * Constructor for the GameState class.
     * 
     * @param board is the board matrix.
     */
    public GameSaved(int[][] board) {
        this.board = board;
    }

    /**
     * Returns the board matrix.
     * 
     * @return board
     */
    public int[][] getBoard() {
        return board;
    }

    /**
     * Sets the board matrix.
     * 
     * @param board is the board matrix.
     */
    public void setBoard(int[][] board) {
        this.board = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            System.arraycopy(board[i], 0, this.board[i], 0, board[0].length);
        }
    }

    /**
     * Returns the game name.
     * 
     * @return game
     */
    public String getGame() {
        return score;
    }

    /**
     * Sets the game name.
     * 
     * @param game is the game name.
     */
    public void setGame(String score) {
        this.score = score;
    }

    /**
     * Sets the level.
     * 
     * @param level is the level.
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * Returns the level.
     * 
     * @return level
     */
    public String getLevel() {
        return level;
    }

}
