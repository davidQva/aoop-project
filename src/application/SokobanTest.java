package application;
import static org.junit.Assert.*;

import framework.*;
import org.junit.Test;

public class SokobanTest {
	
	@Test
	public void testGameInitAndCrateStuckGameOverThenReset() {
		
		
		SokobanGame soko =  new SokobanGame(13, 13, 50);
		
		/*
		 * Check start position
		 * */
		assertEquals(soko.getPlayerPositionX(),6);
		assertEquals(soko.getPlayerPositionY(),6);
		
		/*
		 * Check if the player will stand in the tile above the crate
		 * when the crate have been pushed against the wall of map 1.
		 * */
		for (int i = 0; i < 100; i++) {
			soko.input(StateAndDirection.DOWN);
		}
		assertEquals(soko.getPlayerPositionX(),6);
		assertEquals(soko.getPlayerPositionY(),10);
		
		/*
		 * Move player one step to the left and check position
		 * */
		soko.input(StateAndDirection.LEFT);
		assertEquals(soko.getPlayerPositionX(),5);
		assertEquals(soko.getPlayerPositionY(),10);
		
		/*
		 * Move player down to make the player stand to the left tile
		 * from the crate and check that the player won't clip through the wall 
		 * */
		for (int i = 0; i < 100; i++) {
			soko.input(StateAndDirection.DOWN);
		}
		assertEquals(soko.getPlayerPositionX(),5);
		assertEquals(soko.getPlayerPositionY(),11);
		
		/*
		 * Push the crate against the wall and check if the game is over.
		 * */
		for (int i = 0; i < 5; i++) {
			soko.input(StateAndDirection.RIGHT);
			if(soko.getPlayerPositionX() == 10)
			assertTrue(soko.checkGameOver());
		}
		
		/*
		 * Check if the map have been reset and the player is
		 * in the start position of map 1.
		 */
		assertEquals(soko.getPlayerPositionX(),6);
		assertEquals(soko.getPlayerPositionY(),6);
	
	}
	
	@Test
	public void testGameWonAndIfTheGameLoadedTheSecondMap() {
		
		SokobanGame soko =  new SokobanGame(13, 13, 50);
		
		/*
		 * Check start position
		 * */
		assertEquals(soko.getPlayerPositionX(),6);
		assertEquals(soko.getPlayerPositionY(),6);
		
		/*
		 * Step to the left
		 * */
		soko.input(StateAndDirection.LEFT);
		assertEquals(soko.getPlayerPositionX(),5);
		assertEquals(soko.getPlayerPositionY(),6);
		
		/*
		 * Step down 3 times to stand to the left of the crate
		 * */
		for (int i = 0; i < 3; i++) {
			soko.input(StateAndDirection.DOWN);
		}
		assertEquals(soko.getPlayerPositionX(),5);
		assertEquals(soko.getPlayerPositionY(),9);
		
		/*
		 * Move the crate 3 steps to the left onto the marked spot.
		 * then check if the game is won.
		 * */
		for (int i = 0; i < 3; i++) {
			soko.input(StateAndDirection.RIGHT);
			if(soko.getPlayerPositionX() == 8)
			assertTrue(soko.checkWin());
		}
		
		/*
		 * Check if player is in the start position after game has been won
		 * */
		assertEquals(soko.getPlayerPositionX(),6);
		assertEquals(soko.getPlayerPositionY(),6);
		
		/*
		 * Move player to the left and check if it only moved two steps from
		 * start position, then it means that the second map has loaded after 
		 * winning the first map.
		 * */
		for (int i = 0; i < 100; i++) {
			soko.input(StateAndDirection.LEFT);
		}
		
		/*
		 * If player is standing in front of the wall in in level 2,
		 * then the position of the player should be Col = 4, Row = 6.
		 * */
		assertEquals(soko.getPlayerPositionX(),4);
		assertEquals(soko.getPlayerPositionY(),6);
		
	}
	
	/*
	 * method to check if the player clips through the walls or if the game is over
	 * */
	public boolean checkgamoverandclip(SokobanGame soko) 
	{
		if (soko.checkGameOver()
				|| soko.getPlayerPositionX() <= 0 || soko.getPlayerPositionX() >= soko.getBoard()[0].length-1
				|| soko.getPlayerPositionY() <= 0 || soko.getPlayerPositionY() >= soko.getBoard()[0].length-1);
		return true;
	}
	
	
	/*
	 * Make the player move randomly and count the number of times the game was won and lost.
	 * */
	@Test
	public void testHighNumberORandomMovement() {
		
		SokobanGame soko =  new SokobanGame(13, 13, 50);
		
		int lost = 0;
		int won = 0;
		int moves = 0;
		
		for (int i = 0; i < 5000000; i++) 
		{
			double random = Math.random();
			
			if(random >= 0 && random <= 0.249) {
					soko.input(StateAndDirection.UP);
					if(soko.checkWin()) 
						won++;
					if(checkgamoverandclip(soko));
						lost++;
			}
			if(random >= 0.250 && random <= 0.499) {
					soko.input(StateAndDirection.DOWN);
					if(soko.checkWin()) 
						won++;
					if(checkgamoverandclip(soko));
						lost++;
			}
			if(random >= 0.500 && random <= 0.749) {
					soko.input(StateAndDirection.LEFT);
					if(soko.checkWin()) 
						won++;
					if(checkgamoverandclip(soko));
						lost++;
			}
			if(random >= 0.750 && random < 1) {
					soko.input(StateAndDirection.RIGHT);
					if(soko.checkWin()) 
						won++;
					if(checkgamoverandclip(soko));
						lost++;
			}
			moves++;
//			System.out.print(soko.getPlayerPositionX() + " " +  soko.getPlayerPositionY());
//			System.out.println();
		}
		
//		System.out.println(soko.getPlayerPositionX());
//		System.out.println(soko.getPlayerPositionY());
//		System.out.println();
		
		System.out.println("Game won: " + won + " times");
		System.out.println("Game lost: " + lost + " times");
		System.out.println("Random moves made: " + moves + " times");
		
		
	}
	
	
	
}
