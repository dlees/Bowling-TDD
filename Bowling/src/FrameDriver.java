
public class FrameDriver {
	private BowlingDisplay display;
	
	private int pinsHitRoll1 = 0;
	
	public FrameDriver(BowlingDisplay display) {
		this.display = display;
	}

	public void performRoll1(int frameNum, int pinsHit) {
		pinsHitRoll1 = pinsHit;
		display.setRoll1(frameNum, pinsHit);
	}

	public void performRoll2(int frameNum, int pinsHit) {
		display.setRoll2(frameNum, pinsHit);
		display.setScore(frameNum, pinsHitRoll1 + pinsHit);	
		pinsHitRoll1 = 0;
	}

}
