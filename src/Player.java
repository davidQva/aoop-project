import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player extends Character{
	
	GamePanel gp;
	KeyHandler keyH;
	
	public Player(GamePanel gp, KeyHandler keyH) 
	{
		this.gp = gp;
		this.keyH = keyH;
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() 
	{
		x = 240;
		y = 240;
		position = 80;
	}
	
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

	public void update() 
	{
		if(keyH.upPressed == true) 
		{
			y -= position;
		}
		else if(keyH.downPressed == true)
		{
			y += position;
		}
		else if(keyH.leftPressed == true)
		{
			x -= position;
		}
		else if(keyH.rightPressed == true)
		{
			x += position;
		}
	}
	
	public void draw(Graphics2D g2) 
	{	
		BufferedImage image = character;
		g2.drawImage(image, x, y, gp.UNIT_SIZE, gp.UNIT_SIZE, null);
	}
}
