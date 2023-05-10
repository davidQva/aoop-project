package application;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import framework.Controller;
import framework.GameStateAndDiraction;
import framework.InputController;

public class SokobanController implements InputController, KeyListener {

    GameStateAndDiraction direction;
    private Controller controller;

    public SokobanController(Controller model) {
        this.controller = model;
    }

    @Override
    public GameStateAndDiraction move() {
        return this.direction;
    }

    @Override
    public void keyTyped(KeyEvent e) {       
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_W) {
            this.direction = GameStateAndDiraction.UP;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            this.direction = GameStateAndDiraction.DOWN;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            this.direction = GameStateAndDiraction.LEFT;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            this.direction = GameStateAndDiraction.RIGHT;
        }        
        controller.actionPerformed(null);
    }        

    @Override
    public void keyReleased(KeyEvent e) {        
    }
    
}
