import java.awt.image.BufferedImage;

public class Character {
	
	// variables for position of character(x,y), and position change(position)  
	public int x, y;
	public int move;
	
	//Create variables for the icons that might need to react to inputs and events
	public BufferedImage crate, cratemarked, character;
	public boolean collisionEnabled = false;
}
