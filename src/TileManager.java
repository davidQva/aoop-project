import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

public class TileManager {
	
	/*
	 * create an instance of the gamepanel
	 * an array of tiles and map tile array for drawing the icons in the gamepanel.
	 * */
	
	GamePanel gp;
	public Tile[] tile;
	public int[][] mapTileNum;
	
	/*
	 * make the arrays to the appropriate size for the screen and number of tiles.
	 * get the icons from the icons folder in the resources source folder
	 * load the input map from the maps folder in the resources folder
	 * */
	
	public TileManager(GamePanel gp) 
	{
		tile = new Tile[10];
		mapTileNum = new int[gp.SCREEN_TILE_W][gp.SCREEN_TILE_H];
		
		getTileImage();
		loadMap("/maps/map03.txt");
	}
	
	/*
	 * read the icon files from the chosen location and saves them in the image variable that is stored in an array of bufferedimages.
	 * */
	public void getTileImage() 
	{
		try 
		{
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/Icons/crate.png"));
			tile[0].collision = true;
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/Icons/cratemarked.png"));
			tile[1].collision = true;
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/Icons/blank.png"));
			
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/Icons/blankmarked.png"));
			
			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/Icons/wall.png"));
			tile[4].collision = true;
			
			tile[5] = new Tile();
			tile[5].image = ImageIO.read(getClass().getResourceAsStream("/Icons/player.png"));
			tile[5].collision = true;
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	/*
	 * This loadMap works as follows: Read the map file by utilizing the Inputstream to import the text file, then using the
	 * BufferedReader to read the contents of the text file.
	 * By utilizing the variables col and row with the readline method, it's possible to read the map 
	 * configuration based on the values in the rows and columns
	 * when creating the map we make spaces in between the values to not make it into one string, 
	 * then by using the line.split we read one string value at a time
	 * which is then parsed from a string into an int which gets inserted in the "mapTileNum" array.
	 * when one the column reaches the max width of the screen, then it will continue with the next row, 
	 * when finished we close the buffered reader.
	 * */
	
	public void loadMap(String filePath) 
	{
		try 
		{
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col < gp.SCREEN_TILE_W && row < gp.SCREEN_TILE_H) 
			{
				String line = br.readLine();
				
				while(col < gp.SCREEN_TILE_W) 
				{
					String[]  numbers= line.split(" ");
						
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[col][row] = num;
					
					col++;
				}
				
				if(col == gp.SCREEN_TILE_W) 
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
	
	public void printCurrentMap() 
	{
		int col = 0;
		int row = 0;
		
		while(col < gp.SCREEN_TILE_W && row < gp.SCREEN_TILE_H) 
		{
			while (col < gp.SCREEN_TILE_W) 
			{	
				System.out.print(mapTileNum[col][row] + " ");
				
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
	
	
	/*
	 * this draw works as follows: Starts drawing a tile at the upper left corner then it increment the col by one 
	 * and then increase the value of x by the size of a UNIT tile.
	 * When reaching the end column max value, it resets the col and x variables to start on the next row by incrementing
	 * the row variable by 1 and y variable by the size of a UNIT tile
	 * */
	public void draw(Graphics2D g2) 
	{
		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		
		while(col < gp.SCREEN_TILE_W && row < gp.SCREEN_TILE_H) 
		{
			int tileNum = mapTileNum[col][row];
			
			g2.drawImage(tile[tileNum].image, x, y, gp.UNIT_SIZE, gp.UNIT_SIZE, null);
			
			col++;
			x += gp.UNIT_SIZE;
			
			if(col == gp.SCREEN_TILE_W) 
			{
				col = 0;
				x = 0;
				row++;
				y += gp.UNIT_SIZE;
			}
			
		}
	}
	
}
