package julianwi.awtpeer;

import java.awt.font.LineMetrics;

public class FreetypeLineMetrics extends LineMetrics {

	@Override
	public float getAscent() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return 0;
	}

	@Override
	public int getBaselineIndex() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return 0;
	}

	@Override
	public float[] getBaselineOffsets() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public float getDescent() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return 0;
	}

	@Override
	public float getHeight() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return 0;
	}

	@Override
	public float getLeading() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return 0;
	}

	@Override
	public int getNumChars() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return 0;
	}

	@Override
	public float getStrikethroughOffset() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return 0;
	}

	@Override
	public float getStrikethroughThickness() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return 0;
	}

	@Override
	public float getUnderlineOffset() {
		return 10;
	}

	@Override
	public float getUnderlineThickness() {
		//TODO implement this
		return 10;
	}

}
