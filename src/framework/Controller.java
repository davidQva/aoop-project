package framework;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller class for the game. This class is responsible for handling the
 * input from the user and passing it to the model.
 * actionListener is used to listen for input from the user.
 * input can be either a key press or a mouse click.
 * Controller is using the method pattern to get input from the user.
 */
public class Controller implements ActionListener {

    private AbstractTileModel model;

    private InputController inputController;

    /**
     * Constructor for the controller.
     * 
     * @param model
     */
    public Controller(AbstractTileModel model) {
        this.model = model;

    }

    /**
     * Sets the controller for the game.
     * 
     * @param inputController must use the interface InputController
     */
    public void setController(InputController inputController) {
        this.inputController = inputController;
    }

    /**
     * Returns the model for the game.
     * 
     * @return model
     */
    public AbstractTileModel getAbstractTileModel() {
        return this.model = model;
    }

    /**
     * passes the input from the user to the model.
     * 
     * @param input is the input from the user.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        StateAndDirection newInput = inputController.input();
        model.input(newInput);
    }

}
