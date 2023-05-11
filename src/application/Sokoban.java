package application;

import java.awt.Point;
import java.util.ArrayList;

import framework.AbstractTileModel;
import framework.GameStateAndDiraction;

public class Sokoban extends AbstractTileModel {

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

    GameManager gameManager;

    public Sokoban(int col, int row, int size) {
        super(col, row, size);
        gameManager = new GameManager();
        targetPositions = new ArrayList<Point>();
        initializeBoard();
    }

    private void initializeBoard() {

       
          int[][] board = {
          { 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4 },
          { 4, 3, 2, 2, 2, 2, 4, 3, 2, 2, 2, 3, 4 },
          { 4, 2, 2, 2, 2, 2, 4, 2, 2, 2, 2, 2, 4 },
          { 4, 2, 2, 2, 2, 2, 4, 2, 2, 2, 2, 2, 4 },
          { 4, 2, 2, 2, 2, 2, 5, 2, 2, 2, 2, 2, 4 },
          { 4, 2, 2, 2, 2, 0, 2, 0, 2, 2, 2, 2, 4 },
          { 4, 4, 4, 4, 2, 0, 2, 0, 2, 4, 4, 4, 4 },
          { 4, 2, 2, 2, 2, 0, 2, 0, 2, 2, 2, 2, 4 },
          { 4, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4 },
          { 4, 2, 2, 2, 2, 2, 4, 2, 2, 2, 2, 2, 4 },
          { 4, 2, 2, 2, 2, 2, 4, 2, 2, 2, 2, 2, 4 },
          { 4, 3, 2, 2, 2, 3, 4, 2, 2, 2, 2, 3, 4 },
          { 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4 } };
         

      /*   int[][] board = {
                { 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4 },
                { 4, 3, 2, 2, 2, 2, 2, 3, 2, 2, 2, 3, 4 },
                { 4, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4 },
                { 4, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4 },
                { 4, 2, 2, 2, 2, 2, 5, 2, 2, 2, 2, 2, 4 },
                { 4, 2, 2, 2, 2, 0, 2, 0, 2, 2, 2, 2, 4 },
                { 4, 2, 2, 2, 2, 0, 2, 0, 2, 2, 2, 2, 4 },
                { 4, 2, 2, 2, 2, 0, 2, 0, 2, 2, 2, 2, 4 },
                { 4, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4 },
                { 4, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4 },
                { 4, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4 },
                { 4, 3, 2, 2, 2, 3, 2, 2, 2, 2, 2, 3, 4 },
                { 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4 } }; */

        gameManager.scanLevel(board, targetPositions);

        setBoard(board, GameStateAndDiraction.GAME_START);

    }

    @Override
    public void move(GameStateAndDiraction direction) {
        switch (direction) {
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

        findPlayerAndUpdatePos();

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

        setBoard(board, GameStateAndDiraction.UP);

    }

    public void moveRight() {

        findPlayerAndUpdatePos();

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

        setBoard(board, GameStateAndDiraction.RIGHT);

    }

    public void moveLeft() {

        findPlayerAndUpdatePos();

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

        setBoard(board, GameStateAndDiraction.LEFT);

    }

    public void moveDown() {

        findPlayerAndUpdatePos();

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
            setBoard(board, GameStateAndDiraction.DOWN);

        }
    }

    private void playerStepOverTargetRepaint(int destX, int destY, int playerValue, int destTile) {

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
                board[playerY][playerX] = EMPTY;
                break;
            default:
                break;
        }     
        
    }

    private boolean isValidMove(int startX, int startY, int destX, int destY) {

        checkGameOver();

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
                && (board[row - 1][col] == WALL || board[row - 1][col] == BOX || board[row - 1][col] == BOX_ON_TARGET));
        boolean down = (row < board.length - 1
                && (board[row + 1][col] == WALL || board[row + 1][col] == BOX || board[row + 1][col] == BOX_ON_TARGET));
        boolean left = (col > 0
                && (board[row][col - 1] == WALL || board[row][col - 1] == BOX || board[row][col - 1] == BOX_ON_TARGET));
        boolean right = (col < board[row].length - 1
                && (board[row][col + 1] == WALL || board[row][col + 1] == BOX || board[row][col + 1] == BOX_ON_TARGET));
        // Check if the box is on a target, target can be in a corner
        for (Point target : targetPositions) {
            int targetX = target.x;
            int targetY = target.y;

            if (targetX == col && targetY == row) {
                board[row][col] = BOX_ON_TARGET;
                checkWin();
                return false;
            }
        }

        if (up && left || up && right || down && left || down && right) {
            return true;
        }

        return false;

    }

    public void resetLevel() {

        // Reset the board state
        setBoard(board, GameStateAndDiraction.GAME_RESTART);
    }

    public boolean checkGameOver() {

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == BOX) {
                    // Check if the box is stuck (surrounded by walls or other boxes)
                    if (isBoxStuck(col, row)) {
                        System.out.println("Game Over! Box is stuck.");
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

            if (board[targetY][targetX] != BOX_ON_TARGET) {
                return false;
            }
        }

        System.out.println("You win!");

        return true;
    }

}