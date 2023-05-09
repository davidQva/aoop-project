package application;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import framework.Controller;
import framework.Direction;
import framework.InputController;

public class SokobanController implements InputController, KeyListener {

    Direction direction;
    private Controller controller;

    public SokobanController(Controller model) {
        this.controller = model;
    }

    @Override
    public Direction move() {
        return this.direction;
    }

    @Override
    public void keyTyped(KeyEvent e) {       
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_W) {
            this.direction = Direction.UP;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            this.direction = Direction.DOWN;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            this.direction = Direction.LEFT;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            this.direction = Direction.RIGHT;
        }        
        controller.actionPerformed(null);
    }        

    @Override
    public void keyReleased(KeyEvent e) {        
    }
    
}
