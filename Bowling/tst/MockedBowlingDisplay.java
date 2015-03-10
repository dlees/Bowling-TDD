
public class MockedBowlingDisplay extends BowlingDisplay {

	int[] roll1Frames = new int[10];
	int[] roll2Frames = new int[10];;
	int[] scoreFrames = new int[10];;
	
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
		// TODO Auto-generated method stub

	}

	@Override
	public void setFinalRoll(int frameNum, int roll3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setFinalMark(int frameNum, int FinalMark) {
		// TODO Auto-generated method stub

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

}
