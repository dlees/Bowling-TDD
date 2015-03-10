
public class FrameDriver {
	private BowlingDisplay display;
	
	private int score = 0;
	
	public FrameDriver(BowlingDisplay display) {
		this.display = display;
	}

	public void performRoll1(int frameNum, int pinsHit) {
		score += pinsHit;
		display.setRoll1(frameNum, pinsHit);
	}

	public void performRoll2(int frameNum, int pinsHit) {		
		score += pinsHit;
		
		if (score == 10) {
			display.setMark(frameNum, BowlingDisplay.SPARE);
		}
		
		display.setRoll2(frameNum, pinsHit);
		display.setScore(frameNum, score);	
	}

}
