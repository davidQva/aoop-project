package application;

import java.awt.Frame;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import framework.GameFrame;
import framework.TileRegistry;



public class GameTest{ 
    
    public static void main(String[] args) throws IOException {

        Snake snake = new Snake(10, 10, 50);
        GameFrame frame = new GameFrame();
        frame.SetBoard(snake);


        TileRegistry registry = new TileRegistry();

        SnakeTiles tile = new SnakeTiles(); 

        
        Image image = ImageIO.read(new File("aoop-project/resources/player.png"));
        Image scaledImage = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage); 

        tile.setSize(50);
        tile.setTile(scaledIcon);
        
        registry.addPrototypeTile("player", tile);
                
        snake.setTile(registry.getPrototypeTile("player").getTile(), 3, 2);        
       
        Image image2 = ImageIO.read(new File("aoop-project/resources/wall.png"));
        Image scaledImage2 = image2.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon2 = new ImageIcon(scaledImage2);

        SnakeTiles tile2 = (SnakeTiles) registry.getPrototypeTile("player");
        tile2.setTile(scaledIcon2);

        registry.addPrototypeTile("wall", tile2);

        snake.setTile(registry.getPrototypeTile("wall").getTile(), 2, 5);
        
 


        frame.pack();

    }
}
