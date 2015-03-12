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
		frame.performRoll2(1, FRAME_1_ROLL_2);

		int expected = 10 + FRAME_1_ROLL_1 + FRAME_1_ROLL_2; 		
		assertEquals(expected, display.getScore(0));	
		
		expected += FRAME_1_ROLL_1 + FRAME_1_ROLL_2; 
		assertEquals(expected, display.getScore(1));		
	}
	
	@Test
	public void a_spare_after_a_strike_makes_strike_20() {
		MockedBowlingDisplay display = new MockedBowlingDisplay();
		
		FrameDriver frame = new FrameDriver(display);

		frame.performRoll1(0, STRIKE_ROLL);
		frame.performRoll1(1, SPARE_FRAME_ROLL_1);
		frame.performRoll2(1, SPARE_FRAME_ROLL_2);
		frame.performRoll1(2, FRAME_1_ROLL_1);
		frame.performRoll2(2, FRAME_1_ROLL_2);

		int expected = 20; 		
		assertEquals(expected, display.getScore(0));
		
		expected += SPARE_FRAME_ROLL_1 + SPARE_FRAME_ROLL_2 + FRAME_1_ROLL_1; 
		assertEquals(expected, display.getScore(1));		
		
		expected += FRAME_1_ROLL_1 + FRAME_1_ROLL_2; 
		assertEquals(expected, display.getScore(2));		
	}	
	
	@Test
	public void strike_score_counts_strike_as_1_roll() {
		MockedBowlingDisplay display = new MockedBowlingDisplay();
		
		FrameDriver frame = new FrameDriver(display);

		frame.performRoll1(0, STRIKE_ROLL);
		frame.performRoll1(1, STRIKE_ROLL);
		frame.performRoll1(2, FRAME_1_ROLL_1);
		frame.performRoll2(2, FRAME_1_ROLL_2);

		int expected = 20 + FRAME_1_ROLL_1 ; 		
		assertEquals(expected, display.getScore(0));
		
		expected += STRIKE_ROLL + FRAME_1_ROLL_1 + FRAME_1_ROLL_2; 
		assertEquals(expected, display.getScore(1));	
		
		expected += FRAME_1_ROLL_1 + FRAME_1_ROLL_2; 
		assertEquals(expected, display.getScore(2));		
	}
	
	@Test
	public void turkey_is_30_points() {
		MockedBowlingDisplay display = new MockedBowlingDisplay();
		
		FrameDriver frame = new FrameDriver(display);

		frame.performRoll1(0, STRIKE_ROLL);
		frame.performRoll1(1, STRIKE_ROLL);
		frame.performRoll1(2, STRIKE_ROLL);
		frame.performRoll1(3, FRAME_1_ROLL_1);
		frame.performRoll2(3, FRAME_1_ROLL_2);

		int expected = STRIKE_ROLL + STRIKE_ROLL + STRIKE_ROLL ; 		
		assertEquals(expected, display.getScore(0));
		
		expected += STRIKE_ROLL + STRIKE_ROLL + FRAME_1_ROLL_1; 
		assertEquals(expected, display.getScore(1));
		
		expected += STRIKE_ROLL + FRAME_1_ROLL_1 + FRAME_1_ROLL_2; 
		assertEquals(expected, display.getScore(2));		
		
		expected += FRAME_1_ROLL_1 + FRAME_1_ROLL_2; 
		assertEquals(expected, display.getScore(3));		
	}	
	
	@Test
	public void tenth_frame_ends_after_roll_2_with_no_mark() {
		MockedBowlingDisplay display = new MockedBowlingDisplay();
		
		FrameDriver frame = new FrameDriver(display);

		getToTenth(frame);
		frame.performRoll1(9, FRAME_1_ROLL_1);
		frame.performRoll2(9, FRAME_1_ROLL_2);

		assertEquals(0, display.getFinalRoll());
	}

	@Test
	public void tenth_frame_needs_final_roll_if_spare() {
		MockedBowlingDisplay display = new MockedBowlingDisplay();
		
		FrameDriver frame = new FrameDriver(display);

		getToTenth(frame);
		frame.performRoll1(9, SPARE_FRAME_ROLL_1);
		frame.performRoll2(9, SPARE_FRAME_ROLL_2);
		frame.performFinalRoll(STRIKE_ROLL);

		assertEquals(STRIKE_ROLL, display.getFinalRoll());

		int expected = 273;
		assertEquals(expected, display.getScore(9));

		assertEquals(BowlingDisplay.SPARE, display.getMarks(9));		
		assertEquals(BowlingDisplay.STRIKE, display.getFinalMark());
	}
	
	@Test
	public void all_strikes_is_perfect_game() {
		MockedBowlingDisplay display = new MockedBowlingDisplay();
		
		FrameDriver frame = new FrameDriver(display);

		getToTenth(frame);
		frame.performRoll1(9, STRIKE_ROLL);
		frame.performRoll2(9, STRIKE_ROLL);
		frame.performFinalRoll(STRIKE_ROLL);

		assertEquals(STRIKE_ROLL, display.getFinalRoll());

		int expected = 300;
		assertEquals(expected, display.getScore(9));
		
		int expectedMark = BowlingDisplay.STRIKE; 
		assertEquals(expectedMark, display.getMarks(9));
		assertEquals(expectedMark, display.getFinalMark());
	}
	
	@Test
	public void last_frame_is_strike_and_spare() {
		MockedBowlingDisplay display = new MockedBowlingDisplay();
		
		FrameDriver frame = new FrameDriver(display);

		getToTenth(frame);
		frame.performRoll1(9, STRIKE_ROLL);
		frame.performRoll2(9, SPARE_FRAME_ROLL_1);
		frame.performFinalRoll(SPARE_FRAME_ROLL_2);

		assertEquals(SPARE_FRAME_ROLL_2, display.getFinalRoll());

		int expected = 283;
		assertEquals(expected, display.getScore(9));
		
		int expectedMark = BowlingDisplay.STRIKE; 
		assertEquals(expectedMark, display.getMarks(9));
		assertEquals(BowlingDisplay.SPARE, display.getFinalMark());
	}
	
	private void getToTenth(FrameDriver frame) {
		frame.performRoll1(0, STRIKE_ROLL);
		frame.performRoll1(1, STRIKE_ROLL);
		frame.performRoll1(2, STRIKE_ROLL);
		frame.performRoll1(3, STRIKE_ROLL);
		frame.performRoll1(4, STRIKE_ROLL);
		frame.performRoll1(5, STRIKE_ROLL);
		frame.performRoll1(6, STRIKE_ROLL);
		frame.performRoll1(7, STRIKE_ROLL);
		frame.performRoll1(8, STRIKE_ROLL);
	}
}
