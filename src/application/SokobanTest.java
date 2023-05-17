package application;
import framework.AbstractTileModel;

public class SokobanTest {

    @Test
    public void testSokoban() {
        AbstractTileModel sokoban = new SokobanGame(4, 4, 10);
        
        int[][] board = {
            {1, 1, 1, 1},
            {1, 0, 0, 1},
            {1, 0, 2, 1},
            {1, 1, 1, 1}
        };

        sokoban.setBoard(board);        
        
    }
    
}
