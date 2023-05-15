package framework;

public interface GameObserver {

    public void notify(int[][] board, GameStateAndDirection direction);

}
