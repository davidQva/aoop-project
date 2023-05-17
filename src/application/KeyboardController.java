package application;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import framework.Controller;
import framework.GameStateAndDirection;
import framework.InputController;

/**
 * KeyboardController implements the interface InputController and KeyListener.
 * KeyboardController is responsible for getting input from the user and passing
 * it to the Controller.
 */
public class KeyboardController implements InputController, KeyListener {

    GameStateAndDirection input;
    private Controller controller;

    /**
     * Constructor for the KeyboardController.
     * 
     * @param model is the controller for the game.
     */
    public KeyboardController(Controller model) {
        this.controller = model;
    }

    /**
     * Returns the input from the user.
     * 
     * @return direction
     */
    @Override
    public GameStateAndDirection input() {
        return this.input;
    }

    /**
     * when a key is pressed the input is set to the corresponding direction.
     * The input is then passed to the controller.
     * 
     * @param e is the event from the user.
     */
    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_W) {
            this.input = GameStateAndDirection.UP;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            this.input = GameStateAndDirection.DOWN;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            this.input = GameStateAndDirection.LEFT;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            this.input = GameStateAndDirection.RIGHT;
        }

        if (e.getKeyCode() == KeyEvent.VK_P) {
            this.input = GameStateAndDirection.GAME_PAUSE;
        }
        if (e.getKeyCode() == KeyEvent.VK_M) {
            this.input = GameStateAndDirection.MUTE;}

         if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S
                || e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_D
                || e.getKeyCode() == KeyEvent.VK_R || e.getKeyCode() == KeyEvent.VK_P || e.getKeyCode() == KeyEvent.VK_M)
            controller.actionPerformed(null);
    }

    /**
     * not used
     */
    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

}
