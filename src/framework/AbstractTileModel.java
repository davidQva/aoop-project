package framework;

public abstract class AbstractTileModel {

    private int[][] board;
    private Controller controller;
    private GameFrame frame;
    protected GameView view;

    public AbstractTileModel(int col, int row, int size) {
        board = new int[col][row];
        view = new GameView(this, size);
        frame = new GameFrame(view, this);
        controller = new Controller(this, view);
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

    public void setBoard(int[][] board) {
        this.board = board;          
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

    public abstract void move(Direction direction);

    public abstract void moveUp();

    public abstract void moveDown();

    public abstract void moveLeft();

    public abstract void moveRight();

}