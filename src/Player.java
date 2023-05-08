import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player extends TileObjects{
	
	GamePanel gp;
	TileManager tileMgr;
	public KeyHandler keyH;
	public String direction;
	public Point pos;
	
	/*
	 * Creates a link between the player and the gamepanel with a keylistener.
	 * fetches the character icon and puts the character in the default position.
	 * */
	
	public Player(GamePanel gp, KeyHandler keyH) 
	{
		this.gp = gp;
		this.keyH = keyH;
		
		startPoint(1,1);
		getPlayerImage();
	}
	
	
	public void startPoint(int x, int y) 
	{
		pos = new Point(x , y);
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
			//y -= move;	
			direction = "up";
			pos.translate(0, -1);
			System.out.print(pos.getX() + " ");
			System.out.print(pos.getY());
			System.out.println();
		}
		else if(keyH.downPressed == true)
		{
			//y += move;
			direction = "down";
			pos.translate(0, 1);
			System.out.print(pos.getX() + " ");
			System.out.print(pos.getY());
			System.out.println();
		}
		else if(keyH.leftPressed == true)
		{
			//x -= move;
			direction = "left";
			pos.translate(-1, 0);
			System.out.print(pos.getX() + " ");
			System.out.print(pos.getY());
			System.out.println();
		}
		else if(keyH.rightPressed == true)
		{
			//x += move;
			direction = "right";
			pos.translate(1, 0);
			System.out.print(pos.getX() + " ");
			System.out.print(pos.getY());
			System.out.println();
		}
		
	}
	
	/*
	 * draws the character in the size of tile (Unit_size)
	 * */
	public void draw(Graphics2D g2) 
	{	
		BufferedImage image = character;
		g2.drawImage(image, pos.x * gp.UNIT_SIZE, pos.y * gp.UNIT_SIZE, gp.UNIT_SIZE, gp.UNIT_SIZE,null);
	}
	
}
