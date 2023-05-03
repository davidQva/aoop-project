import javax.swing.JFrame;

public class GameFrame extends JFrame{
	
	public GameFrame() 
	{
	this.add(new GamePanel());
	this.setTitle("MyGame");
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setResizable(false);
	this.addKeyListener(new KeyHandler(new GamePanel()));
	this.pack();
	this.setLocationRelativeTo(null);
	this.setVisible(true);
	}
}
