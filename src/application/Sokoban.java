package application;
import java.awt.Point;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import framework.AbstractTileModel;
import framework.GameStateAndDirection;

public class Sokoban extends AbstractTileModel implements Serializable {

    private static final int BOX = 0;
    private static final int BOX_ON_TARGET = 1;
    private static final int EMPTY = 2;
    private static final int TARGET = 3;
    private static final int WALL = 4;
    private static final int PLAYER = 5;
    private static final int PLAYER_ON_TARGET = 6;
    private ArrayList<Point> targetPositions;
   // private String level;

   // private int[][] board;

    private int playerX;
    private int playerY;

    private int col, row;

    GameManager gameManager;

    public Sokoban(int col, int row, int size) {

          super(col, row, size);
          addGame(this);
         if (getLevel() == null)
            setLevel("map01");
        gameManager = new GameManager();
        initializeBoard(getLevel());
    } 


    public void update(GameStateAndDirection update) { 

        checkWin();
        checkGameOver();
        findPlayerAndUpdatePos();
        super.setUpdate(update);
        notifyAllObservers();

        if (super.getGameStatus() == GameStateAndDirection.GAME_WON && super.getGameStarted() == GameStateAndDirection.GAME_START) {
            super.setGameStatus(GameStateAndDirection.GAME_START);
            initializeBoard(getLevel());         
        } else if (super.getGameStatus() == GameStateAndDirection.GAME_OVER && super.getGameStarted() == GameStateAndDirection.GAME_START) {
            super.setGameStatus(GameStateAndDirection.GAME_START);
            initializeBoard(getLevel());
        } else if (super.getGameStatus() == GameStateAndDirection.GAME_UNPAUSE) {
           super.setGameStatus(GameStateAndDirection.GAME_START);
            super.setGameStarted(GameStateAndDirection.GAME_START);                 
        }     
    }

    private void initializeBoard(String level) {

        // finds the target positions and adds them to the targetPositions arraylist
        // gameManager.scanLevel(getBoard(), targetPositions);
        targetPositions = new ArrayList<Point>();
        this.board = gameManager.fileLevelScan("resources/" + level + ".txt", targetPositions,
        board.length, board[0].length);
        setBoard(board);
       // this.getBoard() = getBoard();
        findPlayerAndUpdatePos();
        super.setGameStarted(GameStateAndDirection.GAME_START);
        update(GameStateAndDirection.GAME_START);

    }

    @Override
    public void input(GameStateAndDirection currentInput) {

        if(getUpdate() == GameStateAndDirection.GAME_LOAD){
            targetPositions = new ArrayList<Point>();
          findPlayerAndUpdatePos();
          gameManager.fileLevelScan("resources/" + getLevel() + ".txt", targetPositions,
          board.length, board[0].length);          
        }     
        
        switch (currentInput) {
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
                super.setGameStarted(GameStateAndDirection.GAME_PAUSE);
                update(GameStateAndDirection.GAME_PAUSE);
                break;
            default:
                break;

        }

    }

    public void findPlayerAndUpdatePos() {

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

    public void moveUp() {

       // findPlayerAndUpdatePos();

        int destX = playerX;
        int destY = playerY - 1;

        // Check if the player can move right
        if (isValidMove(playerX, playerY, destX, destY)) {
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

            playerStepOverTargetRepaint(destX, destY, playerValue, destTile);

        }

        update(GameStateAndDirection.UP);

    }

    public void moveRight() {

       // findPlayerAndUpdatePos();

        int destX = playerX + 1;
        int destY = playerY;

        // Check if the player can move right
        if (isValidMove(playerX, playerY, destX, destY)) {
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
            playerStepOverTargetRepaint(destX, destY, playerValue, destTile);

        }

        update(GameStateAndDirection.RIGHT);

    }

    public void moveLeft() {

     //   findPlayerAndUpdatePos();

        int destX = playerX - 1;
        int destY = playerY;

        // Check if the move is valid
        if (isValidMove(playerX, playerY, destX, destY)) {
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

            playerStepOverTargetRepaint(destX, destY, playerValue, destTile);

        }

        update(GameStateAndDirection.LEFT);

    }

    public void moveDown() {

      //  findPlayerAndUpdatePos();

        // Calculate the destination coordinates
        int destX = playerX;
        int destY = playerY + 1;

        // Check if the move is valid
        if (isValidMove(playerX, playerY, destX, destY)) {
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

            playerStepOverTargetRepaint(destX, destY, playerValue, destTile);

            // Update the getBoard() state and repaint
            update(GameStateAndDirection.DOWN);

        }
    }

    private void playerStepOverTargetRepaint(int destX, int destY, int playerValue, int destTile) {        
        

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

    private boolean isValidMove(int startX, int startY, int destX, int destY) {

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

    private boolean isValidBoxMove(int boxX, int boxY, int destX, int destY) {

        findPlayerAndUpdatePos();

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

    private boolean isBoxStuck(int col, int row) {
        // Check if the box is stuck in a corner
        boolean up = (row > 0
                && (getBoard()[row - 1][col] == WALL) || (getBoard()[row - 1][col] == BOX) || (getBoard()[row -1][col] == BOX_ON_TARGET));
        boolean down = (row < getBoard().length - 1
                && (getBoard()[row + 1][col] == WALL) || (getBoard()[row + 1][col] == BOX) || (getBoard()[row + 1][col] == BOX_ON_TARGET));
        boolean left = (col > 0
                && (getBoard()[row][col - 1] == WALL) || (getBoard()[row][col -1] == BOX) || (getBoard()[row][col -1] == BOX_ON_TARGET));
        boolean right = (col < getBoard()[row].length - 1
                && (getBoard()[row][col + 1] == WALL) || (getBoard()[row][col +1] == BOX) || (getBoard()[row][col +1] == BOX_ON_TARGET));
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
            return true;
        }

        return false;

    }

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
                        if(countBoxes == stuckBoxes)
                        super.setGameStatus(GameStateAndDirection.GAME_OVER);                                               
                        //notifyAllObservers();
                        return true;
                    }
                }
            }
        }       
        return false;
    }

    public boolean checkWin() {

        for (Point target : targetPositions) {
            int targetX = target.x;
            int targetY = target.y;

            if (getBoard()[targetY][targetX] != BOX_ON_TARGET) {
                return false;
            }
        }

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
        super.setGameStatus(GameStateAndDirection.GAME_WON);
        return true;
    }

    public int getPlayerPositionX() {
        return playerX;
    }

    public int getPlayerPositionY() {
        return playerY;
    }

    public void setPlayerPositionX(int x) {
        playerX = x;
    }

    public void setPlayerPositionY(int y) {
        playerY = y;
    }
   
}