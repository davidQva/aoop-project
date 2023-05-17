package application;

import framework.AbstractTileModel;
import framework.GameStateAndDirection;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import javax.swing.*;

public class SnakeModel extends AbstractTileModel implements ActionListener {

    private static final int DELAY = 200;
    private LinkedList<Point> snake;
    private int appleX;
    private int appleY;
    private Timer timer;
    final int x[];
    final int y[];
    int row;
    int col;

    public SnakeModel(int col, int row, int size) {

        super(col, row, size);
        this.row = row;
        this.col = col;      
        snake = new LinkedList<Point>();
        snake.add(new Point(5 / 2, this.row / 2));
        x = new int[this.col];
        y = new int[this.row];
        setUpdate(GameStateAndDirection.RIGHT);

        appleX = (int) (Math.random() * this.col);
        appleY = (int) (Math.random() * this.row);

        timer = new Timer(DELAY, this);
        timer.start();

    }

    @Override
    public void input(GameStateAndDirection newInput) {

        if (newInput == GameStateAndDirection.UP && super.getUpdate() != GameStateAndDirection.DOWN)
            super.setUpdate(GameStateAndDirection.UP);
        if (newInput == GameStateAndDirection.DOWN && super.getUpdate() != GameStateAndDirection.UP)
            super.setUpdate(GameStateAndDirection.DOWN);
        if (newInput == GameStateAndDirection.LEFT && super.getUpdate() != GameStateAndDirection.RIGHT)
            super.setUpdate(GameStateAndDirection.LEFT);
        if (newInput == GameStateAndDirection.RIGHT && super.getUpdate() != GameStateAndDirection.LEFT)
            super.setUpdate(GameStateAndDirection.RIGHT);
        if (newInput == GameStateAndDirection.GAME_PAUSE) {
            if (timer.isRunning()) {
                timer.stop();
                super.setGameStatus(newInput);
                notifyAllObservers();
            } else
                timer.start();
            // direction = GameStateAndDirection.GAME_PAUSE;
        }

    }

    public void update() {

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

        if (snakeX < 0 || snakeX >= col || snakeY < 0 || snakeY >= row) {
            gameOver();
            return;
        }

        if (snakeX == appleX && snakeY == appleY) {
            snake.addFirst(new Point(snakeX, snakeY));
            appleX = (int) (Math.random() * col);
            appleY = (int) (Math.random() * row);
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

    public void gameOver() {
        timer.stop();
        setGameStatus(GameStateAndDirection.GAME_OVER);
        notifyAllObservers();
        resetGame();
    }

    public void resetGame() {
        snake = new LinkedList<Point>();
        snake.add(new Point(col / 2, row / 2));
        setGameStatus(GameStateAndDirection.GAME_START);
        setUpdate(GameStateAndDirection.RIGHT);
        appleX = (int) (Math.random() * col);
        appleY = (int) (Math.random() * row);
        // super.resetBoard();
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update();
    }

}
