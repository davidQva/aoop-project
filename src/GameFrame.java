import javax.swing.JFrame;

public class GameFrame extends JFrame{
	
	public GameFrame() 
	{
	this.add(new GamePanel()); // adds a gamepanel to the frame
	this.setTitle("Sowkowban");
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setResizable(false);
	this.addKeyListener(new KeyHandler(new GamePanel())); // adds a keylistener to the frame based on the gamepanel.
	this.pack();
	this.setLocationRelativeTo(null);
	this.setVisible(true);
	}
}
