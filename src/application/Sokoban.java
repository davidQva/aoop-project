package application;

import framework.AbstractTileModel;
import framework.Direction;

public class Sokoban extends AbstractTileModel {

    private static final int EMPTY = 0;
    private static final int WALL = 1;
    private static final int TARGET = 2;
    private static final int BOX = 3;
    private static final int BOX_ON_TARGET = 4;
    private static final int PLAYER = 5;

    private int playerX;
    private int playerY;

    GameManager gameManager;

    public Sokoban(int col, int row, int size) {
        super(col, row, size);
        gameManager = new GameManager();
        initializeBoard();
    }

    private void initializeBoard() {

        int[][] board = getBoard();

        // Set up your initial board configuration here
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                // Set the appropriate tile based on the row and column
                if (row == 0 || row == board.length - 1 || col == 0 || col == board[0].length - 1) {
                    // Set as WALL
                    board[row][col] = WALL;
                } else {
                    // Set as EMPTY or TARGET based on the position
                    if ((row + col) % 2 == 0) {
                        board[row][col] = TARGET;
                    } else {
                        board[row][col] = EMPTY;
                    }
                }
            }
        }

        board[3][3] = PLAYER;
        board[2][3] = BOX;

        // Update the board state
        setBoard(board);
        // view.paintBoard();

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

    /* public void moveUp() {
        
        int[][] board = getBoard();
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
    
        // Check if the player can move up
        if (playerY > 0 && board[playerY - 1][playerX] != WALL) {
            int destinationTile = board[playerY - 1][playerX];
            // If the destination tile is empty or a target tile
            if (destinationTile == EMPTY || destinationTile == TARGET) {
                // Move the player
                board[playerY][playerX] = (destinationTile == TARGET) ? TARGET : EMPTY;
                board[playerY - 1][playerX] = PLAYER;
                // Update the player's position
                playerY--;
    
                // Check if the box can be pushed
                if (destinationTile == TARGET && playerY > 0 && (board[playerY - 1][playerX] == BOX || board[playerY - 1][playerX] == BOX_ON_TARGET)) {
                    int boxDestinationTile = board[playerY - 2][playerX];
                    // If the box destination tile is empty or a target tile
                    if (boxDestinationTile == EMPTY || boxDestinationTile == TARGET) {
                        // Move the box
                        board[playerY - 1][playerX] = (boxDestinationTile == TARGET) ? BOX_ON_TARGET : BOX;
                        board[playerY - 2][playerX] = (boxDestinationTile == TARGET) ? TARGET : EMPTY;
                    }
                }
            }
        }
    
        // Update the board state
        setBoard(board);
        view.paintBoard();
    } */

    public void moveUp() {
        int[][] board = getBoard();
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
    
        // Check if the player can move up
        if (playerY > 0 && board[playerY - 1][playerX] != WALL) {
            // Move the player up
            board[playerY][playerX] = EMPTY;
            board[playerY - 1][playerX] = PLAYER;
    
            // Update the player's position
            this.playerX = playerX;
            this.playerY = playerY - 1;
        }
    
        // Update the board state
        setBoard(board);
        view.paintBoard();
    }
    

    public void moveRight() {
    }

    public void moveLeft() {
    }

    public void moveDown() {
        int[][] board = getBoard();
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
    
        // Calculate the destination coordinates
        int destX = playerX;
        int destY = playerY+1;
    
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
    
            board[playerY][playerX] = destTile;
            board[destY][destX] = playerValue;
    
            // Update the board state and repaint
            setBoard(board);
            view.paintBoard();
            
            // Check game over condition
            checkGameOver();
        }
    }

    private boolean isValidMove(int startX, int startY, int destX, int destY) {
        int[][] board = getBoard();
        int boardWidth = board[0].length;
        int boardHeight = board.length;
    
        // Check if the destination coordinates are within the board bounds
        if (destX < 0 || destX >= boardWidth || destY < 0 || destY >= boardHeight) {
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
        int[][] board = getBoard();
    
        // Check if the destination is a valid position for the box
        if (isValidBoxMove(boxX, boxY, destX, destY)) {
            // Move the box to the destination coordinates
            int boxValue = board[boxY][boxX];
            board[boxY][boxX] = EMPTY;
            board[destY][destX] = boxValue;
    
            // Update the board state and repaint
            setBoard(board);
            view.paintBoard();
        }
    }
    
    private boolean isValidBoxMove(int boxX, int boxY, int destX, int destY) {
        int[][] board = getBoard();
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
    


    

    public boolean checkGameOver() {

        int[][] board = getBoard();
        boolean isGameOver = true;

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col] == BOX) {
                    // Check if the box is not on a target tile
                    if (board[row][col] != TARGET) {
                        isGameOver = false;
                        break;
                    }
                    // Check if there is a neighboring empty tile or target tile
                    if (row > 0 && (board[row - 1][col] == EMPTY || board[row - 1][col] == TARGET)) {
                        isGameOver = false;
                        break;
                    }
                    if (row < board.length - 1 && (board[row + 1][col] == EMPTY || board[row + 1][col] == TARGET)) {
                        isGameOver = false;
                        break;
                    }
                    if (col > 0 && (board[row][col - 1] == EMPTY || board[row][col - 1] == TARGET)) {
                        isGameOver = false;
                        break;
                    }
                    if (col < board[0].length - 1 && (board[row][col + 1] == EMPTY || board[row][col + 1] == TARGET)) {
                        isGameOver = false;
                        break;
                    }
                }
            }
            if (!isGameOver) {
                break;
            }
        }

        if (isGameOver) {
            System.out.println("Game Over! All boxes are either in the correct places or stuck.");
            // Trigger any necessary game over actions
            // For example, display a game over screen or reset the level
        }

        return isGameOver;
    }

}
