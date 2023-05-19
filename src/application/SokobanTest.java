package application;

import framework.*;
import framework.GameStateAndDirection;

import static org.junit.Assert.*;
import org.junit.Test;


public class SokobanTest {


    @Test
    public void testSokoban() {     

        SokobanGame game = new SokobanGame(13, 13, 50);
/* 
        for(int i = 0; i < 13; i++) {
            for(int j = 0; j < 13; j++) {

                if(game.getValue(i,j) == 5) {  

                    assertEquals(game.getPlayerPositionX(), i);
                    assertEquals(game.getPlayerPositionY(), j);    

                }               

            }
        } */
    }
    
}
