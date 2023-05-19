package framework;

/**
 * InputController is an interface for the input to GameController. The input
 * controller is responsible for getting input from the user and passing it to the model.
 * The input controller is using the method pattern to implement the input.
 */
public interface InputController {
    public StateAndDirection input();
}
