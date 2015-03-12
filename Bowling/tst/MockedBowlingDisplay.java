
public class MockedBowlingDisplay extends BowlingDisplay {

	int[] roll1Frames = new int[10];
	int[] roll2Frames = new int[10];;
	int[] scoreFrames = new int[10];;
	int[] markFrames = new int[10];
	private int finalRoll;
	private int finalMark;
	
	@Override
	public void setRoll1(int frameNum, int roll1) {
		roll1Frames[frameNum] = roll1;
	}

	@Override
	public void setRoll2(int frameNum, int roll2) {
		roll2Frames[frameNum] = roll2;
	}

	@Override
	public void setScore(int frameNum, int score) {
		scoreFrames[frameNum] = score;
	}

	@Override
	public void setMark(int frameNum, int Mark) {
		markFrames[frameNum] = Mark;

	}

	@Override
	public void setFinalRoll(int frameNum, int roll3) {
		finalRoll = roll3;

	}

	@Override
	public void setFinalMark(int frameNum, int finalMark) {
		this.finalMark = finalMark;
	}
	
	public int getRoll1(int frame) {
		return roll1Frames[frame];
	}

	public int getRoll2(int frame) {
		return roll2Frames[frame];
	}
	
	public int getScore(int frame) {
		return scoreFrames[frame];
	}

	public int getMarks(int frame) {
		return markFrames[frame];
	}

	public int getFinalRoll() {
		// TODO Auto-generated method stub
		return finalRoll;
	}

	public int getFinalMark() {
		// TODO Auto-generated method stub
		return finalMark;
	}

}
