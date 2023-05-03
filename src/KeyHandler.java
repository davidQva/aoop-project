import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	
	public boolean upPressed, downPressed, leftPressed, rightPressed;
	GamePanel gp;

	public KeyHandler(GamePanel gp) 
	{
		this.gp = gp;
	}
	
	@Override
	public void keyPressed(KeyEvent e) 
	{
		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_W) 
		{
			upPressed = true;
			gp.updatePos();
		}
		
		if (code == KeyEvent.VK_S) 
		{
			downPressed = true;
			gp.updatePos();
		}
		
		if (code == KeyEvent.VK_A) 
		{
			leftPressed = true;
			gp.updatePos();
		}
		
		if (code == KeyEvent.VK_D) 
		{
			rightPressed = true;
			gp.updatePos();
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) 
	{
		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_W) 
		{
			upPressed = false;
		}
		
		if (code == KeyEvent.VK_S) 
		{
			downPressed = false;
		}
		
		if (code == KeyEvent.VK_A) 
		{
			leftPressed = false;
		}
		
		if (code == KeyEvent.VK_D) 
		{
			rightPressed = false;
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) // can be used to check which key that has been pressed
	{

	}
	

}
