import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

public class TileManager {
	
	GamePanel gp;
	Tile[] tile;
	int mapTileNum[][];
	
	public TileManager(GamePanel gp) 
	{
		tile = new Tile[10];
		mapTileNum = new int[gp.MAX_SCREEN_W][gp.MAX_SCREEN_H];
		
		getTileImage();
		loadMap("/maps/map03.txt");
	}
	
	public void getTileImage() 
	{
		try 
		{
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/Icons/crate.png"));
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/Icons/cratemarked.png"));
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/Icons/blank.png"));
			
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/Icons/blankmarked.png"));
			
			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/Icons/wall.png"));
			
			tile[5] = new Tile();
			tile[5].image = ImageIO.read(getClass().getResourceAsStream("/Icons/player.png"));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void loadMap(String filePath) 
	{
		try 
		{
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col < gp.MAX_SCREEN_W && row < gp.MAX_SCREEN_H) 
			{
				String line = br.readLine();
				
				while(col < gp.MAX_SCREEN_W) //read in each column in the first row until max width then go down one row
				{
					String numbers[] = line.split(" ");
						
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[col][row] = num;
					col++;
				}
				
				if(col == gp.MAX_SCREEN_W) 
				{
					col = 0;
					row++;
				}
			}
			br.close();
		}
		catch(IOException e) 
		{
			
		}
	}
	
	public void draw(Graphics2D g2) 
	{
		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		
		while(col < gp.MAX_SCREEN_W && row < gp.MAX_SCREEN_H) 
		{
			int tileNum = mapTileNum[col][row];
			
			g2.drawImage(tile[tileNum].image, x, y, gp.UNIT_SIZE, gp.UNIT_SIZE, null);
			col++;
			x += gp.UNIT_SIZE;
			
			if(col == gp.MAX_SCREEN_W) 
			{
				col = 0;
				x = 0;
				row++;
				y += gp.UNIT_SIZE;
			}
			
		}
	}
	
}
