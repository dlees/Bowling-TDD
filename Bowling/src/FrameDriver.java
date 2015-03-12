
public class FrameDriver {
	private BowlingDisplay display;
	
	private int score = 0;
	private boolean last_was_spare = false;
	
	public FrameDriver(BowlingDisplay display) {
		this.display = display;
	}

	public void performRoll1(int frameNum, int pinsHit) {
		score += pinsHit;
		display.setRoll1(frameNum, pinsHit);
		
		if (last_was_spare) {
			display.setScore(frameNum-1, score);
			score += pinsHit;
		}
		
	}

	public void performRoll2(int frameNum, int pinsHit) {		
		score += pinsHit;
		
		if (score == 10) {
			display.setMark(frameNum, BowlingDisplay.SPARE);
			last_was_spare = true;
		}
		
		display.setRoll2(frameNum, pinsHit);
		display.setScore(frameNum, score);	
	}

}
