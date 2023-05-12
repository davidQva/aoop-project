package framework;

import java.io.*;

public class GameMonitor implements GameObserver, Serializable { 

	@Override
	public void updateGame(int[][] field, GameStateAndDirection update) 
	{
				
		/*
		 * Checks the state of the game.
		 * if it's initiated, won or lost.
		 * if neither is detected then the observer will print the move that has been made.
		 * */
		switch(update) 
		{
		case GAME_START ->
			System.out.println(update + "\nCurrent Game state:");
		case GAME_WON ->
			System.out.println(update + "\nGame Won");
		case GAME_OVER ->
			System.out.println(update + ("\nGame Lost"));
		case GAME_PAUSE ->
			System.out.println(update + "\nGame Paused");
		case GAME_UNPAUSE ->
			System.out.println(update + "\nGame UnPaused");
		case GAME_RESTART ->
			System.out.println(update + "\nGame Reset");
		default ->
			System.out.println("Moved : " + update + "\n Current Game state");
		}
		
		/*
		 * This functions recreates the current map as a string to keep track of the gamestate
		 * */
		StringBuilder CurrentGameState = new StringBuilder();
		for (int[] is : field) 
		{
			for (int j = 0; j < field[0].length; j++) 
			{
				CurrentGameState.append(is[j]).append(", ");
			}
			CurrentGameState.append("\n");
		}
		System.out.println(CurrentGameState);
	
		logPrinter(field,update);
		//start = 0;
		
	}
	
	public void logPrinter(int [][] field, GameStateAndDirection update) 
	 
	{
		int[][] numbers = field;
		String fileName = "MostRecentGameLog.txt";
		File outputFile = new File(fileName);

		/* if(outputFile.exists() && start == 1)
			outputFile.delete();
		 */
		
		try 
		{	
			FileWriter fw = new FileWriter(outputFile, true);
			BufferedWriter bw = new BufferedWriter(fw);
			for (int k = 0; k < numbers.length; k++) 
			{
				for (int k2 = 0; k2 < numbers[0].length; k2++) 
				{
					bw.write(Integer.toString(numbers[k][k2]));	
					bw.write(", ");
				}
				bw.newLine();
			}
			bw.write(update.toString());
			bw.newLine();
			bw.close();
			fw.close();
			
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	
}