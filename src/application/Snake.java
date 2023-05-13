package application;

import java.util.ArrayList;
import java.util.Random;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import framework.AbstractTileModel;
import framework.GameStateAndDirection;

public class Snake extends AbstractTileModel implements ActionListener {

	GameStateAndDirection startDirection = GameStateAndDirection.RIGHT;

	int bodyParts = 4;
	int foodConsumed;
	Point foodPos;
	boolean running = false;
	
	Timer timer;
	Random random;
	
	static final int DELAY = 100;
	int col,row,size;
	
	final int x[];
	final int y[];
	
	private ArrayList<Point> targetPositions;
	GameManager gameManager;
	
	public Snake(int col, int row, int size) {
		super(col, row, size);
	        
	    this.col = col;
	    this.row = row;
	    this.size = size;
	        
	    this.x = new int [size];
	    this.y = new int [size];
	        
	    random = new Random();
	    gameManager = new GameManager();
        targetPositions = new ArrayList<Point>();
        initializeBoard();
	    
	    startGame();
	}
    
    public void startGame() 
	{
		newFood();
		running = true;
		timer = new Timer(DELAY,this);
		timer.start();
	}
    
    private void initializeBoard() 
    {
    	int[][] board = gameManager.fileLevelScan("resources/snakemap.txt", targetPositions, col, row);
    	gameStarted = GameStateAndDirection.GAME_START;
    	Update(board, GameStateAndDirection.GAME_START);
    }
    
    public void checkFood() 
   	{
   		if((x[0] == foodPos.x) && (y[0] == foodPos.y)) 
   		{
   			bodyParts++;
   			foodConsumed++;
   			newFood();
   		}
   	}
    
    public void newFood() 
	{
		foodPos = new Point ((random.nextInt() % row),(random.nextInt() % col)); //lägg in koordinater för äpplena inom spel banan på random plats.
	}
    
    public void Update(int[][] board, GameStateAndDirection update) {
        this.board = board;
        super.update = update;

        if (gameStatus == GameStateAndDirection.GAME_WON && gameStarted == GameStateAndDirection.GAME_START) {
            System.out.println("You won!");
            gameStarted = GameStateAndDirection.GAME_OVER;
            view.win();
        } else if (gameStatus == GameStateAndDirection.GAME_OVER && gameStarted == GameStateAndDirection.GAME_START) {
            System.out.println("You lost!");
            gameStarted = GameStateAndDirection.GAME_OVER;
            // frame.lose();
        }

    }

    public void moveBody() 
    {
    	for (int i = bodyParts; i > 0; i--) 
    	{
    		x[i] = x[i-1];
    		y[i] = y[i-1];
    	}
    }
    
    public void checkCollisions() 
	{
		for (int i = bodyParts; i >0 ; i--) 
		{
			if((x[0] == x[i]) && (y[0] == y[i])) //kolla om ormen(huvud) krockar med sig själv(kroppen)
			{
				running = false;
			}
		}
		//Kollar om ormens huvud träffar vänstra kanten 
		if (x[0] < 0) 
		{
			running = false;
		}
		//Kollar om ormens huvud träffar högra kanten
		if (x[0] > row) 
		{
			running = false;
		}
		//Kollar om ormens huvud träffar toppen kanten
		if(y[0] < 0) 
		{
			running = false;
		}
		//Kollar om ormens huvud träffar nedre kanten
		if(y[0] > col) 
		{
			running = false;
		}
		
		if (running == false) // om ormen träffar sin kropp med huvudet eller passerar kanterna så ska den stanna.
		{
			timer.stop();
		}
		
	}

    @Override
    public void input(GameStateAndDirection direction) {
     
    	switch (direction) {
        case UP:
            moveUp();
            break;
        case DOWN:
            moveDown();
            break;
        case LEFT:
            moveLeft();
            break;
        case RIGHT:
            moveRight();
            break;
        case GAME_PAUSE:
            System.out.println("Game Paused");
            break;
        default:
            break;
    	}
    }
    
    @Override
    public void moveUp() {
      
    	moveBody();
        y[0] = y[0]-1;	
    }

    @Override
    public void moveDown() {
    	
    	moveBody();
    	y[0] = y[0] + 1;
       
    }

    @Override
    public void moveLeft() {
        
    	moveBody();
    	x[0] = x[0] - 1;
    }

    @Override
    public void moveRight() {
    	
    	moveBody();
    	x[0] = x[0] + 1;
    }

    @Override
    public void setBoard() {
       
        throw new UnsupportedOperationException("Unimplemented method 'setBoard'");
    }

    @Override
    public void update() 
    {
            notifyAllObservers();     
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(running) 
		{
			moveBody();
			checkFood();
			checkCollisions();
		}
	}
    
  

    
}
