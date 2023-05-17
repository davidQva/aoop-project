package framework;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * GameFrame is the view in the MVC pattern.
 * It is a JPanel with the game board,
 * and uses the observer pattern to update the view when the model changes.
 * The board is painted with JLabels so it can be updated easily be
 * modifyed with different tile images and shapes.
 */

public class GameView extends JPanel implements GameObserver {

    private AbstractTileModel game;
    private TileRegistry tileRegistry;
    private GridLayout grid;
    private JLabel[][] label;
    private int tileSize;

    /**
     * notify is called by the model when the model has changed. The method
     * repaints the game board with the new state. The method also checks if the game is paused and
     * when paused check if the user wants to save/load the game.
     */
    @Override
    public void notify(int[][] board, GameStateAndDirection newState) {

        paintBoard(board);

        if (newState == GameStateAndDirection.GAME_PAUSE) {

            Object[] options = {"Save Game", "Load Game", "Unpause Game"};              

/* getTiles(6).getTile() */
            int option = JOptionPane.showOptionDialog(this, "Game is paused", game.getClass().getSimpleName(),
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);

            switch (option) {
                case JOptionPane.YES_OPTION:
                    game.saveGameState(askForFileName());
                    game.setGameStatus(GameStateAndDirection.GAME_UNPAUSE);
                    break;
                case JOptionPane.NO_OPTION:
                    game.loadGameState(askForLoadFileName());
                    game.setGameStatus(GameStateAndDirection.GAME_UNPAUSE);
                    break;
                case JOptionPane.CANCEL_OPTION:
                    game.setGameStatus(GameStateAndDirection.GAME_UNPAUSE);
                    return;
            }

        }

        /*
         * if(direction == GameStateAndDirection.GAME_PAUSE) {
         * JFrame frame = new JFrame();
         * GridLayout grid = new GridLayout(2,1);
         * frame.setLayout(grid);
         * JButton button = new JButton("Save Game");
         * JButton button2 = new JButton("Load Game");
         * 
         * button.addActionListener(e -> {
         * // game.saveGame(askForFileName());
         * frame.dispose();
         * });
         * button2.addActionListener(e -> {
         * game.loadGame(askForLoadFileName());
         * frame.dispose();
         * });
         * 
         * frame.setLocationRelativeTo(null);
         * frame.add(button);
         * frame.add(button2);
         * frame.pack();
         * frame.setVisible(true);
         * }
         */

        /*
         * if(direction == GameStateAndDirection.GAME_PAUSE) {
         * 
         * 
         * JFrame frame = new JFrame("Pause Menu");
         * frame.setLocationRelativeTo(null);
         * frame.setSize(400, 300);
         * frame.setResizable(false);
         * 
         * gameList = new JList<>(new String[] { "Save Game", "Load Game", "Resume Game"
         * });
         * 
         * 
         * 
         * frame.setLayout(new BorderLayout());
         * 
         * 
         * JButton button = new JButton("Save Game");
         * JButton button2 = new JButton("Load Game");
         * JButton button3 = new JButton("Resume Game");
         * 
         * button.addActionListener(e -> {
         * game.saveGame(askForFileName());
         * frame.dispose();
         * });
         * button2.addActionListener(e -> {
         * game.loadGame(askForLoadFileName());
         * frame.dispose();
         * });
         * 
         * button3.addActionListener(e -> {
         * game.setGameStatus(GameStateAndDirection.GAME_UNPAUSE);
         * frame.dispose();
         * });
         * 
         * buttonPanel.add(button);
         * buttonPanel.add(button2);
         * buttonPanel.add(button3);
         * 
         * 
         * frame.add(fileChooser, BorderLayout.CENTER);
         * frame.add(buttonPanel, BorderLayout.SOUTH);
         * frame.pack();
         * frame.setVisible(true);
         * 
         * }
         */

        /*
         * if (game.getGameStatus() == GameStateAndDirection.GAME_OVER)
         * JOptionPane.showMessageDialog(this, "Game Over", game.getClass() + " Game",
         * JOptionPane.INFORMATION_MESSAGE);
         * if (direction == GameStateAndDirection.GAME_OVER) {
         * if (JOptionPane.showConfirmDialog(this, "Do you want to play again?",
         * game.getClass() + " Game",
         * JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
         * System.out.println("bajsnisse");
         * else
         * System.exit(0);
         * }
         */

    }

    /**
     * Asks the user for a file name to save the game to.
     * 
     * @return the provided file name
     */
    private static String askForFileName() {
        return JOptionPane.showInputDialog(null, "Enter the name of the save file:", "Save Game",
                JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Asks the user for a file name to load the game from.
     * 
     * @return the provided file name
     */
    private static String askForLoadFileName() {
        return JOptionPane.showInputDialog(null, "Enter the name of the load file:", "Load Game",
                JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Constructor for GameView
     * creates a gridlayout and adds labels to the gridlayout
     * 
     * @param game     the model that contains the game board size
     * @param tileSize the size of the tiles
     */
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

    /**
     * Paints the game board, by setting the tiles on the board
     * to the corresponding tile in the tile registry
     * 
     * @param board the game board
     */
    public void paintBoard(int[][] board) {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (tileRegistry.getPrototypeTile(board[r][c]) != null)
                    setTile(tileRegistry.getPrototypeTile(board[r][c]), c, r);
            }
        }
    }

    /**
     * Sets a tile on the game board
     * 
     * @param tile the tile to be set
     * @param col  the column of the tile
     * @param row  the row of the tile
     */
    public void setTile(Tile tile, int col, int row) {
        label[row][col].setIcon(tile.getTile());
    }

    /**
     * Adds tiles to the tile registry
     * The tile is asoociated with the board value so
     * that the tile can be retrieved from the registry
     * and set on the game board
     * 
     * @param key  the key of the tile
     * @param tile the tile to be added
     */
    public void addTiles(Integer key, Tile tile) {
        if (tile != null)
            tileRegistry.addPrototypeTile(key, tile);
    }

    /**
     * Gets the tile from the tile registry
     * 
     * @param key the key of the tile
     * @return the tile
     */
    public Tile getTiles(Integer key) {
        return tileRegistry.getPrototypeTile(key);
    }

    /**
     * Gets the tile size
     * 
     * @return the tile size
     */
    public int getTileSize() {
        return tileSize;
    }

}
