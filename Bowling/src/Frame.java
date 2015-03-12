
public class Frame {
	private int firstRoll = 0;
	private int secondRoll = 0;
	Frame nextFrame = null;
	
	public void performRoll1(int pinsHit) {
		firstRoll = pinsHit;
	}
	
	public void performRoll2(int pinsHit) {
		secondRoll = pinsHit;
	}
	
	public int getFirstRoll() {
		return firstRoll;
	}

	public int getSecondRoll() {
		return secondRoll;
	}
	
	public int getScore() {
		if (nextFrame == null) {
			return getBaseScore();
		} else if (isStrike()) {
			return getBaseScore() + nextFrame.getFirstRoll() + nextFrame.getSecondRoll();
		} else if (isSpare()) {
			return getBaseScore() + nextFrame.getFirstRoll();
		} else {
			return getBaseScore();
		}
	}

	private int getBaseScore() {
		return firstRoll + secondRoll;
	}
	
	public boolean isSpare() {
		return getBaseScore() == 10;
	}
	
	public boolean isStrike() {
		return firstRoll == 10;
	}

	public void setNextFrame(Frame frame) {
		nextFrame = frame;
	}
}
