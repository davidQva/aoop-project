package application;

import framework.AbstractTileModel;
import framework.GameStateAndDirection;

public class TestGame extends AbstractTileModel {

    public TestGame(int col, int row, int size) {
        super(col, row, size);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void input(GameStateAndDirection direction) {
     
        switch (direction) {
            case UP:
                System.out.println("UP");
                break;
        
            default:
                break;
        }
    }

    @Override
    public void moveUp() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'moveUp'");
    }

    @Override
    public void moveDown() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'moveDown'");
    }

    @Override
    public void moveLeft() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'moveLeft'");
    }

    @Override
    public void moveRight() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'moveRight'");
    }

    @Override
    public void setBoard() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setBoard'");
    }

    @Override
    public void update() {
            notifyAllObservers();     
    }
    
  

    
}
