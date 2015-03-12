import static org.junit.Assert.*;

import org.junit.Test;


public class BowlingTest {

	private static final int STRIKE_ROLL = 10;
	private static final int SPARE_FRAME_ROLL_1 = 3;
	private static final int SPARE_FRAME_ROLL_2 = 7;
	private static final int FRAME_0_ROLL_1 = 1;
	private static final int FRAME_0_ROLL_2 = 4;
	private static final int FRAME_1_ROLL_1 = 4;
	private static final int FRAME_1_ROLL_2 = 5;

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
	public void frames_score_is_calculated_based_on_previous_frames() {
		MockedBowlingDisplay display = new MockedBowlingDisplay();
		
		FrameDriver frame = new FrameDriver(display);

		frame.performRoll1(0, SPARE_FRAME_ROLL_1);
		frame.performRoll2(0, SPARE_FRAME_ROLL_2);
		frame.performRoll1(1, SPARE_FRAME_ROLL_1);
		frame.performRoll2(1, SPARE_FRAME_ROLL_2);
		frame.performRoll1(2, FRAME_0_ROLL_1);
		frame.performRoll2(2, FRAME_0_ROLL_2);
		frame.performRoll1(3, FRAME_1_ROLL_1);
		frame.performRoll2(3, FRAME_1_ROLL_2);
		
		int expected = SPARE_FRAME_ROLL_1 + SPARE_FRAME_ROLL_2 + SPARE_FRAME_ROLL_1; 		
		assertEquals(expected, display.getScore(0));
		
		expected += SPARE_FRAME_ROLL_2 + SPARE_FRAME_ROLL_1 + FRAME_0_ROLL_1;
		assertEquals(expected, display.getScore(1));	
		
		expected += FRAME_0_ROLL_1 + FRAME_0_ROLL_2;
		assertEquals(expected, display.getScore(2));
		
		expected += FRAME_1_ROLL_1 + FRAME_1_ROLL_2;
		assertEquals(expected, display.getScore(3));	
	}
	
	@Test
	public void a_frame_is_marked_spare_if_frame_total_is_10() {
		MockedBowlingDisplay display = new MockedBowlingDisplay();
		
		FrameDriver frame = new FrameDriver(display);
		
		frame.performRoll1(0, SPARE_FRAME_ROLL_1);
		frame.performRoll2(0, SPARE_FRAME_ROLL_2);

		int expected = BowlingDisplay.SPARE; 
		
		assertEquals(expected, display.getMarks(0));		
	}

	@Test
	public void a_frame_is_not_marked_frame_score_is_not_10() {
		MockedBowlingDisplay display = new MockedBowlingDisplay();
		
		FrameDriver frame = new FrameDriver(display);
		
		frame.performRoll1(0, FRAME_0_ROLL_1);
		frame.performRoll2(0, FRAME_0_ROLL_2);

		int expected = 0; 
		
		assertEquals(expected, display.getMarks(0));		
	}
	
	@Test
	public void a_spares_value_is_10_plus_the_first_roll_of_next_frame() {
		MockedBowlingDisplay display = new MockedBowlingDisplay();
		
		FrameDriver frame = new FrameDriver(display);

		frame.performRoll1(0, SPARE_FRAME_ROLL_1);
		frame.performRoll2(0, SPARE_FRAME_ROLL_2);
		frame.performRoll1(1, FRAME_1_ROLL_1);

		int expected = 10 + FRAME_1_ROLL_1; 
		
		assertEquals(expected, display.getScore(0));		
	}
	
	@Test
	public void a_spares_value_before_a_strike_is_20() {
		MockedBowlingDisplay display = new MockedBowlingDisplay();
		
		FrameDriver frame = new FrameDriver(display);

		frame.performRoll1(0, SPARE_FRAME_ROLL_1);
		frame.performRoll2(0, SPARE_FRAME_ROLL_2);
		frame.performRoll1(1, STRIKE_ROLL);

		int expected = 20; 
		
		assertEquals(expected, display.getScore(0));		
	}

	@Test
	public void two_spares_in_a_row_are_marked_spare() {
		MockedBowlingDisplay display = new MockedBowlingDisplay();
		
		FrameDriver frame = new FrameDriver(display);
		
		frame.performRoll1(0, SPARE_FRAME_ROLL_1);
		frame.performRoll2(0, SPARE_FRAME_ROLL_2);
		frame.performRoll1(1, SPARE_FRAME_ROLL_1);
		frame.performRoll2(1, SPARE_FRAME_ROLL_2);

		int expected = BowlingDisplay.SPARE; 
		
		assertEquals(expected, display.getMarks(0));	
		assertEquals(expected, display.getMarks(1));		
	}
	
	@Test
	public void a_frame_is_marked_strike_if_first_roll_is_10() {
		MockedBowlingDisplay display = new MockedBowlingDisplay();
		
		FrameDriver frame = new FrameDriver(display);
		
		frame.performRoll1(0, STRIKE_ROLL);

		int expected = BowlingDisplay.STRIKE; 
		
		assertEquals(expected, display.getMarks(0));		
	}

	
	@Test
	public void a_strikes_value_is_10_plus_the_next_two_rolls() {
		MockedBowlingDisplay display = new MockedBowlingDisplay();
		
		FrameDriver frame = new FrameDriver(display);

		frame.performRoll1(0, STRIKE_ROLL);
		frame.performRoll1(1, FRAME_1_ROLL_1);
		frame.performRoll1(1, FRAME_1_ROLL_2);

		int expected = 10 + FRAME_1_ROLL_1 + FRAME_1_ROLL_2; 		
		assertEquals(expected, display.getScore(0));	
		
		expected += FRAME_1_ROLL_1 + FRAME_0_ROLL_2; 
		assertEquals(expected, display.getScore(1));		
	}
	
}
