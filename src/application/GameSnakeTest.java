package application;

public class GameSnakeTest {
	
	public static void main(String[] args) throws Exception{
		
		Snake newGame = new Snake(10, 10, 30);

		KeyboardController snakeController = new KeyboardController(newGame.getController());
		newGame.getController().setController(snakeController);
		newGame.getFrame().addKeyListener(snakeController);
		SokobanTile prototype = new SokobanTile();
		newGame.addTile(99, prototype);

		newGame.addTile("snekkbod", 1, prototype);
		newGame.addTile("snekkapple", 2, prototype);
		newGame.addTile("wall", 0, prototype);


		PrintOut printOut = new PrintOut();
		newGame.attach(printOut);		
		
	}
}
