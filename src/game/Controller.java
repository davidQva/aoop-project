package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener {

	private final Game game;
	
	public Controller(Game gam) 
	{
		game = gam;
		game.getGameFrame().addKeyListener(this);
	}
	

	@Override
	public void keyPressed(KeyEvent e) 
	{
		switch(e.getKeyCode()) 
		{
		case KeyEvent.VK_UP:
			game.PressedUp();
			break;
		case KeyEvent.VK_DOWN:
			game.PressedDown();
			break;
		case KeyEvent.VK_LEFT:
			game.PressedLeft();
			break;
		case KeyEvent.VK_RIGHT:
			game.PressedRight();
			break;
		case KeyEvent.VK_P:
			game.PressedPause();
			break;
		case KeyEvent.VK_R:
			game.PressedReset();
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{
		
	}
}
