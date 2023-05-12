package application;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import framework.Controller;
import framework.GameStateAndDirection;
import framework.InputController;

public class SokobanController implements InputController, KeyListener {

    GameStateAndDirection direction;
    private Controller controller;

    public SokobanController(Controller model) {
        this.controller = model;
    }

    @Override
    public GameStateAndDirection move() {
        return this.direction;
    }

    @Override
    public void keyTyped(KeyEvent e) {       
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_W) {
            this.direction = GameStateAndDirection.UP;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            this.direction = GameStateAndDirection.DOWN;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            this.direction = GameStateAndDirection.LEFT;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            this.direction = GameStateAndDirection.RIGHT;
        } 
        if (e.getKeyCode() == KeyEvent.VK_R) {
            this.direction = GameStateAndDirection.GAME_RESTART;
        }
        if (e.getKeyCode() == KeyEvent.VK_P) {
            this.direction = GameStateAndDirection.GAME_PAUSE;
        }
        
        if(e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S 
        || e.getKeyCode() == KeyEvent.VK_A ||e.getKeyCode() == KeyEvent.VK_D
        || e.getKeyCode() == KeyEvent.VK_R || e.getKeyCode() == KeyEvent.VK_P)
        controller.actionPerformed(null);
    }        

    @Override
    public void keyReleased(KeyEvent e) {        
    }
    
}
