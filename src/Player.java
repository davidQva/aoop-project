import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player extends Character{
	
	GamePanel gp;
	TileManager tileMgr;
	public KeyHandler keyH;
	public String direction;
	
	
	/*
	 * Creates a link between the player and the gamepanel with a keylistener.
	 * fetches the character icon and puts the character in the default position.
	 * */
	public Player(GamePanel gp, KeyHandler keyH) 
	{
		this.gp = gp;
		this.keyH = keyH;
		
		setDefaultValues();
		getPlayerImage();
	}
	
	/*
	 * sets the default values of the character position and the movement(position)
	 * */
	
	public void setDefaultValues() 
	{
		x = 180;
		y = 180;
		move = 60;
	}
	
	/*
	 *	reads the character image file from the icons folder in the source folder "resources" 
	 */
	
	public void getPlayerImage() 
	{
		try 
		{
			character = ImageIO.read(getClass().getResourceAsStream("/Icons/player.png"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/*
	 * updates the value of the x and y coordinates based on if the direction pressed variable is true.
	 * where it increases in y as it goes down and decreases as it goes up. with the same notion for left and right.
	 * */
	
	public void update() 
	{
		
		if(keyH.upPressed == true) 
		{
			direction = "up";
			y -= move;
		}
		else if(keyH.downPressed == true)
		{
			direction = "down";
			y += move;
		}
		else if(keyH.leftPressed == true)
		{
			direction = "left";
			x -= move;
		}
		else if(keyH.rightPressed == true)
		{
			direction = "right";
			x += move;
		}
		
		gp.cStatus.tileStatus(this);
		
	}
	
	/*
	 * draws the character in the size of tile (Unit_size)
	 * */
	public void draw(Graphics2D g2) 
	{	
		BufferedImage image = character;
		g2.drawImage(image, x, y, gp.UNIT_SIZE, gp.UNIT_SIZE, null);
	}
}
