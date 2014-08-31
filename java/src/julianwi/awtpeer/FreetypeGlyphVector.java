package julianwi.awtpeer;

import gnu.java.awt.peer.gtk.GdkFontPeer;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphJustificationInfo;
import java.awt.font.GlyphMetrics;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.text.CharacterIterator;

public class FreetypeGlyphVector extends GlyphVector {
	
	private char[] chars;
	private Font f;
	private FreetypeFontPeer peer;
	private int[] glyphCodes;
	
	public FreetypeGlyphVector(Font font, FontRenderContext frc, CharacterIterator ci) {
		f = font;
		if(!(font.getPeer() instanceof FreetypeFontPeer)){
			throw new IllegalArgumentException("Not a valid font.");
		}
		peer = (FreetypeFontPeer)font.getPeer();
		chars = new char[ci.getEndIndex()];
		glyphCodes = new int[ci.getEndIndex()];
		for(char c = ci.first(); c != CharacterIterator.DONE; c = ci.next()) {
			chars[ci.getIndex()] = c;
			glyphCodes[ci.getIndex()] = getglyphindex(peer.nativefont, c);
			System.out.println(c+":index"+glyphCodes[ci.getIndex()]);
		}
	}
	
	public native int getglyphindex(long font, int character);
	private native GeneralPath getGlyphOutlineNative(long font, int glyphIndex);

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
		GeneralPath gp = getGlyphOutlineNative(peer.nativefont, glyphCodes[glyphIndex]);
		AffineTransform tx = AffineTransform.getTranslateInstance(glyphIndex*40, 0);
		gp.transform(tx);
		System.out.println(gp);
		return gp;
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
		return glyphCodes.length;
	}

	@Override
	public Shape getOutline() {
		GeneralPath path = new GeneralPath();
		for( int i = 0; i < getNumGlyphs(); i++ ){
			path.append(getGlyphOutline(i), false);
		}
		return path;
		//return getGlyphOutline(4);
	}

	@Override
	public Shape getOutline(float x, float y) {
		AffineTransform tx = AffineTransform.getTranslateInstance( x, y );
		GeneralPath gp = (GeneralPath)getOutline();
		gp.transform( tx );
		return gp;
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
