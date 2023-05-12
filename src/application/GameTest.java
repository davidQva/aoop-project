package application;

import framework.GameMonitor;
import framework.GameSound;

public class GameTest {
    
    public static void main(String[] args) throws Exception {
        
        Sokoban newGame = new Sokoban(13, 13, 70);
        
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

        //PrintOut printOut = new PrintOut();

        //newGame.attach(printOut);
        
        GameSound sound = new GameSound();
        newGame.attach(sound);
        
        GameMonitor monitor = new GameMonitor();
        newGame.attach(monitor);     

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
