package game;

import java.awt.*;

import javax.swing.*;

public abstract class Game extends JFrame{

	private static final long serialVersionUID = 1L;
	
	/*
	 * Details about the variables below:
	 * PlayerX and PlayerY represent the current position of the player in x and y.
	 * IntegerMap is the Map presented as the map written in integers.
	 * PlayerStartX and PlayerStartY represents the start position for the player in x and y.
	 * */
	private static final GameImages Gimg = new GameImages();
	private final JFrame frame =  new JFrame();
	
	private int PlayerStartX;
	private int PlayerStartY;
	private int CurrPlayerX;
	private int CurrPlayerY;
	int[][] CurrMap;
	int[][] StartMap;
	
	public abstract void PressedUp();
	public abstract void PressedDown();
	public abstract void PressedLeft();
	public abstract void PressedRight();
	public abstract void PressedPause();
	public abstract void PressedReset();
	
	public Game() 
	{
		startUp();
	}
	
	/*
	 * Below are essential methods which is used to update and get the values needed
	 * to make changes to positions and updating the game map as well as saving the 
	 * original map layout as a variable.
	 * */
	private int[][] getMapLevel() 
	{
		int[][] level = 
					{
					{4,4,4,4,4,4,4,4,4,4,4,4,4},
					{4,3,2,2,2,2,4,3,2,2,2,3,4},
					{4,2,2,2,2,2,4,2,2,2,2,2,4},
					{4,2,2,2,2,2,4,2,2,2,2,2,4},
					{4,2,2,2,2,2,5,2,2,2,2,2,4},
					{4,2,2,2,2,0,2,0,2,2,2,2,4},
					{4,4,4,4,2,0,2,0,2,4,4,4,4},
					{4,2,2,2,2,0,2,0,2,2,2,2,4},
					{4,2,2,2,2,2,2,2,2,2,2,2,4},
					{4,2,2,2,2,2,4,2,2,2,2,2,4},
					{4,2,2,2,2,2,4,2,2,2,2,2,4},
					{4,3,2,2,2,3,4,2,2,2,2,3,4},
					{4,4,4,4,4,4,4,4,4,4,4,4,4}};
		
		/*int[][] level = 
					{
					{4,4,4,4,4,4,4,4,4,4,4,4,4},
					{4,3,2,2,2,2,2,3,2,2,2,3,4},
					{4,2,2,2,2,2,2,2,2,2,2,2,4},
					{4,2,2,2,2,2,2,2,2,2,2,2,4},
					{4,2,2,2,2,2,5,2,2,2,2,2,4},
					{4,2,2,2,2,0,2,0,2,2,2,2,4},
					{4,2,2,2,2,0,2,0,2,2,2,2,4},
					{4,2,2,2,2,0,2,0,2,2,2,2,4},
					{4,2,2,2,2,2,2,2,2,2,2,2,4},
					{4,2,2,2,2,2,2,2,2,2,2,2,4},
					{4,2,2,2,2,2,2,2,2,2,2,2,4},
					{4,3,2,2,2,3,2,2,2,2,2,3,4},
					{4,4,4,4,4,4,4,4,4,4,4,4,4}};*/
		
		return level;
	}
	
	public int getStartPositionX() 
	{
		return PlayerStartX;
	}
	
	public int getStartPositionY() 
	{
		return PlayerStartY;
	}
	
	public void setPlayerPositionX(int X) 
	{
		CurrPlayerX = X;
	}
	
	public void setPlayerPositionY(int Y) 
	{
		CurrPlayerY = Y;
	}
	
	public int getPlayerPositionX() 
	{
		return CurrPlayerX;
	}
	
	public int getPlayerPositionY() 
	{
		return CurrPlayerY;
	}
	
	public int[][] getPlayField()
	{
		return CurrMap;
	}
	
	public JFrame getGameFrame() 
	{
		return frame;
	}
	
	 public void setMap(int[][] map){
	        CurrMap = map;
	    }
	
    public int[][] getCurrentField()
    {
        int[][] Map = new int[CurrMap.length][CurrMap[0].length];
        for(int i = 0; i < CurrMap.length; i++)
        {
            System.arraycopy(CurrMap[i], 0, Map[i], 0, CurrMap[0].length);
        }
        return CurrMap;
    }
    
    public int[][] getStartField()
    { 
        int[][] SetStart = new int[StartMap.length][StartMap[0].length];
        for(int i = 0; i < StartMap.length; i++)
        {
            System.arraycopy(StartMap[i], 0, SetStart[i], 0, StartMap[0].length);
        }
        return SetStart;
    }

    public int[][] createStart()
    {
        int[][] CreateStart = new int[CurrMap.length][CurrMap[0].length];
        for(int i = 0; i < CurrMap.length; i++)
        {
            System.arraycopy(CurrMap[i], 0, CreateStart[i], 0, CurrMap[0].length);
        }
        return CreateStart;
    }
	
	public void startUp() 
	{
		frame.setPreferredSize(new Dimension(790,820));
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(Gimg,BorderLayout.CENTER);
        	
       this.CurrMap = getMapLevel();
		CurrMap = createStart();
		StartMap = createStart();
		
		PlayerStartX = 4;
		PlayerStartY = 6;
		CurrPlayerX = PlayerStartX;
		CurrPlayerY = PlayerStartY;
		
		frame.pack();
		frame.setTitle("そうこばん");
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	public static void main(String[] args) 
	{
		GameLogic logic = new GameLogic();
		logic.addObserver(Gimg);
		logic.NotifyObservers(logic.getCurrentField(), "Game Start");
		new Controller(logic);
	}
}
