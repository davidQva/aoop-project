package framework;

/**
 * Observer interface for the game. This interface is used to notify the
 * observer of changes in the game. The passed parameters are the game board and
 * the state of the game.
 */
public interface GameObserver {

    public void notify(int[][] board, GameStateAndDirection stateUpdate);

}
