
public class FrameDriver {
	private BowlingDisplay display;
	private int firstRoll = 0;
	private int score = 0;
	private boolean last_was_spare = false;
	
	public FrameDriver(BowlingDisplay display) {
		this.display = display;
	}

	public void performRoll1(int frameNum, int pinsHit) {
		firstRoll = pinsHit;
		score += pinsHit;
		display.setRoll1(frameNum, pinsHit);
		
		if (last_was_spare) {
			display.setScore(frameNum-1, score);
			score += pinsHit;
			last_was_spare = false;
		}
		
		if (pinsHit == 10) {
			display.setMark(frameNum, BowlingDisplay.STRIKE);			
		}
		
	}

	public void performRoll2(int frameNum, int pinsHit) {		
		score += pinsHit;
		
		if (firstRoll + pinsHit == 10) {
			display.setMark(frameNum, BowlingDisplay.SPARE);
			last_was_spare = true;
		}
		
		display.setRoll2(frameNum, pinsHit);
		display.setScore(frameNum, score);	
	}

}
