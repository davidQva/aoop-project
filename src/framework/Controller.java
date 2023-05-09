package framework;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener {


    private TileModel model;
    private GameView view;


    public Controller(TileModel model, GameView view) {
        this.model = model;
        this.view = view;
    }
 /* 
    @Override
    public void actionPerformed(ActionEvent e) {

        int[][] test2 = { {1,1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1 ,1}, {1, 1, 1, 1} };
        model.setBoard(test2);
        view.paintBoard();
       
    }
 
 */    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int[][] test2 = { {1,1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1 ,1}, {1, 1, 1, 1} };
        
        int[][] test1 = { {2,2, 2, 2}, {1, 1, 1, 1}, {1, 1, 1 ,1}, {1, 1, 1, 1} };

        int[][] test3 = { {2,2, 2, 2}, {2,2, 2, 2}, {1, 1, 1 ,1}, {1, 1, 1, 1} };

        int[][] test4 = { {2,2, 2, 2}, {1, 1, 1, 1}, {2,2, 2, 2}, {1, 1, 1, 1} };      

        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                model.setBoard(test4);
                view.paintBoard();                            
                break;
            case KeyEvent.VK_A:
            model.setBoard(test1);
            view.paintBoard();
                break;
            case KeyEvent.VK_S:
            model.setBoard(test2);
            view.paintBoard();
                break;              
            case KeyEvent.VK_D:
            model.setBoard(test3);
            view.paintBoard();
               break;
            }
       
    }
    

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
    }

}
