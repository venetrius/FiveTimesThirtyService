package testftt.logic;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ftt.logic.State;

public class TestState {


	@Test
	public void youShallPass() {
		assertEquals(2, 2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testFailWithNotANumberToken() {
	    State state = new State("1_all");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testFailWithEvenToken() {
	    State state = new State("1_2");
	}
	
	@Test
	public void testValidInput() {
		State state = new State("2_2_3_4_5");
		assertEquals(2, state.getRoundNumber());
		assertEquals(0, state.getAiPoints());
		assertEquals(2, state.getPlayerPoints());
		assertEquals(6, state.getAiSum());
		assertArrayEquals(new int[] {2, 4}, state.getAiBetHistory());
		assertArrayEquals(new int[] {3, 5}, state.getpBetHistory());
	}
	
}
