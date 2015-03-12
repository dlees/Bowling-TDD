
public class Frame {
	private int firstRoll = 0;
	private int secondRoll = 0;
	private int score = 0;
	
	public Frame(int score) {
		this.score = score;
	}

	public void performRoll1(int pinsHit) {
		firstRoll = pinsHit;
		score += pinsHit;		
	}

	public void performRoll2(int pinsHit) {
		secondRoll = pinsHit;
		score += pinsHit;
	}
	
	public void addPinsToScore(int pinsHit) {
		score = pinsHit;
	}
	
	public int getScore() {
		return score;
	}
	
	public boolean isSpare() {
		return firstRoll + secondRoll == 10;
	}
	
	public boolean isStrike() {
		return firstRoll == 10;
	}

}
