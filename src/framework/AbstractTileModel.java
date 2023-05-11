package framework;
import java.net.URL;
import java.util.ArrayList;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public abstract class AbstractTileModel {
    
    protected int[][] board;
    protected Controller controller;
    protected GameFrame frame;
    protected GameView view;  
    private ArrayList<GameObserver> observers = new ArrayList<GameObserver>();
    private GameStateAndDiraction direction;
    
    public void attach(GameObserver observer) {       
        this.observers.add(observer);
    }

    public void notifyAllObservers() {
        for (GameObserver observer : observers) {
            observer.updateGame(board, direction);
        }
    }

    public AbstractTileModel(int col, int row, int size) {
        board = new int[col][row];
        view = new GameView(this, size);
        frame = new GameFrame(view, this);
        controller = new Controller(this, view);
        attach(view);
    }

    public GameStateAndDiraction getDirection() {
        return direction;
    }

    public Controller getController() {
        return controller;
    }

    public GameFrame getFrame() {
        return frame;
    }


    public int[][] getBoard() {
        return board;
    }
    
    public void setBoard(int[][] board, GameStateAndDiraction direction) {
        this.board = board;
        this.direction = direction;
        notifyAllObservers();
    }    
    
    public void addTile(Integer key, Tile tile) {
        view.addTiles(key, tile);
    }

    public Tile getTile(Integer key) {
        return view.getTiles(key);
    }

    public void addTile(String fileNamePNG, int x, Tile protoType) {
        
        Tile thisTile = (Tile)protoType.clone();
        URL url = getClass().getResource("/audio/" + fileNamePNG + ".png");  
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

    public abstract void move(GameStateAndDiraction direction);

    public abstract void moveUp();

    public abstract void moveDown();

    public abstract void moveLeft();

    public abstract void moveRight();

}