package game;

import java.util.ArrayList;

public class GameLogic extends Game{

	private static final long serialVersionUID = 1L;

	private Boolean Running = false;
	private Boolean Won = false;
	private int Pause = 0;
	private final ArrayList<GameObserver> observer = new ArrayList<GameObserver>();
	 
	/*
	 * Variables based on the values of the game objects to easier keep track during
	 * game state.
	 * */
	private int CRATE = 0;
	private int CRATE_MARKED = 1;
	private int GROUND = 2;
	private int GROUND_MARKED = 3;
	private int WALL = 4;
	private int PLAYER = 5;
	private int TEMP_PLAYER = 6;
	
	public GameLogic() 
	{
		addObserver(new GameMonitor());
		Running = true;
	}
	
	/*
	 * Adds the observer to the arraylist.
	 * */
	public void addObserver(GameObserver observer) 
	{
		this.observer.add(observer);
	}
	
	public boolean getWon() 
	{
		return Won;
	}
	
	/*
	 * By pressing the keyboard button "P" the game will change the game to running = false
	 * and pause to a binary "true", if the "P" button is pressed while in pause, the the game
	 * will be unpaused.
	 * */
	
	 @Override
	    public void PressedReset() 
	 	{ 
            Won = false;
	        
            setPlayerPositionX(getStartPositionX());
	        setPlayerPositionY(getStartPositionY());
	        
	        setMap(getStartField());
	        NotifyObservers(getPlayField(), "Game Reset"); 
	    }
	
	@Override
	public void PressedPause() 
	{
		switch(Pause) 
		{
			case 0:
				Pause = 1;
				NotifyObservers(getPlayField(), "Game Paused");
				Running = false;
				break;
			case 1:
				Pause = 0;
				NotifyObservers(getPlayField(), "Game UnPaused");
				Running = true;
				break;
		}	
	}
	
	
	/*
	 * If the Controller has been pressed then the methods below will execute by first checking
	 * if the game is running, then if the game has been won, if it has it will return.
	 * If the game is still running and has not been won yet based on the GameWon method
	 * then it will notify the observer of the last movement on the map.
	 * and check if the movement for one tile ahead of player is a collision and if 
	 * the movement for one tile ahead of a crate is a collision or a marked tile.(based on the )
	 * */
	@Override
	public void PressedUp() 
	{
		if(Running) 
		{
			if(Won) 
			{
				return;
			}
			Scan(getPlayerPositionX(), getPlayerPositionY(), -1, 0, -2, 0);
			NotifyObservers(getPlayField(), "Up");
			GameWon();
			if(Won) 
			{
				NotifyObservers(getPlayField(), "Game Won");
			}
		}
	}

	@Override
	public void PressedDown() 
	{
		if(Running) 
		{
			if(Won) 
			{
				return;
			}
			Scan(getPlayerPositionX(), getPlayerPositionY(), 1, 0, 2, 0);
			NotifyObservers(getPlayField(), "Down");
			GameWon();
			if(Won) 
			{
				NotifyObservers(getPlayField(), "Game Won");
			}
		}
	}

	@Override
	public void PressedLeft() 
	{
		if(Running) 
		{
			if(Won) 
			{
				return;
			}
			Scan(getPlayerPositionX(), getPlayerPositionY(), 0, -1, 0, -2);
			NotifyObservers(getPlayField(), "Left");
			GameWon();
			if(Won) 
			{
				NotifyObservers(getPlayField(), "Game Won");
			}
		}
	}

	@Override
	public void PressedRight() 
	{
		if(Running) 
		{
			if(Won) 
			{
				return;
			}
			Scan(getPlayerPositionX(), getPlayerPositionY(), 0, 1, 0, 2);
			NotifyObservers(getPlayField(), "Right");
			GameWon();
			if(Won) 
			{
			NotifyObservers(getPlayField(), "Game Won");
			}
		}
	}
	
	
	/*
	 *This method checks the map is there are any collisions based on moving 1 tile and
	 *then updates the position of the player and the ground which has been traversed.
	 *the method also check if a crate and a marked crate will collide with another crate or wall
	 *in front of it, by checking the next coordinate in succession to
	 *the direction which the player has chosen to move.
	 *If the direction of the moved crate is a marked spot, then it will update the image to a marked crate.
	 * */
	public void Scan(int tryX, int tryY, int x_1, int y_1, int x_2, int y_2) {
		
	if(Running) 
	{
		
        int[][] map = getPlayField();
        
        if (map[tryX + x_1][tryY + y_1] == WALL) 
        {
            return;
        }
        if (map[tryX + x_1][tryY + y_1] == GROUND)
        { 
        	map[tryX + x_1][tryY + y_1] = PLAYER;
            setPlayerPositionX(tryX + x_1);
            setPlayerPositionY(tryY + y_1);
            
            if (map[tryX][tryY] == PLAYER) 
            { 
            	map[tryX][tryY] = GROUND;
            } 
            else 
            {
            	map[tryX][tryY] = GROUND_MARKED;
            }
            setMap(map);
            return;
        }

        if (map[tryX + x_1][tryY + y_1] == CRATE || map[tryX + x_1][tryY + y_1] == CRATE_MARKED) //checks if next block is a crate
        { 
            if (map[tryX + x_2][tryY + y_2] == WALL || map[tryX + x_2][tryY + y_2] == CRATE || map[tryX + x_2][tryY + y_2] == CRATE_MARKED) //checks if the block after the crate is another crate or a wall 
            {
                return; 
            }
            
    	   if (map[tryX + x_2][tryY + y_2] == GROUND)
            {
            	map[tryX + x_2][tryY + y_2] = CRATE;
            	
                if (map[tryX + x_1][tryY + y_1] == CRATE) 
                {
                	map[tryX + x_1][tryY + y_1] = PLAYER;
                	setPlayerPositionX(tryX + x_1);
                	setPlayerPositionY(tryY + y_1);
                } 
                else 
                {
                	map[tryX + x_1][tryY + y_1] = TEMP_PLAYER;
                	setPlayerPositionX(tryX + x_1);
                	setPlayerPositionY(tryY + y_1);
                }
                
                if (map[tryX][tryY] == PLAYER) 
                { 
                	map[tryX][tryY] = GROUND;
                } 
                else 
                {
                	map[tryX][tryY] = GROUND_MARKED;
                }
                setMap(map);
                return;
            }
            
            if (map[tryX + x_2][tryY + y_2] == GROUND_MARKED) 
            { 
            	map[tryX + x_2][tryY + y_2] = CRATE_MARKED;
                if (map[tryX + x_1][tryY + y_1] == CRATE)
                { 
                	map[tryX + x_1][tryY + y_1] = PLAYER;
                	setPlayerPositionX(tryX + x_1);
                	setPlayerPositionY(tryY + y_1);
                } 
                else 
                {
                	map[tryX + x_1][tryY + y_1] = TEMP_PLAYER;
                	setPlayerPositionX(tryX + x_1);
                	setPlayerPositionY(tryY + y_1);
                }
                if (map[tryX][tryY] == PLAYER)
                { 
                	map[tryX][tryY] = GROUND;
                } 
                else 
                {
                	map[tryX][tryY] = GROUND_MARKED;
                }
                setMap(map);
                return;
            }
        }
        if (map[tryX + x_1][tryY + y_1] == GROUND_MARKED) 
        	{ 
        		map[tryX + x_1][tryY + y_1] = TEMP_PLAYER;
        		setPlayerPositionX(tryX + x_1);
        		setPlayerPositionY(tryY + y_1);
        		
        	if (map[tryX][tryY] == PLAYER) 
            {
            	map[tryX][tryY] = GROUND;
            } 
            else 
            {
            	map[tryX][tryY] = GROUND_MARKED;
            }
            	setMap(map);
        	}
		}
    }
	
	/*
	 * This method checks the gameMap for marked areas and crates which increment by 1 when found.
	 * the function then checks if the number of marked areas and the number of unmarked crates are equal to 0, 
	 * if the marked areas have been covered and the crates have been marked then the level has been completed.
	 * */
	public void GameWon() 
	{
		int groundmark = 0;
		int crates = 0;
		
        for (int i = 0; i < 13; i++) 
        {
            for (int j = 0; j < 13; j++) 
            { 
                if (CurrMap[i][j] == GROUND_MARKED)
                { 
                    groundmark++;
                    
                }
                if (CurrMap[i][j] == CRATE)
                	crates++;
            }
       	}
        
        if (groundmark == 0 && crates == 0) // if there are no crates and no groundmarks left, win !
        { 
        		Won = true;
        }
    }
	
	/*
	 * This method updates the values of the observer to keep track of the current map state.
	 * */
	public void NotifyObservers(int[][] field, String update) 
	{
		 
			for (GameObserver go : observer) 
			{
				go.UpdateField(field, update);
			}
	}
}
