package framework;
import java.util.ArrayList;

public abstract class AbstractTileModel {
    //:q should the board be private? it is used alot in child classes

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

    public abstract void move(GameStateAndDiraction direction);

    public abstract void moveUp();

    public abstract void moveDown();

    public abstract void moveLeft();

    public abstract void moveRight();

}