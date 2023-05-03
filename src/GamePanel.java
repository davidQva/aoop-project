import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GamePanel extends JPanel {
	
	public static final int MAX_SCREEN_W = 15;
	public static final int MAX_SCREEN_H = 15;
	static final int SCREEN_W = 1200;
	static final int SCREEN_H = 1200;
	public static final int UNIT_SIZE = 80;
	public static final int GAME_UNITS = (SCREEN_W*SCREEN_H)/UNIT_SIZE;
	
	final int x[] = new int[GAME_UNITS];
	final int y[] = new int[GAME_UNITS];
	char direction = ' ';
	
	TileManager tileMgr = new TileManager(this);
	KeyHandler keyH = new KeyHandler(this); 
	Player player = new Player(this,keyH); // add charcter with key listener
	
	public GamePanel() 
	{
		new TileManager(this);
		this.setPreferredSize(new Dimension(SCREEN_W,SCREEN_H));
		this.setBackground(Color.black);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		
		updatePos();
		repaint();
	}
	
	public void paintComponent(Graphics g) 
	{	
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		tileMgr.draw(g2);
		
		player.draw(g2);
		
		draw(g2); // print grid.
	}
	
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
	
	public void draw(Graphics g) 
	{
		/*for (int i = 0; i < SCREEN_H/UNIT_SIZE; i++) 
		{
			g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_H); // skapa ett rutnät baserat på storleken man valt som unit size.
			g.drawLine(0, i*UNIT_SIZE,SCREEN_W , i*UNIT_SIZE);
		}*/
	}
	
}
