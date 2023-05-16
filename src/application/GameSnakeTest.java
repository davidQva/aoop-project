package application;

public class GameSnakeTest {
	
	public static void main(String[] args) throws Exception{
		
		Snake newGame = new Snake(20, 20, 30);

		KeyboardController snakeController = new KeyboardController(newGame.getController());
		newGame.getController().setController(snakeController);
		newGame.getFrame().addKeyListener(snakeController);
		SokobanTile prototype = new SokobanTile();
		newGame.addTile(null, prototype);

		newGame.addTile("snekkbod", 1, prototype);
		newGame.addTile("snekkapple", 2, prototype);
		newGame.addTile("wall", 0, prototype);
		newGame.addTile("snekkhead", 3, prototype);
		
		PrintOut printOut = new PrintOut();
		newGame.attach(printOut);
		
	}
}
