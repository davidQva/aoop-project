package application;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.SwingUtilities;
import framework.Controller;
import framework.GameStateAndDirection;
import framework.InputController;

public class SokobanMouseController implements InputController, MouseListener {

    private Controller model;
    private GameStateAndDirection direction;
    private Sokoban game;

    public SokobanMouseController(Controller model) {
        this.model = model;
    }

    @Override
    public GameStateAndDirection move() {
        return this.direction;
    }

    public void setGame(Sokoban game) {
        this.game = game;
    }

    @Override
    public void mouseClicked(MouseEvent e) {    
     
        int x = game.getPlayerPositionX();
        int y = game.getPlayerPositionY();

        Point X = e.getPoint();
        Point panelLocation = SwingUtilities.convertPoint(game.getView(), 0, 0, game.getFrame().getParent());

        int offSetX = panelLocation.x;

        int offSetY =  panelLocation.y;

        X.x = X.x - offSetX;
        X.y = X.y - offSetY;


       
        int divRow = X.x / 50;
        int divCol = X.y / 50;
               
        
        String s = "";

      
    
                    if(x == divRow + 1 && y == divCol){
                        this.direction = GameStateAndDirection.LEFT;
                    }
                    else if(x == divRow - 1 && y == divCol){
                        this.direction = GameStateAndDirection.RIGHT;
                    }
                    else if(x == divRow && y == divCol + 1){
                        this.direction = GameStateAndDirection.UP;
                    }
                    else if(x == divRow && y == divCol - 1){
                        this.direction = GameStateAndDirection.DOWN;
                    }                   
                    else{
                        this.direction = GameStateAndDirection.NONE;
                    }

                    System.out.println("X: " + divRow + " Y: " + divCol + " Direction: " + this.direction);
       
        model.actionPerformed(null);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
      
    }

    @Override
    public void mouseEntered(MouseEvent e) {
       
    }

    @Override
    public void mouseExited(MouseEvent e) {
      
    }
    
}
