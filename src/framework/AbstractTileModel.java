package framework;

import java.net.URL;
import java.util.ArrayList;
import java.awt.Image;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * AbstractTileModel is the abstract class for the game.
 * It is used to create the board, game state, view, frame, and controller.
 * It uses a MVC pattern to create the framework.
 * 
 * Adding controller input to the game is done by using the inputController
 * interface.
 * inputController uses the strategy pattern to add input to the game.
 * Addingobserver to the game is done by using the GameObserver interface.
 * 
 * GameObserver uses the observer pattern to add observers to the game.
 * 
 * Adding tiles to the game is done by using the Tile interface.
 * Create a prototype of the tile and add it to the game.
 * 
 * Then add the tile to the game by using the addTile method and
 * linking the tile to a number and image.
 * 
 * Saving and loading the game is done by using the Memento pattern.
 * GameState is the caretaker and gameState, level and the board are the
 * memento.
 * 
 */
public abstract class AbstractTileModel {

    private int[][] board;
    private Controller controller;
    private GameFrame frame;
    private GameView view;
    private GameStateAndDirection gameStatus;
    private GameStateAndDirection gameStarted;
    private GameStateAndDirection update;
    private ArrayList<GameObserver> observers = new ArrayList<GameObserver>();
    private GameState gameState;
    private String level;

    /**
     * AbstractTileModel is the constructor for the AbstractTileModel class.
     * It is used to create the board, game state, view, frame, and controller.
     * It also attaches the view to the observer.
     * 
     * @param col
     * @param row
     * @param size
     */
    public AbstractTileModel(int col, int row, int size) {
        board = new int[col][row];
        gameState = new GameState(board);
        view = new GameView(this, size);
        frame = new GameFrame(view, this);
        controller = new Controller(this);
        attach(view);
    }

    /**
     * Attach is used to add an observer to the list of observers.
     * 
     * @param observer must use the GameObserver interface.
     */
    public void attach(GameObserver observer) {
        this.observers.add(observer);
    }

    /**
     * Observer pattern is used to notify the observers of the game.
     * attach is used to add an observer to the list of observers.
     * The observers are notified when the board and update are passed to the notify
     * method.
     */
    public void notifyAllObservers() {
        for (GameObserver observer : observers) {
            observer.notify(board, update);
        }
    }

    /**
     * returns the update.
     * 
     * @return update can be one of the following:
     *         UP, DOWN, LEFT, RIGHT, GAME_OVER, GAME_WON, GAME_PAUSE, GAME_RESUME,
     *         GAME_START, GAME_SAVE, GAME_LOAD.
     */
    public GameStateAndDirection getUpdate() {
        return update;
    }

    /**
     * update is used to set the direction and update the game.
     * this is what is passed to the observers.
     * 
     * @param update
     */
    public void setUpdate(GameStateAndDirection update) {
        this.update = update;
    }

    /**
     * Gets the game status.
     * 
     * @return
     */
    public GameStateAndDirection getGameStatus() {
        return gameStatus;
    }

    /**
     * gameStatus is used to set the game status and direction.
     * 
     * @param gameStatus ,the game status to be set should only be one of the
     *                   following:
     *                   GAME_OVER, GAME_WON, GAME_PAUSE, GAME_RESUME, GAME_START,
     *                   GAME_SAVE, GAME_LOAD.
     */
    public void setGameStatus(GameStateAndDirection gameStatus) {
        this.gameStatus = gameStatus;
    }

    /**
     * Sets the gameStarted to the parameter.
     * gameStarted is used to keep track of the game has started or paused.
     * 
     * @param gameStarted , gameStarted should only be set to GAME_START or
     *                    GAME_PAUSE.
     */
    public void setGameStarted(GameStateAndDirection gameStarted) {
        this.gameStarted = gameStarted;
    }

    /**
     * Returns the gameStarted.
     * only returns GAME_START or GAME_PAUSE.
     * 
     * @return
     */
    public GameStateAndDirection getGameStarted() {
        return gameStarted;
    }

    /**
     * Gets the tile.
     * 
     * @param key ,the key to find the tile in the hashmap.
     * @return tile from hashmap.
     */
    public Tile getTile(Integer key) {
        return view.getTiles(key);
    }

    /**
     * Gets the tile size.
     */
    public int getTileSize() {
        return view.getTileSize();
    }

    /**
     * Gets the board.
     * 
     * @return board from the game.
     */
    public int[][] getBoard() {
        return board;
    }

    /**
     * Sets the board.
     * 
     * @param board ,the board to be set.
     */
    public void setBoard(int[][] board) {
        this.board = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            System.arraycopy(board[i], 0, this.board[i], 0, board[0].length);
        }
    }

    /**
     * Adds a tile to the game.
     * 
     * @param key  ,the key to be used in the hashmap and to asoociate the tile with
     *             the board.
     * @param tile ,the tile to be added.
     */
    public void addTile(int key, Tile tile) {
        view.addTiles(key, tile);
    }

    /**
     * Adds a tile to the game.
     * Using a prototype pattern to clone the tile.
     * 
     * @param fileNamePNG ,the name of the file to be loaded from resources.
     * @param x           ,the key to be used in the hashmap and to asoociate the
     *                    tile with the board.
     * @param protoType   ,the prototype to be cloned.
     * @throws IOException
     */
    public void addTile(int x, Tile protoType, String fileNamePNG) {

        Tile thisTile = (Tile) protoType.clone();
        URL url = ClassLoader.getSystemResource(fileNamePNG + ".png");
        Image imgWall;

        try {
            imgWall = ImageIO.read(url);
            Image scaledimgWall = imgWall.getScaledInstance(view.getTileSize(), view.getTileSize(), Image.SCALE_SMOOTH);
            ImageIcon imageIconWall = new ImageIcon(scaledimgWall);
            thisTile.setTile(imageIconWall);
        } catch (IOException e) {
            e.printStackTrace();
        }
        view.addTiles(x, thisTile);
    }

    /**
     * Saves the current gamestate to a serialized file.
     * gamestate contains the board, level and game.
     * 
     * @param fileName ,the name of the file to be saved.
     *                 the saved file will be named as the game class name plus
     *                 fileName.
     * @throws IOException if the file is not found.
     */
    public void saveGameState(String fileName) {

        String className = this.getClass().getSimpleName();

        gameState.setBoard(board);
        gameState.setGame(className);
        gameState.setLevel(level);

        try {
            FileOutputStream fileOut = new FileOutputStream(className + "." + fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(gameState);
            out.close();
            System.out.println("Gamestate saved as " + className + "." + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Loads a serialized file and sets the gamestate to the loaded gamestate.
     * Gamestate is then used to set the board and level.
     * 
     * @param fileName ,the name of the file to be loaded.
     *                 if the file is not found, an error will be thrown.
     *                 class name is used to find the file and prevent loading the
     *                 wrong file.
     * @throws IOException if the file is not found.
     */
    public void loadGameState(String fileName) {
        GameState temp = null;
        String className = this.getClass().getSimpleName();
        try {
            FileInputStream fileIn = new FileInputStream(className + "." + fileName);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            temp = (GameState) objectIn.readObject();
            objectIn.close();
            fileIn.close();
            System.out.println("Gamestate loaded from " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Gamestate not found");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (temp == null) {
            return;
        } 

        this.board = null;
        this.board = new int[temp.getBoard().length][temp.getBoard()[0].length];

        for (int i = 0; i < temp.getBoard().length; i++) {
            for (int j = 0; j < temp.getBoard()[0].length; j++) {
                board[i][j] = temp.getBoard()[i][j];
            }
        }

        level = (String) temp.getLevel();
        update = GameStateAndDirection.GAME_LOAD;            
        notifyAllObservers();
    }

    /**
     * Sets the value of the board at position i,j to k.
     * 
     * @param i row
     * @param j column
     * @param k value
     */
    public void setValue(int i, int j, int k) {
        board[i][j] = k;
    }

    /**
     * Gets the value of the board at position i,j.
     * 
     * @param i row
     * @param j column
     * @return value
     */
    public int getValue(int i, int j) {
        return board[i][j];
    }

    /**
     * Sets the level to the parameter.
     * Used when loading a game. And Changing level.
     * 
     * @param level
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * Returns the level.
     * 
     * @return
     */
    public String getLevel() {
        return level;
    }

    /**
     * Gets the controller.
     * 
     * @return controller from MVC pattern.
     */
    public Controller getController() {
        return controller;
    }

    /**
     * Gets the frame.
     * 
     * @return frame
     */
    public GameFrame getFrame() {
        return frame;
    }

    /**
     * repaints the board.
     */
    public void repaintBoard() {
        view.paintBoard(board);
    }
    
    /**
     * Gets the view.
     * 
     * @return view
     */
    public GameView getView() {
        return view;
    }

    /**
     * Abstract method to be implemented in subclasses.
     */

    /**
     * abstract method for input, implemented in subclasses.
     * 
     * @param newInput ,the input to be used. The input should be a
     *                 GameStateAndDirection.
     *                 GameStateAndDirection is used to keep track of the gamestate.
     *                 Contains the gamestate and the direction of the player.
     *                 input() is used to update the gamestate. Controller calls
     *                 input() when a key is pressed.
     */
    public abstract void input(GameStateAndDirection newInput);


}