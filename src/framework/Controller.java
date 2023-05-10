package framework;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Controller implements ActionListener {


    private AbstractTileModel model;
    private GameView view;
    private InputController inputController;   
    
    public Controller(AbstractTileModel model, GameView view) {
        this.model = model;
        this.view = view;   
    }
       

    public void setController(InputController inputController) {
        this.inputController = inputController;  
    }

    public AbstractTileModel getAbstractTileModel() {
        return this.model = model;
    }

/*   
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand() == "UP"){           
            model.move(Direction.UP);
        }
        if (e.getActionCommand() == "DOWN") {
            model.move(Direction.DOWN);
        }
        if (e.getActionCommand() == "LEFT") {
            model.move(Direction.LEFT);
        }
        if (e.getActionCommand() == "RIGHT") {
            model.move(Direction.RIGHT);
        }

        view.paintBoard();

    }
 */
    @Override
    public void actionPerformed(ActionEvent e) {
       
        GameStateAndDiraction direction = inputController.move();                      

        if (direction == GameStateAndDiraction.UP) {
            model.move(GameStateAndDiraction.UP);
        }
        if (direction == GameStateAndDiraction.DOWN) {
            model.move(GameStateAndDiraction.DOWN);
        }
        if (direction == GameStateAndDiraction.LEFT) {
            model.move(GameStateAndDiraction.LEFT);
        }
        if (direction == GameStateAndDiraction.RIGHT) {
            model.move(GameStateAndDiraction.RIGHT);
        }

        //view.paintBoard();

    }





    //q: how can i create a button source for the arrow keys?

    //a: create a keylistener and add it to the frame, then use the keylistener to move the snake
    //q: how do i use the keylistener to move the snake?
    //a: use the keylistener to call the move method in the snake class
    //q: how can i create a scource for the arrow keys?
    //a: create a keylistener and add it to the frame, then use the keylistener to move the snake

 


 
    /* 
    @Override
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
    } */

}
