package game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class GameImages extends JComponent implements GameObserver{

	private static final long serialVersionUID = 1L;
	
	private final File crate, cratemarked, ground, groundmarked, player, wall;
	private File[][] mapImages;
	 
	 /*
	  *read and save the image files from the resources source folder into represented variables. 
	  * */
	public GameImages() 
	{
		 super();
		 crate = new File("resources/images/crate.png");
	     cratemarked = new File("resources/images/cratemarked.png");
	     player = new File("resources/images/player.png");
	     ground = new File("resources/images/blank.png");
	     groundmarked = new File("resources/images/blankmarked.png");
	     wall = new File("resources/images/wall.png");
	}
	
	
	/*
	 * Defines which images that should be printed at the specific positions on the map,
	 * the second integer for player was needed when walking over the marked spots on the map
	 * otherwise the map updated the marked spot to a regular ground image.
	 * */
	public File[][] defineMap(int[][] field)
	{
		File[][] temp = new File[field.length][field[0].length];
		for (int i = 0; i < field.length; i++) 
		{
			for (int j = 0; j < field[0].length; j++) 
			{
					if(field[i][j] == 0) 
				{
					temp[i][j] = crate;
				}
				else if(field[i][j] == 1) 
				{
					temp[i][j] = cratemarked;
				}
				else if(field[i][j] == 2) 
				{
					temp[i][j] = ground;
				}
				else if(field[i][j] == 3) 
				{
					temp[i][j] = groundmarked;
				}
				else if(field[i][j] == 4) 
				{
					temp[i][j] = wall;
				}
				else if(field[i][j] == 5 || field[i][j] == 6) 
				{
					temp[i][j] = player;
				}
			}
		}
		return temp;
	}
	
	/*
	 * This method draws the images onto the visual frame.
	 * */
	public void paintComponent(Graphics g) 
	{
		for (int i = 0; i < mapImages[0].length; i++) 
		{
			for (int j = 0; j < mapImages.length; j++) 
			{
				File tempMaptile = mapImages[j][i];
				
				BufferedImage image = null;
				try 
				{
					image = ImageIO.read(tempMaptile);
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
				scaleImage(image,60,60);
				
				Graphics2D g2 = (Graphics2D)g;
				g2.drawImage(scaleImage(image,60,60), null, 60*i, 60*j);

				//drawResult(g2);
	
			}
		}
	}
	
	/*public void drawResult(Graphics2D g) 
	{
		
		
		if(gl != null) 
		{
		if(gl.wonStatus() == true)
		{
			g.setColor(Color.blue);
			g.setFont(new Font("Ink Free",Font.BOLD, 25));
			g.drawString("You Win!", 150, 150);
			if(gl.wonStatus() == false)
				g.dispose();
		}
		
		if(gl.lostStatus() == 1)
		{
			g.setColor(Color.red);
			g.setFont(new Font("Ink Free",Font.BOLD, 25));
			g.drawString("Game Lost!", 150, 150);
			if(gl.lostStatus() == 0)
				g.dispose();
		}
		
		if(gl.pauseStatus() == 1)
		{
			g.setColor(Color.white);
			g.setFont(new Font("Ink Free",Font.BOLD, 25));
			g.drawString("Game Paused", 150, 150);
			if(gl.pauseStatus() == 0)
				g.dispose();
		}
		}
	}*/

	/*
	 * This method is used to scale the image to a bigger size than the original.
	 * */
	public static BufferedImage scaleImage(BufferedImage Image, int NewWidth, int NewHeight) {
	    BufferedImage resizedImage = new BufferedImage(NewWidth, NewHeight, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2d = resizedImage.createGraphics();
	    g2d.drawImage(Image, 0, 0, NewWidth, NewHeight, null);
	    g2d.dispose();
	    return resizedImage;
	}
	
	/*
	 * Updates the visual map.
	 * */
	@Override
	public void UpdateField(int[][] field, String update)
	{
		mapImages = defineMap(field);
		repaint();
	}

}
