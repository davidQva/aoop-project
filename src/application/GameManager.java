package application;

import javax.sound.sampled.*;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class GameManager {
    private int level;
    private boolean isGameOver;
    // Other game-related variables

    public GameManager() {
    	level = 1;
        isGameOver = false; 
        // Other initialization logic
    }

    public int getLevel() {
        return level;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void checkGameState() {
        // Implement the logic to check the game state
        // For example, check if the player has completed all levels or if the game is over
        if (level == 10) {
            System.out.println("Congratulations! You've completed all levels!");
            // Set the game over flag
            isGameOver = true;
        }
    }

    public void scanLevel(int[][] level, ArrayList ponterArr){

       
        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level[0].length; j++){
                if (level[i][j] == 3)
                {
                    Point pointer = new Point(j, i);
                    ponterArr.add(pointer);
                }
            }
        }


    }
    
    public int[][] fileLevelScan(String filePath, ArrayList pointArr) 
    {
    	int[][] array = new int[13][13];
    	String filepath = filePath;
    	
		try {
			//InputStream is = getClass().getResourceAsStream(filepath);
			BufferedReader br = new BufferedReader(new FileReader(filepath));
			int col = 0;
			int row = 0;
			while(col < 13 && row < 13) 
			{
				String line = br.readLine();
				
					while(row < 13) 
				{
					String numbers[] = line.split(" ");
					int num = Integer.parseInt(numbers[row]);
					
					array[col][row] = num;
					
					if(num == 3) {
						Point point = new Point(row,col);
						pointArr.add(point);
					}
					row++;
				}
					row = 0;
					col++;
			}
			br.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		return array;
    }
   
}

