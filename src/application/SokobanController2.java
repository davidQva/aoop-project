package application;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import framework.Controller;
import framework.GameStateAndDiraction;
import framework.InputController;

public class SokobanController2 implements InputController, MouseListener {

    private Controller model;
    private GameStateAndDiraction direction;

    public SokobanController2(Controller model) {
        this.model = model;
    }

    @Override
    public GameStateAndDiraction move() {
        return this.direction;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        Point x = e.getPoint(); 
        
        int row = model.getAbstractTileModel().getBoard().length;
        int col = model.getAbstractTileModel().getBoard()[0].length;
        
        String s = "";

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++){
                if (x.x > j * 50 && x.x < j * 50 + 50 && x.y > i * 50 && x.y < i * 50 + 50) {
                    s = s + i + " " + j;
                }
            }
        }     

        System.out.println(s);

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
