package julianwi.awtpeer;

import java.awt.AWTError;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.font.LineMetrics;
import java.awt.font.TextAttribute;
import java.awt.geom.Rectangle2D;
import java.text.CharacterIterator;
import java.util.Locale;
import java.util.Map;

import gnu.java.awt.peer.ClasspathFontPeer;

public class AndroidFontPeer extends ClasspathFontPeer {

	private AndroidFontMetrics fontMetrics;

	public AndroidFontPeer(String name, Map<?, ?> atts) {
		super(name, atts);
	    int size = 12;
	    Float sizeFl = (Float) atts.get(TextAttribute.SIZE);
	    if (sizeFl != null)
	      size = sizeFl.intValue();

	    int style = 0;
	    // Detect italic attribute.
	    Float posture = (Float) atts.get(TextAttribute.POSTURE);
	    if (posture != null && !posture.equals(TextAttribute.POSTURE_REGULAR))
	      style |= Font.ITALIC;

	    // Detect bold attribute.
	    Float weight = (Float) atts.get(TextAttribute.WEIGHT);
	    if (weight != null && weight.compareTo(TextAttribute.WEIGHT_REGULAR) > 0)
	      style |= Font.BOLD;

	    //this.name = name;
	    this.style = style;
	    this.size = size;
	}

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
			if (font.getPeer() != this){
				throw new AWTError("The specified font has a different peer than this");
			}
			if (fontMetrics == null){
				fontMetrics = new AndroidFontMetrics(font);
			}
			return fontMetrics;
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
