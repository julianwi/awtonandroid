package julianwi.awtpeer;

import java.awt.Font;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphJustificationInfo;
import java.awt.font.GlyphMetrics;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class FreetypeGlyphVector extends GlyphVector {
	
	public FreetypeGlyphVector(Font font, String string, FontRenderContext frc) {
		throw new UnsupportedOperationException("Not yet implemented.");
	}

	@Override
	public boolean equals(GlyphVector set) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return false;
	}

	@Override
	public Font getFont() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public FontRenderContext getFontRenderContext() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public int getGlyphCode(int glyphIndex) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return 0;
	}

	@Override
	public int[] getGlyphCodes(int beginGlyphIndex, int numEntries,
			int[] codeReturn) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public GlyphJustificationInfo getGlyphJustificationInfo(int glyphIndex) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public Shape getGlyphLogicalBounds(int glyphIndex) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public GlyphMetrics getGlyphMetrics(int glyphIndex) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public Shape getGlyphOutline(int glyphIndex) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public Point2D getGlyphPosition(int glyphIndex) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public float[] getGlyphPositions(int beginGlyphIndex, int numEntries,
			float[] positionReturn) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public AffineTransform getGlyphTransform(int glyphIndex) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public Shape getGlyphVisualBounds(int glyphIndex) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public Rectangle2D getLogicalBounds() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public int getNumGlyphs() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return 0;
	}

	@Override
	public Shape getOutline() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public Shape getOutline(float x, float y) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public Rectangle2D getVisualBounds() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public void performDefaultLayout() {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public void setGlyphPosition(int glyphIndex, Point2D newPos) {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public void setGlyphTransform(int glyphIndex, AffineTransform newTX) {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

}
