import static org.junit.Assert.*;

import org.junit.Test;


public class BowlingTest {

	private static final int ROLL_2 = 4;
	private static final int ROLL_1 = 1;

	@Test
	public void frame_with_no_mark_is_sum_of_rolls() {
		MockedBowlingDisplay display = new MockedBowlingDisplay();
		
		FrameDriver frame = new FrameDriver(display);
		
		frame.performRoll1(0, ROLL_1);
		frame.performRoll2(0, ROLL_2);
		
		int expected = ROLL_1 + ROLL_2; 
		
		assertEquals(expected, display.getScore(0));
		
	}

}
