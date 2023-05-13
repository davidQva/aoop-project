package application;

public class snakeTest {
	
	public static void main(String[] args) throws Exception{
		
		Snake newGame = new Snake(50,50,20);
		
		SnakeController snekkcontroll = new SnakeController(newGame.getController());
		newGame.getController().setController(snekkcontroll);
		newGame.getFrame().addKeyListener(snekkcontroll);
		SokobanTile prototype = new SokobanTile();
		newGame.addTile(99, prototype);
		
		newGame.addTile("blank", 0, prototype);
		newGame.addTile("wall", 1, prototype);
		newGame.addTile("blankmarked", 3, prototype);
		
		newGame.repaintBoard();  
	}
}
