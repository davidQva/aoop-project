package application;

import framework.AbstractTileModel;
import framework.GameMonitor;

public class GameSokobanTest {
    
    public static void main(String[] args) throws Exception  {
        
        AbstractTileModel newGame = new Sokoban(13, 13, 50);      
         
        KeyboardController snakeController = new KeyboardController(newGame.getController());
        newGame.getController().setController(snakeController);
        newGame.getFrame().addKeyListener(snakeController);
 /* 
         SokobanMouseController snakeController2 = new SokobanMouseController(newGame.getController());
        newGame.getFrame().addMouseListener(snakeController2);
        snakeController2.setGame(newGame);
        newGame.getController().setController(snakeController2);  */

 
        SokobanTile prototype = new SokobanTile();
        newGame.addTile(null, prototype);

       
         GameSound sound = new GameSound();
        newGame.attach(sound); 
    /*     
        GameMonitor monitor = new GameMonitor();
        newGame.attach(monitor);      

       */

        newGame.addTile("crate", 0, prototype);
        newGame.addTile("cratemarked", 1, prototype);
        newGame.addTile("blank", 2, prototype);
        newGame.addTile("blankmarked", 3, prototype);
        newGame.addTile("wall", 4, prototype);
        newGame.addTile("player", 5, prototype);
        newGame.addTile("player", 6, prototype); 

        newGame.repaintBoard();
    }

}
