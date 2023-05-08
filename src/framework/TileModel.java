package framework;

public class TileModel {

    private int[][] board;

    public TileModel(int col, int row) {
        board = new int[col][row];
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;          
    }
}