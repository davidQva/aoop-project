package application;

import java.awt.Point;
import java.util.ArrayList;
import framework.AbstractTileModel;
import framework.MapLoader;
import framework.StateAndDirection;

public class SokobanGame extends AbstractTileModel {

    private static final int BOX = 0;
    private static final int BOX_ON_TARGET = 1;
    private static final int EMPTY = 2;
    private static final int TARGET = 3;
    private static final int WALL = 4;
    private static final int PLAYER = 5;
    private static final int PLAYER_ON_TARGET = 6;
    private ArrayList<Point> targetPositions;

    private int playerX;
    private int playerY;

    MapLoader gameManager;

    public SokobanGame(int col, int row, int size) {      

        super(col, row, size);
        if (getLevel() == null)
            setLevel("map01");
        gameManager = new MapLoader();
        initializeBoard(getLevel());

    }

    /**
     * update method is called when all the new states are set to the model
     * updates the model and notifies all observers
     * 
     * @param update
     */
    public void update(StateAndDirection update) {

        checkWin();
        checkGameOver();
        updatePlayerPos();
        setUpdate(update);
        notifyAllObservers();

        if (super.getGameStatus() == StateAndDirection.GAME_WON
                && super.getGameStarted() == StateAndDirection.GAME_START) {
            super.setGameStatus(StateAndDirection.GAME_START);
           System.out.println("You won!");
            initializeBoard(getLevel());
        } else if (super.getGameStatus() == StateAndDirection.GAME_OVER
                && super.getGameStarted() == StateAndDirection.GAME_START) {
            super.setGameStatus(StateAndDirection.GAME_START);
            System.out.println("You lost!");
            initializeBoard(getLevel());
        } else if (super.getGameStatus() == StateAndDirection.GAME_UNPAUSE) {
            super.setGameStatus(StateAndDirection.GAME_START);
            super.setGameStarted(StateAndDirection.GAME_START); 
        }
    }

    /**
     * Initialize the board with the given level
     * updates targetPositions with the target positions, and
     * finds the player and updates playerX and playerY
     * 
     * @param level
     */
    public void initializeBoard(String level) {

        targetPositions = new ArrayList<Point>();
        setBoard(gameManager.fileLevelScan("resources/" + level + ".txt", targetPositions,
                getBoard().length, getBoard()[0].length));

        updatePlayerPos();
        super.setGameStarted(StateAndDirection.GAME_START);
        update(StateAndDirection.GAME_START);

    }

    /**
     * input() is called from the controller when a key is pressed
     * 
     * @param newInput the key that was pressed by the user, can only be UP, DOWN,
     *                 LEFT, RIGHT
     *                 or GAME_PAUSE with the current implementation
     */
    @Override
    public void input(StateAndDirection newInput) {

        /*
         * If the game is loaded, we need to initialize the board
         * with the given level to find the player and update the
         * target positions.
         */
        if (getUpdate() == StateAndDirection.GAME_LOAD) {
            targetPositions = new ArrayList<Point>();
            updatePlayerPos();
            gameManager.fileLevelScan("resources/" + getLevel() + ".txt", targetPositions,
                    getBoard().length, getBoard()[0].length);
        }

        switch (newInput) {
            case UP:
                moveUp();
                break;
            case DOWN:
                moveDown();
                break;
            case LEFT:
                moveLeft();
                break;
            case RIGHT:
                moveRight();
                break;
            case GAME_PAUSE:
                super.setGameStarted(StateAndDirection.GAME_PAUSE);
                update(StateAndDirection.GAME_PAUSE);
                break;
            case MUTE:
                update(StateAndDirection.MUTE);
            default:
                break;

        }

    }

    /**
     * finds the player and updates playerX and playerY
     */
    public void updatePlayerPos() {

        int playerX = -1;
        int playerY = -1;

        // Find the current position of the player
        for (int row = 0; row < getBoard().length; row++) {
            for (int col = 0; col < getBoard()[0].length; col++) {
                if (getBoard()[row][col] == PLAYER || getBoard()[row][col] == PLAYER_ON_TARGET) {
                    playerX = col;
                    playerY = row;
                    break;
                }
            }
        }
        this.playerX = playerX;
        this.playerY = playerY;
    }

    /**
     * Checks if the player can move from the current position to the destination
     * position.
     */
    public void moveUp() {

        /**
         * The destination coordinates
         */
        int destX = playerX;
        int destY = playerY - 1;

        /**
         * Check if the player can move up
         */
        if (isValidPlayerMove(playerX, playerY, destX, destY)) {
            // Move the player to the destination coordinates
            int playerValue = getBoard()[playerY][playerX];
            int destTile = getBoard()[destY][destX];

            if (destTile == BOX || destTile == BOX_ON_TARGET) {
                // Push the box if there is one
                int boxX = destX;
                int boxY = destY;
                int boxDestX = destX;
                int boxDestY = destY - 1;

                moveBox(boxX, boxY, boxDestX, boxDestY);
            }

            /**
             * Move the player to the destination coordinates
             */
            movePlayer(destX, destY, playerValue, destTile);

        }

        /**
         * Update the model
         */
        update(StateAndDirection.UP);

    }

    public void moveRight() {

        // findPlayerAndUpdatePos();

        int destX = playerX + 1;
        int destY = playerY;

        // Check if the player can move right
        if (isValidPlayerMove(playerX, playerY, destX, destY)) {
            // Move the player to the destination coordinates
            int playerValue = getBoard()[playerY][playerX];
            int destTile = getBoard()[destY][destX];

            if (destTile == BOX || destTile == BOX_ON_TARGET) {
                // Push the box if there is one
                int boxX = destX;
                int boxY = destY;
                int boxDestX = destX + 1;
                int boxDestY = destY;

                moveBox(boxX, boxY, boxDestX, boxDestY);
            }
            movePlayer(destX, destY, playerValue, destTile);

        }

        update(StateAndDirection.RIGHT);

    }

    public void moveLeft() {

        // findPlayerAndUpdatePos();

        int destX = playerX - 1;
        int destY = playerY;

        // Check if the move is valid
        if (isValidPlayerMove(playerX, playerY, destX, destY)) {
            // Move the player to the destination coordinates
            int playerValue = getBoard()[playerY][playerX];
            int destTile = getBoard()[destY][destX];

            if (destTile == BOX || destTile == BOX_ON_TARGET) {
                // Push the box if there is one
                int boxX = destX;
                int boxY = destY;
                int boxDestX = destX - 1;
                int boxDestY = destY;
                // Move the box to the destination coordinates
                moveBox(boxX, boxY, boxDestX, boxDestY);
            }

            movePlayer(destX, destY, playerValue, destTile);

        }

        update(StateAndDirection.LEFT);

    }

    public void moveDown() {

        // findPlayerAndUpdatePos();

        // Calculate the destination coordinates
        int destX = playerX;
        int destY = playerY + 1;

        // Check if the move is valid
        if (isValidPlayerMove(playerX, playerY, destX, destY)) {
            // Move the player to the destination coordinates
            int playerValue = getBoard()[playerY][playerX];
            int destTile = getBoard()[destY][destX];

            if (destTile == BOX || destTile == BOX_ON_TARGET) {
                // Push the box if there is one
                int boxX = destX;
                int boxY = destY;
                int boxDestX = destX;
                int boxDestY = destY + 1;

                moveBox(boxX, boxY, boxDestX, boxDestY);
            }

            movePlayer(destX, destY, playerValue, destTile);

            // Update the getBoard() state and repaint
            update(StateAndDirection.DOWN);

        }
    }

    /**
     * set the new position of the player and box,
     * and the old position of the player and box.
     * 
     * @param destX
     * @param destY
     * @param playerValue
     * @param destTile
     */
    private void movePlayer(int destX, int destY, int playerValue, int destTile) {

        switch (destTile) {
            case TARGET:
                getBoard()[destY][destX] = PLAYER_ON_TARGET;
                getBoard()[playerY][playerX] = EMPTY;
                break;
            case EMPTY:
                getBoard()[destY][destX] = PLAYER;
                if (playerValue == PLAYER_ON_TARGET) {
                    getBoard()[playerY][playerX] = TARGET;
                } else {
                    getBoard()[playerY][playerX] = EMPTY;
                }
                break;
            case BOX_ON_TARGET:
                getBoard()[destY][destX] = PLAYER_ON_TARGET;
                getBoard()[playerY][playerX] = EMPTY;
                break;
            case BOX:
                getBoard()[destY][destX] = PLAYER;
                if (playerValue == PLAYER_ON_TARGET) {
                    getBoard()[playerY][playerX] = TARGET;
                } else {
                    getBoard()[playerY][playerX] = EMPTY;
                }
                break;
            default:
                break;
        }

    }

    /**
     * checks if the move is valid for the player
     * 
     * @param startX current x position
     * @param startY current y position
     * @param destX  destination x position
     * @param destY  destination y position
     * @return true if the move is valid, false otherwise
     */
    private boolean isValidPlayerMove(int startX, int startY, int destX, int destY) {

        // Check if the destination coordinates are within the getBoard() bounds
        if (destX < 0 || destX >= getBoard().length || destY < 0 || destY >= getBoard()[0].length) {
            return false;
        }

        // Check if the destination tile is a wall
        if (getBoard()[destY][destX] == WALL) {
            return false;
        }

        // Check if the destination tile is a box or box on a target
        if (getBoard()[destY][destX] == BOX || getBoard()[destY][destX] == BOX_ON_TARGET) {
            // Check if the box can be pushed
            int boxDestX = destX + (destX - startX);
            int boxDestY = destY + (destY - startY);

            // Check if the box destination is a wall
            if (getBoard()[boxDestY][boxDestX] == WALL) {
                return false;
            }

            // Check if the box destination is another box
            if (getBoard()[boxDestY][boxDestX] == BOX || getBoard()[boxDestY][boxDestX] == BOX_ON_TARGET) {
                return false;
            }

        }
        return true;
    }

    /**
     * moves the box to the destination coordinates
     * 
     * @param boxX  current x position
     * @param boxY  current y position
     * @param destX destination x position
     * @param destY destination y position
     */
    public void moveBox(int boxX, int boxY, int destX, int destY) {

        // Check if the destination is a valid position for the box
        if (isValidBoxMove(boxX, boxY, destX, destY)) {

            if (getBoard()[destY][destX] == TARGET) {
                getBoard()[destY][destX] = BOX_ON_TARGET;
            } else {
                getBoard()[destY][destX] = BOX;
            }
        }
    }

    /**
     * checks if the move is valid for the box
     * 
     * @param boxX  current x position
     * @param boxY  current y position
     * @param destX destination x position
     * @param destY destination y position
     * @return true if the move is valid, false otherwise
     */
    private boolean isValidBoxMove(int boxX, int boxY, int destX, int destY) {

        updatePlayerPos();

        // Check if the destination coordinates are within the getBoard() bounds
        int distanceX = Math.abs(boxX - playerX);
        int distanceY = Math.abs(boxY - playerY);

        // Check if the destination coordinates are within the getBoard() bounds
        if ((distanceX == 1 && distanceY == 0) || (distanceX == 0 && distanceY == 1)) {
            // Check if the destination is empty or a target tile
            int destTile = getBoard()[destY][destX];
            return destTile == EMPTY || destTile == TARGET;
        }

        return false;
    }

    /**
     * checks if the box is stuck in a corner or with other boxes
     * 
     * @param col box x position
     * @param row box y position
     * @return
     */
    private boolean isBoxStuck(int col, int row) {
        // Check if the box is stuck in a corner
        boolean up = (row > 0
                && (getBoard()[row - 1][col] == WALL) || (getBoard()[row - 1][col] == BOX)
                || (getBoard()[row - 1][col] == BOX_ON_TARGET));
        boolean down = (row < getBoard().length - 1
                && (getBoard()[row + 1][col] == WALL) || (getBoard()[row + 1][col] == BOX)
                || (getBoard()[row + 1][col] == BOX_ON_TARGET));
        boolean left = (col > 0
                && (getBoard()[row][col - 1] == WALL) || (getBoard()[row][col - 1] == BOX)
                || (getBoard()[row][col - 1] == BOX_ON_TARGET));
        boolean right = (col < getBoard()[row].length - 1
                && (getBoard()[row][col + 1] == WALL) || (getBoard()[row][col + 1] == BOX)
                || (getBoard()[row][col + 1] == BOX_ON_TARGET));

        // Check if the box is on a target, target can be in a corner
        for (Point target : targetPositions) {
            int targetX = target.x;
            int targetY = target.y;/*  */

            if (targetX == col && targetY == row) {
                getBoard()[row][col] = BOX_ON_TARGET;
                return false;
            }
        }

        if (up && left || up && right || down && left || down && right) {

            if(up && left && getBoard()[row-1][col-1] == WALL || (up && right && getBoard()[row-1][col+1] == WALL ||
            (down && left && getBoard()[row+1][col-1] == WALL || (down && right && getBoard()[row+1][col+1] == WALL)))){
                setGameStatus(StateAndDirection.GAME_OVER);                
            }

          



            return true;            
        }

        return false;

    }

    /**
     * checks if the game is over
     * 
     * @return true if the game is over, false otherwise
     */
    public boolean checkGameOver() {

        int countBoxes = 0;
        int stuckBoxes = 0;

        for (int row = 0; row < getBoard().length; row++) {
            for (int col = 0; col < getBoard()[row].length; col++) {
                if (getBoard()[row][col] == BOX) {
                    countBoxes++;
                    // Check if the box is stuck (surrounded by walls or other boxes)
                    if (isBoxStuck(col, row)) {
                        stuckBoxes++;
                    }
                }
            }
        }
        if (countBoxes == stuckBoxes){
            super.setGameStatus(StateAndDirection.GAME_OVER);
        return true;}
        return false;
    }

    /**
     * checks if the game is won by checking if all boxes are on targets
     * 
     * @return true if the game is won, false otherwise
     */
    public boolean checkWin() {

        for (Point target : targetPositions) {
            int targetX = target.x;
            int targetY = target.y;

            if (getBoard()[targetY][targetX] != BOX_ON_TARGET) {
                return false;
            }
        }

        super.setGameStatus(StateAndDirection.GAME_WON);
        switch (getLevel()) {
            case "map01":
                setLevel("map02");
                break;
            case "map02":
                setLevel("map03");
                break;
            default:
                break;
        }
        return true;
    }

    /**
     * returns the player positionX
     */
    public int getPlayerPositionX() {
        return playerX;
    }

    /**
     * returns the player positionY
     * 
     * @return
     */
    public int getPlayerPositionY() {
        return playerY;
    }

    /**
     * sets the player positionX
     * 
     * @param x
     */
    public void setPlayerPositionX(int x) {
        playerX = x;
    }

    /**
     * sets the player positionY
     * 
     * @param y
     */
    public void setPlayerPositionY(int y) {
        playerY = y;
    }

}