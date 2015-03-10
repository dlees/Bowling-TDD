
public abstract class BowlingDisplay {
	public static final int SPARE = 1;
	public static final int STRIKE = 2;
	
	public abstract void setRoll1(int frameNum, int roll1);
	public abstract void setRoll2(int frameNum, int roll2);
	public abstract void setScore(int frameNum, int roll1);
	public abstract void setMark(int frameNum, int Mark);
	public abstract void setFinalRoll(int frameNum, int roll3);
	public abstract void setFinalMark(int frameNum, int FinalMark);
}
