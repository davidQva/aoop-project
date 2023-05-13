package application;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import framework.Controller;
import framework.GameStateAndDirection;
import framework.InputController;

public class SnakeController implements InputController, KeyListener{

	GameStateAndDirection direction;
    private Controller controller;
	
	public SnakeController(Controller model) {
		this.controller = model;
	}
	
	@Override
    public GameStateAndDirection move() {
        return this.direction;
    }	

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_W) {
        	if(this.direction != GameStateAndDirection.DOWN) {
            this.direction = GameStateAndDirection.UP;
        	}
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
        	
        	if(this.direction != GameStateAndDirection.UP) {
                this.direction = GameStateAndDirection.DOWN;
            	}
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
        	if(this.direction != GameStateAndDirection.RIGHT) {
                this.direction = GameStateAndDirection.LEFT;
            	}
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
        	if(this.direction != GameStateAndDirection.LEFT) {
                this.direction = GameStateAndDirection.RIGHT;
            	}
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
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
