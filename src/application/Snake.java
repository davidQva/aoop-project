package application;


import framework.AbstractTileModel;
import framework.GameStateAndDirection;

import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
import java.util.LinkedList;

import javax.swing.*;

public class Snake extends AbstractTileModel implements ActionListener, Serializable{

    private final int BOARD_SIZE;
    private static final int DELAY = 200;
    private LinkedList<Point> snake; 
    private int appleX;
    private int appleY;
    private Timer timer;
    final int x[]; 
	final int y[];
    int rows;

    public Snake(int col, int row, int size) {
        
        super(col, row, size);
        this.rows = row;       
        BOARD_SIZE = col;
        snake = new LinkedList<Point>();
        snake.add(new Point(row/2, col/2));
        x = new int[BOARD_SIZE];
        y = new int[BOARD_SIZE];
        super.setGameStatus(GameStateAndDirection.RIGHT);
        
        appleX = (int) (Math.random() * BOARD_SIZE);
        appleY = (int) (Math.random() * BOARD_SIZE);     

        timer = new Timer(DELAY,this);
        timer.start();

    }

   @Override
    public void input(GameStateAndDirection direction) {

        if(direction == GameStateAndDirection.UP && super.getUpdate() != GameStateAndDirection.DOWN)
            super.setUpdate(GameStateAndDirection.UP);
        if(direction == GameStateAndDirection.DOWN && super.getUpdate() != GameStateAndDirection.UP)
            super.setUpdate(GameStateAndDirection.DOWN);
        if(direction == GameStateAndDirection.LEFT && super.getUpdate() != GameStateAndDirection.RIGHT)
            super.setUpdate(GameStateAndDirection.LEFT);
        if(direction == GameStateAndDirection.RIGHT && super.getUpdate() != GameStateAndDirection.LEFT)
            super.setUpdate(GameStateAndDirection.RIGHT);
        if(direction == GameStateAndDirection.GAME_PAUSE) {
            if(timer.isRunning()){
                timer.stop();
                super.setGameStatus(direction);
                notifyAllObservers();
            } else
                timer.start();
           // direction = GameStateAndDirection.GAME_PAUSE;
        }        
            
    }

    public void update(){

        Point head = snake.getFirst();

        int snakeX = head.x;
        int snakeY = head.y;

        if (getUpdate() == GameStateAndDirection.LEFT) {
            snakeX--;
        } else if (getUpdate() == GameStateAndDirection.RIGHT) {
            snakeX++;
        } else if (getUpdate() == GameStateAndDirection.UP) {
            snakeY--;
        } else if (getUpdate() == GameStateAndDirection.DOWN) {
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
                super.setValue(i,j,0);
            }
        }

        for (Point body : snake) {

            if (body == snake.getFirst()) {
                super.setValue(body.y,body.x,3);
                continue;
            }
            super.setValue(body.y,body.x, 1);
        }
        super.setValue(appleY, appleX, 2);        
      
        notifyAllObservers();
    }

    public void gameOver() {
        timer.stop();
        super.setUpdate(GameStateAndDirection.GAME_OVER);
        notifyAllObservers();
        resetGame();
    }

    public void resetGame() {
        snake = new LinkedList<Point>();
        snake.add(new Point(rows/2, rows/2));
        super.setUpdate(GameStateAndDirection.RIGHT); 
        appleX = (int) (Math.random() * BOARD_SIZE);
        appleY = (int) (Math.random() * BOARD_SIZE);
        super.resetBoard();       
        timer.start();
    }
   
    @Override
    public void actionPerformed(ActionEvent e) {
        update();
    }  

}
