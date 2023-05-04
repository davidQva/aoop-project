import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GamePanel extends JPanel {
	
	/*
	 * Creates a Max tile number in both height and width to make it possible to read the map.
	 * 
	 * */
	public static final int SCREEN_TILE_W = 16;
	public static final int SCREEN_TILE_H = 12;
	public static final int UNIT_SIZE = 60;

	/*
	 * Adding a instance of the classes below to the gamepanel and a keylistener that is coupled with the player.
	 * */
	
	public TileManager tileMgr;
	KeyHandler keyH = new KeyHandler(this);
	public CollisionStatus cStatus = new CollisionStatus(this);
	public Player player = new Player(this,keyH);
	
	public GamePanel()
	{
		tileMgr = new TileManager(this);
		this.setPreferredSize(new Dimension((UNIT_SIZE*SCREEN_TILE_W),(UNIT_SIZE*SCREEN_TILE_H)));
		this.setBackground(Color.black);
		this.addKeyListener(keyH);
		this.setFocusable(true); // make the gamepanel react to keyInputs
		
		updatePos();
		tileMgr.printCurrentMap();
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
			if(tileMgr.mapTileNum[player.pos.x][player.pos.y - 1] == 4 )
				return;
			//else if(tileMgr.mapTileNum[player.pos.x][player.pos.y - 1] == 1)
			
			player.update();
			repaint();
		}
		
		if(keyH.downPressed == true)
		{
			if(tileMgr.mapTileNum[player.pos.x][player.pos.y + 1] == 4 )
				return;
			//else if(tileMgr.mapTileNum[player.pos.x][player.pos.y - 1] == 1)
				
			player.update();
			repaint();
		}
		
		if(keyH.leftPressed == true)
		{
			if(tileMgr.mapTileNum[player.pos.x - 1][player.pos.y] == 4 )
				return;
			//else if(tileMgr.mapTileNum[player.pos.x][player.pos.y - 1] == 1)
			
			player.update();
			repaint();
		}
		
		if(keyH.rightPressed == true)
		{
			if(tileMgr.mapTileNum[player.pos.x + 1][player.pos.y] == 4 )
				return;
			//else if(tileMgr.mapTileNum[player.pos.x][player.pos.y - 1] == 1) 
			
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
