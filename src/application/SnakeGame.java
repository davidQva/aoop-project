package application;

import framework.AbstractTileModel;
import framework.StateAndDirection;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import javax.swing.*;

public class SnakeGame extends AbstractTileModel implements ActionListener {

    private static final int DELAY = 200;
    private LinkedList<Point> snake;
    private int appleX;
    private int appleY;
    private Timer timer;
    StateAndDirection lastDir;   
    int row;
    int col;

    public SnakeGame(int col, int row, int size) {

        super(col, row, size);
        this.row = row;
        this.col = col;
        snake = new LinkedList<Point>();
        snake.add(new Point(row / 2, col / 2));
        setUpdate(StateAndDirection.RIGHT);
        appleX = (int) (Math.random() * this.row);
        appleY = (int) (Math.random() * this.col);

        timer = new Timer(DELAY, this);
        timer.start();

    }

    /**
     * input method for the game controller to call when a key is pressed
     * sets the newInput to the direction of the key pressed by the user
     */
    @Override
    public void input(StateAndDirection newInput) {

        if(newInput != StateAndDirection.GAME_PAUSE)
        lastDir = newInput;

        if (newInput == StateAndDirection.UP && super.getUpdate() != StateAndDirection.DOWN)
            super.setUpdate(StateAndDirection.UP);
        if (newInput == StateAndDirection.DOWN && super.getUpdate() != StateAndDirection.UP)
            super.setUpdate(StateAndDirection.DOWN);
        if (newInput == StateAndDirection.LEFT && super.getUpdate() != StateAndDirection.RIGHT)
            super.setUpdate(StateAndDirection.LEFT);
        if (newInput == StateAndDirection.RIGHT && super.getUpdate() != StateAndDirection.LEFT)
            super.setUpdate(StateAndDirection.RIGHT);


        if (newInput == StateAndDirection.GAME_PAUSE) {
            if (timer.isRunning()) {
                timer.stop();
                setUpdate(StateAndDirection.GAME_PAUSE);
                notifyAllObservers();
                if(getUpdate() != StateAndDirection.GAME_LOAD){
                setUpdate(lastDir);
                timer.start();
                }            
            }
        }

        if (getUpdate() == StateAndDirection.GAME_LOAD) {
            
            snake.clear();

            for (int i = 0; i < getBoard().length; i++) {
                for (int j = 0; j < getBoard()[0].length; j++) {
                   
                    if(getBoard()[i][j] == 3)
                     snake.addFirst(new Point(j,i));

                    if(getBoard()[i][j] == 1)
                     snake.addLast(new Point(j,i));

                    if(getBoard()[i][j] == 2){
                        appleX = j;
                        appleY = i;
                    }                  
                }
            }         
            setUpdate(lastDir);
            timer.start();            
        }
    }

    /**
     * updates the game state and direction of the game model and notifies the
     * observers
     * 
     * @param e is the action event that triggers the update, in this case the timer
     *          reads the direction from the update variable and moves the snake
     *          accordingly
     */
    public void update() {

        Point head = snake.getFirst();

        int snakeX = head.x;
        int snakeY = head.y;

        if (getUpdate() == StateAndDirection.LEFT) {
            snakeX--;
        } else if (getUpdate() == StateAndDirection.RIGHT) {
            snakeX++;
        } else if (getUpdate() == StateAndDirection.UP) {
            snakeY--;
        } else if (getUpdate() == StateAndDirection.DOWN) {
            snakeY++;
        }

        for (Point body : snake) {
            if (body.x == snakeX && body.y == snakeY) {
                gameOver();
                return;
            }
        }

        if (snakeX < 0 || snakeX >= row || snakeY < 0 || snakeY >= col) {
            gameOver();
            return;
        }

        if (snakeX == appleX && snakeY == appleY) {
            snake.addFirst(new Point(snakeX, snakeY));
            appleX = (int) (Math.random() * row);
            appleY = (int) (Math.random() * col);
        } else {
            snake.removeLast();
            snake.addFirst(new Point(snakeX, snakeY));
        }

        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                super.setValue(i, j, 0);
            }
        }

        for (Point body : snake) {

            if (body == snake.getFirst()) {
                super.setValue(body.y, body.x, 3);
                continue;
            }
            super.setValue(body.y, body.x, 1);
        }
        super.setValue(appleY, appleX, 2);

      notifyAllObservers();
    }

    /**
     * resets the game state and direction of the game model and notifies the
     * observers
     */
    public void gameOver() {
        timer.stop();
        setGameStatus(StateAndDirection.GAME_OVER);
        notifyAllObservers();
        resetGame();
    }

    /**
     * resets the game state and direction of the game
     */
    public void resetGame() {
        snake = new LinkedList<Point>();
        snake.add(new Point(row / 2, col / 2));
        setGameStatus(StateAndDirection.GAME_START);
        setUpdate(StateAndDirection.RIGHT);
        appleX = (int) (Math.random() * row);
        appleY = (int) (Math.random() * col);
        // super.resetBoard();
        timer.start();
    }

    /**
     * trigger by the timer, calls the update method
     * 
     * @param e is the action event that triggers the update, in this case the timer
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        update();
    }

}
