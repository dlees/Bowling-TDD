
public class FrameDriver {
	private BowlingDisplay display;
	
	Frame[] frames = new Frame[10];
	
	public FrameDriver(BowlingDisplay display) {
		this.display = display;
	}

	public void performRoll1(int frameNum, int pinsHit) {
		Frame curFrame = frames[frameNum] = new Frame();
		
		if (frameNum > 0) {
			frames[frameNum-1].setNextFrame(curFrame);
		}
		
		curFrame.performRoll1(pinsHit);
		
		display.setRoll1(frameNum, pinsHit);
		
		if (curFrame.isStrike()) {
			display.setMark(frameNum, BowlingDisplay.STRIKE);			
		}		
		
		updateDisplayScores(frameNum);
	}

	public void performRoll2(int frameNum, int pinsHit) {
		Frame curFrame = frames[frameNum];
		curFrame.performRoll2(pinsHit);
		
		if (curFrame.isSpare()) {
			display.setMark(frameNum, BowlingDisplay.SPARE);
		}

		display.setRoll2(frameNum, pinsHit);
		
		updateDisplayScores(frameNum);
	}
	
	private int updateDisplayScores(int frameNum) {
		int total = 0;
		for (int i = 0 ; i <= frameNum ; i++) {
			total += frames[i].getScore();
			display.setScore(i, total);
		}
		return total;
	}

	public void performFinalRoll(int pinsHit) {
		Frame curFrame = frames[9];

		curFrame.performRoll3(pinsHit);

		display.setFinalMark(9, curFrame.getFinalMark());
		
		display.setFinalRoll(9, pinsHit);
		updateDisplayScores(9);		
	}

}
