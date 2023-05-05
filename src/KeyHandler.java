import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	
	
	/*
	 * Create an instance if the GamePanel and the boolean values which will be needed to check if the keys have been pressed or not.
	 * */
	GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed;
	/*
	 * adds the keyHandler to the gamepanel which gets input as an argument.
	 * */
	public KeyHandler(GamePanel gp) 
	{
		this.gp = gp;
	}
	
	/*
	 * create a local variable which saves the keyCode, 
	 * when the keyCode is equal to the key, then the boolean variable for that key will be set as true and updates the position of the charcter
	 * only one key may be pressed at a time.
	 * */
	@Override
	public void keyPressed(KeyEvent e) 
	{
		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_UP) 
		{
			if(downPressed == true || leftPressed == true || rightPressed == true)
				return;
			else 
			upPressed = true;
			gp.updatePos();
		}
		
		if (code == KeyEvent.VK_DOWN) 
		{
			if(upPressed == true || leftPressed == true || rightPressed == true)
				return;
			else 
			downPressed = true;
			gp.updatePos();
		}
		
		if (code == KeyEvent.VK_LEFT) 
		{
			if(downPressed == true || upPressed == true || rightPressed == true)
				return;
			else 
				leftPressed = true;
				gp.updatePos();
			
		}
		
		if (code == KeyEvent.VK_RIGHT) 
		{
			if(downPressed == true || upPressed == true || leftPressed == true)
				return;
			else 
			rightPressed = true;
			gp.updatePos();
		}
	}
	
	/*
	 * sets the value of the "direction"pressed variable to false when the key has been released.
	 * */
	@Override
	public void keyReleased(KeyEvent e) 
	{
		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_UP) 
		{
			upPressed = false;
		}
		
		if (code == KeyEvent.VK_DOWN) 
		{
			downPressed = false;
		}
		
		if (code == KeyEvent.VK_LEFT) 
		{
			leftPressed = false;
		}
		
		if (code == KeyEvent.VK_RIGHT) 
		{
			rightPressed = false;
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) // can be used to check which key that has been pressed
	{

	}
	

}
