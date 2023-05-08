package game;
/*
 * This is an observer interface which will keep track of the current map layout and state.
 * */
public interface GameObserver {
	void UpdateField(int[][] field, String update);
}
