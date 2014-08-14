package julianwi.awtpeer;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.font.LineMetrics;
import java.awt.geom.Rectangle2D;
import java.text.CharacterIterator;
import java.util.Locale;
import java.util.Map;

import gnu.java.awt.peer.ClasspathFontPeer;

public class FreetypeFontPeer extends ClasspathFontPeer {
	
	static {
		System.loadLibrary("ftpeer");
		int error = InitFreeType();
		System.out.println("freetype init error code "+error);
		if(error != 0){
				throw new UnsupportedOperationException("an error occurred during freetype initialization error code " + error);
		}
	}

	public FreetypeFontPeer(String name, Map<?, ?> attrs) {
		super(name, attrs);
		// TODO Auto-generated constructor stub
	}
	
	public native static int InitFreeType();

	@Override
	public boolean canDisplay(Font font, int c) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return false;
	}

	@Override
	public int canDisplayUpTo(Font font, CharacterIterator i, int start,
			int limit) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return 0;
	}

	@Override
	public String getSubFamilyName(Font font, Locale locale) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public String getPostScriptName(Font font) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public int getNumGlyphs(Font font) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return 0;
	}

	@Override
	public int getMissingGlyphCode(Font font) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return 0;
	}

	@Override
	public byte getBaselineFor(Font font, char c) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return 0;
	}

	@Override
	public String getGlyphName(Font font, int glyphIndex) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public GlyphVector createGlyphVector(Font font, FontRenderContext frc,
			CharacterIterator ci) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public GlyphVector createGlyphVector(Font font, FontRenderContext ctx,
			int[] glyphCodes) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public GlyphVector layoutGlyphVector(Font font, FontRenderContext frc,
			char[] chars, int start, int limit, int flags) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public FontMetrics getFontMetrics(Font font) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public boolean hasUniformLineMetrics(Font font) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return false;
	}

	@Override
	public LineMetrics getLineMetrics(Font font, CharacterIterator ci,
			int begin, int limit, FontRenderContext rc) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public Rectangle2D getMaxCharBounds(Font font, FontRenderContext rc) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

}
