package application;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.SwingUtilities;
import framework.Controller;
import framework.GameStateAndDirection;
import framework.InputController;

/**
 * MouseController implements the interface InputController and MouseListener.
 * MouseController is responsible for getting input from the user and passing
 * it to the Controller.
 */
public class MouseController implements InputController, MouseListener {

    private Controller model;
    private GameStateAndDirection input;
    private SokobanGame game;

    /**
     * Constructor for the MouseController.
     * 
     * @param model is the controller for the game.
     */
    public MouseController(Controller model) {
        this.model = model;
    }

    /**
     * Returns the input from the user.
     * 
     * @return direction
     */
    @Override
    public GameStateAndDirection input() {
        return this.input;
    }

    /**
     * To pass state of the game to the controller.
     * so that the controller can provide direction to the model.
     * 
     * @param game
     */
    public void setGame(SokobanGame game) {
        this.game = game;
    }

    /**
     * when the mouse is clicked the input is set and checks if can provide a valid
     * direction.
     * If valid the input is then passed to the controller.
     * 
     * @param e is the event from the user.
     */
    @Override
    public void mouseClicked(MouseEvent e) {

        int x = game.getPlayerPositionX();
        int y = game.getPlayerPositionY();

        Point X = e.getPoint();
        Point panelLocation = SwingUtilities.convertPoint(game.getView(), 0, 0, game.getFrame().getParent());

        int offSetX = panelLocation.x;
        int offSetY = panelLocation.y;

        X.x = X.x - offSetX;
        X.y = X.y - offSetY;

        int tileSize = game.getTileSize();

        int divRow = X.x / tileSize;
        int divCol = X.y / tileSize;

       // String s = "";

        if (x == divRow + 1 && y == divCol) {
            this.input = GameStateAndDirection.LEFT;
        } else if (x == divRow - 1 && y == divCol) {
            this.input = GameStateAndDirection.RIGHT;
        } else if (x == divRow && y == divCol + 1) {
            this.input = GameStateAndDirection.UP;
        } else if (x == divRow && y == divCol - 1) {
            this.input = GameStateAndDirection.DOWN;
        } else {
            this.input = GameStateAndDirection.NONE;
        }

      //  System.out.println("X: " + divRow + " Y: " + divCol + " Direction: " + this.input);

        /**
         * event is passed to the controller.
         */
        model.actionPerformed(null);
    }


    /**
     * not used
     */
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
