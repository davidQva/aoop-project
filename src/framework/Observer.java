package framework;

/**
 * Observer interface for the game. This interface is used to notify the
 * observer of changes in the game. The passed parameters are the game board and
 * the state of the game.
 */
public interface Observer {

    public void notify(int[][] board, StateAndDirection stateUpdate);

}
