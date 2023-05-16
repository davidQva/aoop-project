package framework;

import java.net.URL;
import java.util.ArrayList;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public abstract class AbstractTileModel {
    
    protected int[][] board;
    private Controller controller;
    private GameFrame frame;
    private GameView view;
    private GameStateAndDirection gameStatus;
    private GameStateAndDirection gameStarted;
    private GameStateAndDirection update;
    private ArrayList<GameObserver> observers = new ArrayList<GameObserver>();
    private GameState gameState;
    private String level;
    private AbstractTileModel game;
    
    public AbstractTileModel(int col, int row, int size) {
        board = new int[col][row];
        gameState = new GameState(board);
        view = new GameView(this, size);
        frame = new GameFrame(view, this);
        controller = new Controller(this, view);
        attach(view);
    }

    public void addGame(AbstractTileModel game) {
        this.game = game;
    }

    public void getGame() {
        this.game = game;
    }

    public void attach(GameObserver observer) {
        this.observers.add(observer);
    }
    
    public void notifyAllObservers() {
        for (GameObserver observer : observers) {
            observer.notify(board, update);
        }
    }
    

    public GameStateAndDirection getUpdate() {
        return update;
    }

    public void setUpdate(GameStateAndDirection update) {
        this.update = update;
    }

    public GameStateAndDirection getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStateAndDirection gameStatus) {
        this.gameStatus = gameStatus;
    }

    public Controller getController() {
        return controller;
    }

    public GameFrame getFrame() {
        return frame;
    }  

    public GameView getView() {
        return view;
    }

    public void addTile(Integer key, Tile tile) {
        view.addTiles(key, tile);
    }

    public Tile getTile(Integer key) {
        return view.getTiles(key);
    }

    public void getTileSize() {
        view.getTileSize();
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
        notifyAllObservers();
    }   

    public void addTile(String fileNamePNG, int x, Tile protoType) {

        Tile thisTile = (Tile) protoType.clone();
        URL url = ClassLoader.getSystemResource(fileNamePNG + ".png");
        Image imgWall;

        try {
            imgWall = ImageIO.read(url);
            Image scaledimgWall = imgWall.getScaledInstance(view.getTileSize(), view.getTileSize(), Image.SCALE_SMOOTH);
            ImageIcon imageIconWall = new ImageIcon(scaledimgWall);
            thisTile.setTile(imageIconWall);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        view.addTiles(x, thisTile);
    }

    public void saveGameState(String fileName) {

        String className  = this.getClass().getSimpleName();
        
        gameState.setBoard(board);
        gameState.setGame(className);
        gameState.setLevel(level);       

        try {                
            FileOutputStream fileOut = new FileOutputStream(className+"."+fileName);   
            ObjectOutputStream out = new ObjectOutputStream(fileOut);            
            out.writeObject(gameState);
            out.close();
            System.out.println("Gamestate saved as " + className +"." + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }     

    }

    public void loadGameState(String fileName) {       
        GameState temp = null;
        String className  = this.getClass().getSimpleName();
        try {
            FileInputStream fileIn = new FileInputStream(className+"."+fileName);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);     
            temp = (GameState) objectIn.readObject();
            objectIn.close();
            fileIn.close();
            System.out.println("Gamestate loaded from " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < temp.getBoard().length; i++) {
            for(int j = 0; j < temp.getBoard()[0].length; j++) {
                board[i][j] = temp.getBoard()[i][j];
            }
        }


        level = (String)temp.getLevel();
        update = GameStateAndDirection.GAME_LOAD;       
        notifyAllObservers();
    }



    public void saveGame(String fileName, AbstractTileModel game) {

        String className  = this.getClass().getSimpleName();     

        try {                
            FileOutputStream fileOut = new FileOutputStream(className+"."+fileName);   
            ObjectOutputStream out = new ObjectOutputStream(fileOut);            
            out.writeObject(game);
            out.close();
            System.out.println("Game saved as " + className +"." + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }       
    }

    public void loadGame(String fileName) {       
           
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);     
            AbstractTileModel temp = (AbstractTileModel) objectIn.readObject();
            objectIn.close();
            fileIn.close();
            System.out.println("Game loaded from " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
}

    public void repaintBoard() {
        view.paintBoard(board);
    }

    public abstract void input(GameStateAndDirection direction);

    public void setRows(int row) {
       // TODO Auto-generated method stub
    }

    public GameStateAndDirection getGameStarted() {
       return gameStarted;
    }

    public void setGameStarted(GameStateAndDirection gameStarted) {
       this.gameStarted = gameStarted;
    }

    public void setValue(int i, int j, int k) {
         board[i][j] = k;
    }  
    
    public void resetBoard() {
        int[][] board = new int[getBoard().length][getBoard()[0].length];
        setBoard(board);
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }        

}