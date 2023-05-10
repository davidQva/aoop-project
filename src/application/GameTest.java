package application;

import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class GameTest {

    public static void main(String[] args) throws Exception {

        Sokoban newGame = new Sokoban(13, 13, 50);
        /*
         * SokobanController2 snakeController2 = new
         * SokobanController2(newGame.getController());
         * newGame.getFrame().addMouseListener(snakeController2);
         * newGame.getController().setController(snakeController2);
         */
        SokobanController snakeController = new SokobanController(newGame.getController());
        newGame.getController().setController(snakeController);
        newGame.getFrame().addKeyListener(snakeController);
        SokobanTile prototype = new SokobanTile();
        newGame.addTile(99, prototype);   


        Image imgWall = ImageIO.read(new File("aoop-project/resources/wall.png"));
        Image scaledimgWall = imgWall.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon imageIconWall = new ImageIcon(scaledimgWall);
        
        Image imgPlayer = ImageIO.read(new File("aoop-project/resources/player.png"));
        Image scaledimgPlayer = imgPlayer.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon imageIconPlayer = new ImageIcon(scaledimgPlayer);

        Image imgBox = ImageIO.read(new File("aoop-project/resources/crate.png"));
        Image scaledimgBox = imgBox.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon imageIconBox = new ImageIcon(scaledimgBox);
    
        Image imgGoal = ImageIO.read(new File("aoop-project/resources/blankmarked.png"));
        Image scaledimgGoal = imgGoal.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon imageIconGoal = new ImageIcon(scaledimgGoal);

        Image imgFloor = ImageIO.read(new File("aoop-project/resources/blank.png"));
        Image scaledimgFloor = imgFloor.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon imageIconFloor = new ImageIcon(scaledimgFloor);


        Image imgBoxOnGoal = ImageIO.read(new File("aoop-project/resources/cratemarked.png"));
        Image scaledimgBoxOnGoal = imgBoxOnGoal.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon imageIconBoxOnGoal = new ImageIcon(scaledimgBoxOnGoal);
              
  
        // ImageIcon imageIconPlayerOnGoal = new
        // ImageIcon("src/application/playerOnGoal.png");

        SokobanTile player = prototype.clone();     
        player.setTile(imageIconPlayer);
        SokobanTile wall = prototype.clone(); 
        wall.setTile(imageIconWall);
        SokobanTile box = prototype.clone(); 
        box.setTile(imageIconBox);
        SokobanTile goal = prototype.clone(); 
        goal.setTile(imageIconGoal);
        SokobanTile floor = prototype.clone(); 
        floor.setTile(imageIconFloor);
        SokobanTile boxOnGoal = prototype.clone();
        boxOnGoal.setTile(imageIconBoxOnGoal);

        newGame.addTile(5, player);
        newGame.addTile(4, wall);
        newGame.addTile(0, box);
        newGame.addTile(3, goal);
        newGame.addTile(2, floor);
        newGame.addTile(1, boxOnGoal);   

        newGame.getView().paintBoard();
    }

}
