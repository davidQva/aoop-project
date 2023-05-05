/*import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class CollisionObject extends TileObjects {

	GamePanel gp;
	TileManager tileMgr;
	public Point cratePos;
	public ArrayList<Point> pointArr;
	
	public CollisionObject(GamePanel gp, TileManager tm) 
	{
		this.gp = gp;
		this.tileMgr = tm;
		pointArr = new ArrayList<Point>();
		
		getCrateImage();
	}
	
	public void getCrateImage() 
	{
		try 
		{
			crate = ImageIO.read(getClass().getResourceAsStream("/Icons/crate.png"));
			cratemarked = ImageIO.read(getClass().getResourceAsStream("/Icons/cratemarked.png"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void getColObjects() 
	{
		int col = 0;
		int row = 0;
		
		while(col < gp.SCREEN_TILE_W && row < gp.SCREEN_TILE_H) 
		{
			while (col < gp.SCREEN_TILE_W) 
			{	
				if(tileMgr.mapTileIndex[col][row] == tileMgr.tile[0]) ;
				
				getPoint(col,row);
				
				col++;
			}
			
			if(col == gp.SCREEN_TILE_W) 
			{
				System.out.println();
				col = 0;
				row++;
			} 
		}
	}
	
	public void getPoint(int x, int y) 
	{
		for (int i = 0; i < pointArr.size(); i++) 
		{
			pointArr.add(new Point(x,y));
		}	
		
	}
	
	
	 //Skapa en låda på varje punkt som har en låda enligt mapindex.
	 
	public void draw(Graphics2D g2) 
	{	
		BufferedImage image = crate;
		BufferedImage image2 = cratemarked;
		
		for (Point point : pointArr) 
		{	
			g2.drawImage(image, point.x * gp.UNIT_SIZE, point.y * gp.UNIT_SIZE, gp.UNIT_SIZE, gp.UNIT_SIZE,null);
		}
		
		if(tileMgr.mapTileIndex[cratePos.x][cratePos.y] == tileMgr.tile[1]) 
		{
			g2.drawImage(image2, cratePos.x * gp.UNIT_SIZE, cratePos.y * gp.UNIT_SIZE, gp.UNIT_SIZE, gp.UNIT_SIZE,null);
		}
	}
	
}*/
