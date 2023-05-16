package framework;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {


    private AbstractTileModel model;
    private GameView view;//use this to get the JPanel?
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

    @Override
    public void actionPerformed(ActionEvent e) {
        GameStateAndDirection direction = inputController.move();
        model.input(direction);       
    }

}
