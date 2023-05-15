package application;


import framework.AbstractTileModel;
import framework.GameStateAndDirection;

import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

import javax.swing.*;

public class Snake extends AbstractTileModel implements ActionListener{

    private final int BOARD_SIZE;
    private static final int DELAY = 250;
    private LinkedList<Point> snake;
    //private int[][] board;
    private int snakeLength;    
    private int snakeX;
    private int snakeY;
    private int appleX;
    private int appleY;
    private Timer timer;
    final int x[]; 
	final int y[];
    int rows;

    public Snake(int col, int row, int size) {
        
        super(col, row, size);
        this.rows = row;
        board = new int[col][row];
        BOARD_SIZE = col;
        snake = new LinkedList<Point>();
        snake.add(new Point(row/2, size/2));
        x = new int[BOARD_SIZE];
        y = new int[BOARD_SIZE];
        update = GameStateAndDirection.RIGHT;
        snakeX = col / 2;
        snakeY = row / 2;
        appleX = (int) (Math.random() * BOARD_SIZE);
        appleY = (int) (Math.random() * BOARD_SIZE);
        timer = new Timer(DELAY, this);
        timer.start();

    }
   /*  
    @Override
    public void actionPerformed(ActionEvent e) 
    {	
        update();
       // input(gameStarted);
    }

 */ 
   @Override
    public void input(GameStateAndDirection direction) {

        if(direction == GameStateAndDirection.UP && this.update != GameStateAndDirection.DOWN)
            this.update = GameStateAndDirection.UP;
        if(direction == GameStateAndDirection.DOWN && this.update != GameStateAndDirection.UP)
            this.update = GameStateAndDirection.DOWN;
        if(direction == GameStateAndDirection.LEFT && this.update != GameStateAndDirection.RIGHT)
            this.update = GameStateAndDirection.LEFT;
        if(direction == GameStateAndDirection.RIGHT && this.update != GameStateAndDirection.LEFT)
            this.update = GameStateAndDirection.RIGHT;     
       
    }

    public void update() {

        Point head = snake.getFirst();

        int snakeX = head.x;
        int snakeY = head.y;

        if (update == GameStateAndDirection.LEFT) {
            snakeX--;
        } else if (update == GameStateAndDirection.RIGHT) {
            snakeX++;
        } else if (update == GameStateAndDirection.UP) {
            snakeY--;
        } else if (update == GameStateAndDirection.DOWN) {
            snakeY++;
        }

        for (Point body : snake) {
            if (body.x == snakeX && body.y == snakeY) {
                gameOver();                            
                return;
            }
        }

        if (snakeX < 0 || snakeX >= BOARD_SIZE || snakeY < 0 || snakeY >= BOARD_SIZE) {            
            gameOver();                
            return;
        }

        if (snakeX == appleX && snakeY == appleY) {
             snake.addFirst(new Point(snakeX, snakeY));
            appleX = (int) (Math.random() * BOARD_SIZE);
            appleY = (int) (Math.random() * BOARD_SIZE);
        } else {
            snake.removeLast();
            snake.addFirst(new Point(snakeX, snakeY));
        }

 
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = 0;
            }
        }

        for (Point body : snake) {
            board[body.y][body.x] = 1;
        }

        board[appleY][appleX] = 2;

        notifyAllObservers();
    }

    public void gameOver() {
        timer.stop();
        update = GameStateAndDirection.GAME_OVER;
        notifyAllObservers();
        resetGame();
    }

    public void resetGame() {
        snake = new LinkedList<Point>();
        snake.add(new Point(rows/2, rows/2));
        update = GameStateAndDirection.RIGHT;
        snakeX = BOARD_SIZE / 2;
        snakeY = BOARD_SIZE / 2;
        appleX = (int) (Math.random() * BOARD_SIZE);
        appleY = (int) (Math.random() * BOARD_SIZE);
        board = new int[BOARD_SIZE][BOARD_SIZE];
        timer.start();
    }

    @Override
    public void moveUp() {   
           // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'moveDown'");   
    }

    @Override
    public void moveDown() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'moveDown'");
    }

    @Override
    public void moveLeft() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'moveLeft'");
    }

    @Override
    public void moveRight() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'moveRight'");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        update();
    }  

}
