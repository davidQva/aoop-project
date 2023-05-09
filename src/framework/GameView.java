package framework;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameView extends JPanel {
   
    private TileModel game;
    private TileRegistry tileRegistry;
    private GridLayout grid;
    private JLabel[][] label;

    public GameView(TileModel game, int size) {
     
       // addKeyListener(new Controller(game, this));
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
                label[r][c].setPreferredSize(new Dimension(size, size));
                this.add(label[r][c]);
            }
        }
        
    }

    public void paintBoard() {
        int[][] board = game.getBoard();
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                setTile(tileRegistry.getPrototypeTile(board[r][c]), c, r);
            }
        }
    }

    public void setTile(Tile tile, int col, int row) {
        label[row][col].setIcon(tile.getTile());
    } 

    public void addTiles(Integer key, Tile tile) {
        tileRegistry.addPrototypeTile(key, tile);
    }
    
}
