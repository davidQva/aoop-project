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

public class GameView extends JPanel implements GameObserver{

    private AbstractTileModel game;
    private TileRegistry tileRegistry;
    private GridLayout grid;
    private JLabel[][] label;
    private int tileSize;
    private JList<String> gameList;
    

    @Override
    public void notify(int[][] board, GameStateAndDirection direction) {        
        
        paintBoard(board);
        
        if (direction == GameStateAndDirection.GAME_PAUSE) {

            int option = JOptionPane.showConfirmDialog(this, "Do you want to save the game?", game.getClass() + " Game",
                    JOptionPane.YES_NO_CANCEL_OPTION);

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

        /*  if(direction == GameStateAndDirection.GAME_PAUSE) {
            JFrame frame = new JFrame();
            GridLayout grid = new GridLayout(2,1);
            frame.setLayout(grid);         
            JButton button = new JButton("Save Game");
            JButton button2 = new JButton("Load Game");

            button.addActionListener(e -> {
              //  game.saveGame(askForFileName());
                frame.dispose();
            });
            button2.addActionListener(e -> {
                game.loadGame(askForLoadFileName());
                frame.dispose();
            });

            frame.setLocationRelativeTo(null);
            frame.add(button);
            frame.add(button2);
            frame.pack();
            frame.setVisible(true);
        }  */

      /*   if(direction == GameStateAndDirection.GAME_PAUSE) {    
            
            
            JFrame frame = new JFrame("Pause Menu");
            frame.setLocationRelativeTo(null);
            frame.setSize(400, 300);
            frame.setResizable(false);

            gameList = new JList<>(new String[] { "Save Game", "Load Game", "Resume Game" });         

            

            frame.setLayout(new BorderLayout());
            
           
            JButton button = new JButton("Save Game");
            JButton button2 = new JButton("Load Game");
            JButton button3 = new JButton("Resume Game");

            button.addActionListener(e -> {
                game.saveGame(askForFileName());
                frame.dispose();
            });
            button2.addActionListener(e -> {
                game.loadGame(askForLoadFileName());
                frame.dispose();
            });

            button3.addActionListener(e -> {
                game.setGameStatus(GameStateAndDirection.GAME_UNPAUSE);
                frame.dispose();
            });
            
            buttonPanel.add(button);
            buttonPanel.add(button2);
            buttonPanel.add(button3);
            
            
            frame.add(fileChooser, BorderLayout.CENTER);
            frame.add(buttonPanel, BorderLayout.SOUTH);      
            frame.pack();
            frame.setVisible(true); 

        } */   

     /*    if (game.getGameStatus() == GameStateAndDirection.GAME_OVER)
        JOptionPane.showMessageDialog(this, "Game Over", game.getClass() + " Game",
                    JOptionPane.INFORMATION_MESSAGE);
        if (direction == GameStateAndDirection.GAME_OVER) {
            if (JOptionPane.showConfirmDialog(this, "Do you want to play again?", game.getClass() + " Game",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                    System.out.println("bajsnisse");
                    else
                    System.exit(0);
                }            */    
                
    }

    private static String askForFileName() {
        return JOptionPane.showInputDialog(null, "Enter the name of the save file:", "Save Game",
                JOptionPane.PLAIN_MESSAGE);
    }

    private static String askForLoadFileName() {
        return JOptionPane.showInputDialog(null, "Enter the name of the load file:", "Load Game",
                JOptionPane.PLAIN_MESSAGE);
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
