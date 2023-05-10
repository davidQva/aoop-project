package application;

import java.awt.Point;
import java.util.ArrayList;

import framework.AbstractTileModel;
import framework.Direction;

public class Sokoban extends AbstractTileModel {

    private static final int BOX = 0;
    private static final int BOX_ON_TARGET = 1;
    private static final int EMPTY = 2;
    private static final int TARGET = 3;
    private static final int WALL = 4;
    private static final int PLAYER = 5;
    private ArrayList<Point> targetPositions;
    //private Point[] targetPositions;

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

      /*   int[][] board = {
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
                { 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4 } }; */

                int[][] board =       {
                    {4,4,4,4,4,4,4,4,4,4,4,4,4},
                    {4,3,2,2,2,2,2,3,2,2,2,3,4},
                    {4,2,2,2,2,2,2,2,2,2,2,2,4},
                    {4,2,2,2,2,2,2,2,2,2,2,2,4},
                    {4,2,2,2,2,2,5,2,2,2,2,2,4},
                    {4,2,2,2,2,0,2,0,2,2,2,2,4},
                    {4,2,2,2,2,0,2,0,2,2,2,2,4},
                    {4,2,2,2,2,0,2,0,2,2,2,2,4},
                    {4,2,2,2,2,2,2,2,2,2,2,2,4},
                    {4,2,2,2,2,2,2,2,2,2,2,2,4},
                    {4,2,2,2,2,2,2,2,2,2,2,2,4},
                    {4,3,2,2,2,3,2,2,2,2,2,3,4},
                    {4,4,4,4,4,4,4,4,4,4,4,4,4}};




        gameManager.scanLevel(board, targetPositions);
 
        setBoard(board);    

    }

    @Override
    public void move(Direction direction) {
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

    public void findPlayer() {

        int playerX = -1;
        int playerY = -1;

        // Find the current position of the player
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col] == PLAYER) {
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

        findPlayer();

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

            board[playerY][playerX] = EMPTY;
            board[destY][destX] = PLAYER;

        }

        // Update the board state

        setBoard(board);
        view.paintBoard();

    }

    public void moveRight() {

        findPlayer();

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

            board[playerY][playerX] = EMPTY;
            board[destY][destX] = PLAYER;

        }

        setBoard(board);
        view.paintBoard();

    }

    public void moveLeft() {

        findPlayer();

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

                moveBox(boxX, boxY, boxDestX, boxDestY);
            }

            board[playerY][playerX] = EMPTY;
            board[destY][destX] = PLAYER;

        }

        setBoard(board);
        view.paintBoard();

    }

    public void moveDown() {

        findPlayer();

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

            board[playerY][playerX] = EMPTY;
            board[destY][destX] = PLAYER;

            // Update the board state and repaint
            setBoard(board);
            view.paintBoard();

            // Check game over condition
            // checkGameOver();
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

            // Check if the box destination is valid
            if (!isValidMove(destX, destY, boxDestX, boxDestY)) {
                return false;
            }
        }       
        return true;
    }

    public void moveBox(int boxX, int boxY, int destX, int destY) {

        // Check if the destination is a valid position for the box
        if (isValidBoxMove(boxX, boxY, destX, destY)) {
            // Move the box to the destination coordinates
            // int boxValue = board[boxY][boxX];
            board[boxY][boxX] = PLAYER;
            board[destY][destX] = BOX;

            // Update the board state and repaint
            setBoard(board);
            // view.paintBoard();            
        }
    }

    private boolean isValidBoxMove(int boxX, int boxY, int destX, int destY) {

        findPlayer();

        // Calculate the distance between the player and the box
        int distanceX = Math.abs(boxX - playerX);
        int distanceY = Math.abs(boxY - playerY);

        // Check if the player can push the box
        if ((distanceX == 1 && distanceY == 0) || (distanceX == 0 && distanceY == 1)) {
            // Check if the destination is empty or a target tile
            int destTile = board[destY][destX];
            return destTile == EMPTY || destTile == TARGET;
        }

        return false;
    }

    private boolean isBoxStuck(int col, int row) {

        boolean up = (row > 0
                && (board[row - 1][col] == WALL || board[row - 1][col] == BOX || board[row - 1][col] == BOX_ON_TARGET));
        boolean down = (row < board.length - 1
                && (board[row + 1][col] == WALL || board[row + 1][col] == BOX || board[row + 1][col] == BOX_ON_TARGET));
        boolean left = (col > 0
                && (board[row][col - 1] == WALL || board[row][col - 1] == BOX || board[row][col - 1] == BOX_ON_TARGET));
        boolean right = (col < board[row].length - 1
                && (board[row][col + 1] == WALL || board[row][col + 1] == BOX || board[row][col + 1] == BOX_ON_TARGET));

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
        setBoard(board);
        view.paintBoard();
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