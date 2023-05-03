import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GamePanel extends JPanel {
	
	/*
	 * Creates a Max tile number in both height and width to make it possible to read the map.
	 * 
	 * */
	
	public static final int MAX_SCREEN_W = 16;
	public static final int MAX_SCREEN_H = 12;
	public static final int SCREEN_W = 960;
	public static final int SCREEN_H = 720;
	public static final int UNIT_SIZE = 60;

	/*
	 * Adding a instance of the classes below to the gamepanel and a keylistener that is coupled with the player.
	 * */
	TileManager tileMgr = new TileManager(this);
	KeyHandler keyH = new KeyHandler(this); 
	Player player = new Player(this,keyH); 
	
	public GamePanel() 
	{
		new TileManager(this);
		this.setPreferredSize(new Dimension(SCREEN_W,SCREEN_H));
		this.setBackground(Color.black);
		this.addKeyListener(keyH);
		this.setFocusable(true); // make the gamepanel react to keyInputs
		
		updatePos();
		repaint();
	}
	
	/*
	 * Draw the tile icons first and then draw the player to keep the player at the top "layer" of the gamePanel
	 */
	
	public void paintComponent(Graphics g) 
	{	
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		tileMgr.draw(g2);
		
		player.draw(g2);
		
		//draw(g2); // draw grid
	}
	
	/*
	 * update the position of the player based on the boolean value of the *direction* pressed variable
	 * which updates the position of the character based on the size of a tile, then repaints the character icon.
	 * */
	
	public void updatePos() 
	{
		if(keyH.upPressed == true) 
		{
			player.update();	
			repaint();
		}
		
		if(keyH.downPressed == true) 
		{
			player.update();	
			repaint();
		}
		
		if(keyH.leftPressed == true) 
		{
			player.update();
			repaint();
		}
		
		if(keyH.rightPressed == true) 
		{
			player.update();
			repaint();
		}
		
	}
	
	/*public void draw(Graphics g) 
	{
		for (int i = 0; i < SCREEN_H/UNIT_SIZE; i++) 
		{
			g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_H); // skapa ett rutnät baserat på storleken man valt som unit size.
			g.drawLine(0, i*UNIT_SIZE,SCREEN_W , i*UNIT_SIZE);
		}
	}*/
	
}
