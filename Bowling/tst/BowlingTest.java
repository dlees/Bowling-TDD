import static org.junit.Assert.*;

import org.junit.Test;


public class BowlingTest {

	private static final int SPARE_FRAME_ROLL_2 = 7;
	private static final int SPARE_FRAME_ROLL_1 = 3;
	private static final int FRAME_1_ROLL_2 = 5;
	private static final int FRAME_1_ROLL_1 = 4;
	private static final int FRAME_0_ROLL_2 = 4;
	private static final int FRAME_0_ROLL_1 = 1;

	@Test
	public void frame_with_no_mark_is_sum_of_rolls() {
		MockedBowlingDisplay display = new MockedBowlingDisplay();
		
		FrameDriver frame = new FrameDriver(display);
		
		frame.performRoll1(0, FRAME_0_ROLL_1);
		frame.performRoll2(0, FRAME_0_ROLL_2);
		
		int expected = FRAME_0_ROLL_1 + FRAME_0_ROLL_2; 
		
		assertEquals(expected, display.getScore(0));		
	}

	@Test
	public void frames_score_is_calculated_based_on_previous_score() {
		MockedBowlingDisplay display = new MockedBowlingDisplay();
		
		FrameDriver frame = new FrameDriver(display);
		
		frame.performRoll1(0, FRAME_0_ROLL_1);
		frame.performRoll2(0, FRAME_0_ROLL_2);
		frame.performRoll1(1, FRAME_1_ROLL_1);
		frame.performRoll2(1, FRAME_1_ROLL_2);
		
		int expected = FRAME_0_ROLL_1 + FRAME_0_ROLL_2 + FRAME_1_ROLL_1 + FRAME_1_ROLL_2; 
		
		assertEquals(expected, display.getScore(1));		
	}
	
	@Test
	public void a_frame_is_marked_spare_if_second_roll_is_10() {
		MockedBowlingDisplay display = new MockedBowlingDisplay();
		
		FrameDriver frame = new FrameDriver(display);
		
		frame.performRoll1(0, SPARE_FRAME_ROLL_1);
		frame.performRoll2(0, SPARE_FRAME_ROLL_2);

		int expected = BowlingDisplay.SPARE; 
		
		assertEquals(expected, display.getMarks(0));		
	}
	

}
