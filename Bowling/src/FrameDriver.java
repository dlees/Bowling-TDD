
public class FrameDriver {
	private BowlingDisplay display;
	
	Frame[] frames = new Frame[10];
	int score = 0;
	
	public FrameDriver(BowlingDisplay display) {
		this.display = display;
	}

	public void performRoll1(int frameNum, int pinsHit) {
		Frame curFrame = frames[frameNum] = new Frame(score);
		curFrame.performRoll1(pinsHit);
		
		display.setRoll1(frameNum, pinsHit);
		
		if (frameNum > 0 && frames[frameNum-1].isSpare()) {
			Frame prevFrame = frames[frameNum-1];
			prevFrame.addPinsToScore(prevFrame.getScore() + pinsHit);
			display.setScore(frameNum-1, prevFrame.getScore());
		}
		
		if (pinsHit == 10) {
			display.setMark(frameNum, BowlingDisplay.STRIKE);			
		}
		
	}

	public void performRoll2(int frameNum, int pinsHit) {
		Frame curFrame = frames[frameNum];
		curFrame.performRoll2(pinsHit);
		
		if (curFrame.isSpare()) {
			display.setMark(frameNum, BowlingDisplay.SPARE);
		}
		
		display.setRoll2(frameNum, pinsHit);
		display.setScore(frameNum, curFrame.getScore());
		score = curFrame.getScore();
	}

}
