package application;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GameManager {   

    //not used right now
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
    
    public int[][] fileLevelScan(String filePath, ArrayList pointArr, int col, int row) 
    {
    	int[][] array = new int[col][row];
    	String filepath = filePath;
    	
		try {
			//InputStream is = getClass().getResourceAsStream(filepath);
			BufferedReader br = new BufferedReader(new FileReader(filepath));
			int coll = 0;
			int roww = 0;
			while(coll < col && roww < row) 
			{
				String line = br.readLine();
				
					while(roww < row) 
				{
					String numbers[] = line.split(" ");
					int num = Integer.parseInt(numbers[roww]);
					
					array[coll][roww] = num;
					
					if(num == 3) {
						Point point = new Point(roww,coll);
						pointArr.add(point);
					}                   
					roww++;
				}
					roww = 0;
					coll++;
			}
			br.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		return array;
    }
   
}

