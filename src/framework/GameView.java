package framework;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameView extends JPanel implements GameObserver {

    private AbstractTileModel game;
    private TileRegistry tileRegistry;
    private GridLayout grid;
    private JLabel[][] label;
    private int tileSize;

    @Override
    public void updateGame(int[][] board, GameStateAndDirection direction) {
        paintBoard(board);
    }

    public GameView(AbstractTileModel game, int tileSize) {

        this.tileSize = tileSize;
        this.game = game;
        int row = game.getBoard().length;
        int col = game.getBoard()[0].length;

        tileRegistry = new TileRegistry();
        grid = new GridLayout(row, col);
        this.setLayout(grid);

        label = new JLabel[row][col];

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                label[r][c] = new JLabel();
                label[r][c].setPreferredSize(new Dimension(tileSize, tileSize));
                this.add(label[r][c]);
            }
        }

    }

    public void paintBoard(int[][] board) {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (tileRegistry.getPrototypeTile(board[r][c]) != null)
                    setTile(tileRegistry.getPrototypeTile(board[r][c]), c, r);
            }
        }
    }

    public void win() {
        
        JButton win = new JButton("You Win!");
        label[0][0].add(win);     
       
    }

    public void setTile(Tile tile, int col, int row) {
        label[row][col].setIcon(tile.getTile());
    }

    public void addTiles(Integer key, Tile tile) {
        if (tile != null)
            tileRegistry.addPrototypeTile(key, tile);
    }

    public Tile getTiles(Integer key) {
        return tileRegistry.getPrototypeTile(key);
    }

    public int getTileSize() {
        return tileSize;
    }

    public void getBoard(int[][] board) {
        this.game.getBoard();
    }

}
