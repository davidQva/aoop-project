package application;

import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


import java.io.File;
import java.io.IOException;



import framework.GameFrame;
import framework.GameView;
import framework.TileModel;

public class GameTest2 {
    

    public static void main(String[] args) throws IOException {
        
        
        
        
        int[][] test = { {1,1,1,1}, {2, 2, 2, 2}, {2, 2, 2 ,2}, {1, 1, 1, 1} };
        
        
        SnakeTiles tile = new SnakeTiles();
        TileModel tileModel = new TileModel(4,4);
        GameView gameView = new GameView(tileModel, 50);       
        GameFrame gameFrame = new GameFrame(gameView, tileModel);
        gameFrame.setVisible(true);
    
        Image image = ImageIO.read(new File("aoop-project/resources/player.png"));
        Image scaledImage = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage); 

        tile.setSize(50);
        tile.setTile(scaledIcon);

        SnakeTiles tile2 = tile.clone();

            
        Image image2 = ImageIO.read(new File("aoop-project/resources/wall.png"));
        Image scaledImage2 = image2.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon2 = new ImageIcon(scaledImage2); 

        tile2.setSize(50);
        tile2.setTile(scaledIcon2);
        
        gameView.addTiles(1, tile);
        gameView.addTiles(2, tile2);

       // gameView.setTile(tile, 1, 0);

       // gameFrame.SetBoard(gameView,tileModel);      

        tileModel.setBoard(test);

        gameView.paintBoard();
      
        int[][] test2 = { {1,1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1 ,1}, {1, 1, 1, 1} };


      //  tileModel.setBoard(test2);

      //  gameView.paintBoard();



       
       
       

    }


}
