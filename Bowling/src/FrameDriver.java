
public class FrameDriver {
	private BowlingDisplay display;
	
	Frame[] frames = new Frame[10];
	
	public FrameDriver(BowlingDisplay display) {
		this.display = display;
	}

	public void performRoll1(int frameNum, int pinsHit) {
		Frame curFrame = frames[frameNum] = new Frame();
		curFrame.performRoll1(pinsHit);
		
		display.setRoll1(frameNum, pinsHit);
		
		if (frameNum > 0 && frames[frameNum-1].isSpare()) {
			Frame prevFrame = frames[frameNum-1];
			prevFrame.addPinsToScore(prevFrame.getScore() + pinsHit);
			display.setScore(frameNum-1, getTotalScore(frameNum-1));
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
		display.setScore(frameNum, getTotalScore(frameNum));
	}
	
	private int getTotalScore(int frameNum) {
		int total = 0;
		for (int i = 0 ; i <= frameNum ; i++) {
			total += frames[i].getScore();
		}
		return total;
	}

}
