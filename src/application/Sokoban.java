package application;

import java.awt.Point;
import java.util.ArrayList;

import framework.AbstractTileModel;
import framework.GameStateAndDirection;

public class Sokoban extends AbstractTileModel {

    private static final int BOX = 0;
    private static final int BOX_ON_TARGET = 1;
    private static final int EMPTY = 2;
    private static final int TARGET = 3;
    private static final int WALL = 4;
    private static final int PLAYER = 5;
    private static final int PLAYER_ON_TARGET = 6;
    private ArrayList<Point> targetPositions;
    private String level;

    private int playerX;
    private int playerY;

    private int col, row;

    GameManager gameManager;

    public Sokoban(int col, int row, int size) {
        super(col, row, size);
        if (level == null)
            level = "map01";

        gameManager = new GameManager();
        initializeBoard(level);
    }

    public void update(GameStateAndDirection update) {

        findPlayerAndUpdatePos();
        super.update = update;
        notifyAllObservers();

        if (gameStatus == GameStateAndDirection.GAME_WON && gameStarted == GameStateAndDirection.GAME_START) {
            gameStatus = GameStateAndDirection.GAME_START;
            initializeBoard(level);

        } else if (gameStatus == GameStateAndDirection.GAME_OVER && gameStarted == GameStateAndDirection.GAME_START) {
            gameStatus = GameStateAndDirection.GAME_START;
            initializeBoard(level);
        }
    }

    private void initializeBoard(String level) {

        // finds the target positions and adds them to the targetPositions arraylist
        // gameManager.scanLevel(board, targetPositions);
        targetPositions = new ArrayList<Point>();
        int[][] board = gameManager.fileLevelScan("resources/" + level + ".txt", targetPositions,
                super.getBoard().length, super.getBoard()[0].length);
        this.board = board;
        findPlayerAndUpdatePos();
        gameStarted = GameStateAndDirection.GAME_START;
        update(GameStateAndDirection.GAME_START);

    }

    @Override
    public void input(GameStateAndDirection currentInput) {

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
            default:
                break;

        }

    }

    public void findPlayerAndUpdatePos() {

        int playerX = -1;
        int playerY = -1;

        // Find the current position of the player
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col] == PLAYER || board[row][col] == PLAYER_ON_TARGET) {
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
            int playerValue = board[playerY][playerX];
            int destTile = board[destY][destX];

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
            int playerValue = board[playerY][playerX];
            int destTile = board[destY][destX];

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
            int playerValue = board[playerY][playerX];
            int destTile = board[destY][destX];

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
            int playerValue = board[playerY][playerX];
            int destTile = board[destY][destX];

            if (destTile == BOX || destTile == BOX_ON_TARGET) {
                // Push the box if there is one
                int boxX = destX;
                int boxY = destY;
                int boxDestX = destX;
                int boxDestY = destY + 1;

                moveBox(boxX, boxY, boxDestX, boxDestY);
            }

            playerStepOverTargetRepaint(destX, destY, playerValue, destTile);

            // Update the board state and repaint
            update(GameStateAndDirection.DOWN);

        }
    }

    private void playerStepOverTargetRepaint(int destX, int destY, int playerValue, int destTile) {

        checkGameOver();

        switch (destTile) {
            case TARGET:
                board[destY][destX] = PLAYER_ON_TARGET;
                board[playerY][playerX] = EMPTY;
                break;
            case EMPTY:
                board[destY][destX] = PLAYER;
                if (playerValue == PLAYER_ON_TARGET) {
                    board[playerY][playerX] = TARGET;
                } else {
                    board[playerY][playerX] = EMPTY;
                }
                break;
            case BOX_ON_TARGET:
                board[destY][destX] = PLAYER_ON_TARGET;
                board[playerY][playerX] = EMPTY;
                break;
            case BOX:
                board[destY][destX] = PLAYER;
                if (playerValue == PLAYER_ON_TARGET) {
                    board[playerY][playerX] = TARGET;
                } else {
                    board[playerY][playerX] = EMPTY;
                }
                break;
            default:
                break;
        }

    }

    private boolean isValidMove(int startX, int startY, int destX, int destY) {

        // Check if the destination coordinates are within the board bounds
        if (destX < 0 || destX >= board.length || destY < 0 || destY >= board[0].length) {
            return false;
        }

        // Check if the destination tile is a wall
        if (board[destY][destX] == WALL) {
            return false;
        }

        // Check if the destination tile is a box or box on a target
        if (board[destY][destX] == BOX || board[destY][destX] == BOX_ON_TARGET) {
            // Check if the box can be pushed
            int boxDestX = destX + (destX - startX);
            int boxDestY = destY + (destY - startY);

            // Check if the box destination is a wall
            if (board[boxDestY][boxDestX] == WALL) {
                return false;
            }

            // Check if the box destination is another box
            if (board[boxDestY][boxDestX] == BOX || board[boxDestY][boxDestX] == BOX_ON_TARGET) {
                return false;
            }

        }
        return true;
    }

    public void moveBox(int boxX, int boxY, int destX, int destY) {

        // Check if the destination is a valid position for the box
        if (isValidBoxMove(boxX, boxY, destX, destY)) {

            if (board[destY][destX] == TARGET) {
                board[destY][destX] = BOX_ON_TARGET;
            } else {
                board[destY][destX] = BOX;
            }
        }
    }

    private boolean isValidBoxMove(int boxX, int boxY, int destX, int destY) {

        findPlayerAndUpdatePos();

        // Check if the destination coordinates are within the board bounds
        int distanceX = Math.abs(boxX - playerX);
        int distanceY = Math.abs(boxY - playerY);

        // Check if the destination coordinates are within the board bounds
        if ((distanceX == 1 && distanceY == 0) || (distanceX == 0 && distanceY == 1)) {
            // Check if the destination is empty or a target tile
            int destTile = board[destY][destX];
            return destTile == EMPTY || destTile == TARGET;
        }

        return false;
    }

    private boolean isBoxStuck(int col, int row) {
        // Check if the box is stuck in a corner
        boolean up = (row > 0
                && (board[row - 1][col] == WALL));
        boolean down = (row < board.length - 1
                && (board[row + 1][col] == WALL));
        boolean left = (col > 0
                && (board[row][col - 1] == WALL));
        boolean right = (col < board[row].length - 1
                && (board[row][col + 1] == WALL));
        // Check if the box is on a target, target can be in a corner
        for (Point target : targetPositions) {
            int targetX = target.x;
            int targetY = target.y;/*  */

            if (targetX == col && targetY == row) {
                board[row][col] = BOX_ON_TARGET;
                return false;
            }
        }

        if (up && left || up && right || down && left || down && right) {
            return true;
        }

        return false;

    }

    public boolean checkGameOver() {

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == BOX) {
                    // Check if the box is stuck (surrounded by walls or other boxes)
                    if (isBoxStuck(col, row)) {
                        gameStatus = GameStateAndDirection.GAME_OVER;
                        update = GameStateAndDirection.GAME_OVER;
                        notifyAllObservers();
                        return true;
                    }
                }
            }
        }
        checkWin();
        return false;
    }

    public boolean checkWin() {

        for (Point target : targetPositions) {
            int targetX = target.x;
            int targetY = target.y;

            if (board[targetY][targetX] != BOX_ON_TARGET) {
                return false;
            }
        }

        switch (level) {
            case "map01":
                level = "map02";
                break;
            case "map02":
                level = "map03";
                break;
            default:
                break;
        }
        gameStatus = GameStateAndDirection.GAME_WON;
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