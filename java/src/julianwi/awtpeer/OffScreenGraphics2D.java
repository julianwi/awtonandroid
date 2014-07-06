package julianwi.awtpeer;

import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.RenderingHints.Key;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;
import java.util.Map;

public class OffScreenGraphics2D extends Graphics2D implements Cloneable {

	private Rectangle clip;
	private Color color;
	private OffScreenImage offimg;

	public OffScreenGraphics2D(OffScreenImage offScreenImage) {
		offimg = offScreenImage;
	}

	@Override
	public void draw(Shape shape) {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public boolean drawImage(Image image, AffineTransform xform,
			ImageObserver obs) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return false;
	}

	@Override
	public void drawImage(BufferedImage image, BufferedImageOp op, int x, int y) {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public void drawRenderedImage(RenderedImage image, AffineTransform xform) {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public void drawRenderableImage(RenderableImage image, AffineTransform xform) {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public void drawString(String text, int x, int y) {
		try {
			offimg.methods.add(AndroidGraphics2D.class.getMethod("drawString", new Class[]{String.class, int.class, int.class}));
			offimg.args.add(new Object[]{text, x, y});
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void drawString(String text, float x, float y) {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public void drawString(AttributedCharacterIterator iterator, int x, int y) {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public void drawString(AttributedCharacterIterator iterator, float x,
			float y) {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public void fill(Shape shape) {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public boolean hit(Rectangle rect, Shape text, boolean onStroke) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return false;
	}

	@Override
	public GraphicsConfiguration getDeviceConfiguration() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public void setComposite(Composite comp) {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public void setPaint(Paint paint) {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public void setStroke(Stroke stroke) {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public void setRenderingHint(Key hintKey, Object hintValue) {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public Object getRenderingHint(Key hintKey) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public void setRenderingHints(Map<?, ?> hints) {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public void addRenderingHints(Map<?, ?> hints) {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public RenderingHints getRenderingHints() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public void translate(int x, int y) {
		//TODO implement clipping
	}

	@Override
	public void translate(double tx, double ty) {
		//TODO implement clipping
	}

	@Override
	public void rotate(double theta) {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public void rotate(double theta, double x, double y) {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public void scale(double scaleX, double scaleY) {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public void shear(double shearX, double shearY) {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public void transform(AffineTransform transform) {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public void setTransform(AffineTransform transform) {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public AffineTransform getTransform() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public Paint getPaint() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public Composite getComposite() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public void setBackground(Color color) {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public Color getBackground() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public Stroke getStroke() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public void clip(Shape s) {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public FontRenderContext getFontRenderContext() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public void drawGlyphVector(GlyphVector g, float x, float y) {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public Graphics create() {
		try {
			return (OffScreenGraphics2D) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public void setColor(Color color) {
		try {
			offimg.methods.add(AndroidGraphics2D.class.getMethod("setColor", Color.class));
			offimg.args.add(new Object[]{color});
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		this.color = color;
	}

	@Override
	public void setPaintMode() {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public void setXORMode(Color color) {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public Font getFont() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public void setFont(Font font) {
		try {
			offimg.methods.add(AndroidGraphics2D.class.getMethod("setFont", Font.class));
			offimg.args.add(new Object[]{font});
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	@Override
	public FontMetrics getFontMetrics(Font font) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public Rectangle getClipBounds() {
		return clip;
	}

	@Override
	public void clipRect(int x, int y, int width, int height) {
		clip = new Rectangle(x, y, width, height);
	}

	@Override
	public void setClip(int x, int y, int width, int height) {
		//TODO implement clipping
	}

	@Override
	public Shape getClip() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public void setClip(Shape clip) {
		//TODO implement clipping
	}

	@Override
	public void copyArea(int x, int y, int width, int height, int dx, int dy) {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public void drawLine(int x1, int y1, int x2, int y2) {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public void fillRect(int x, int y, int width, int height) {
		try {
			offimg.methods.add(AndroidGraphics2D.class.getMethod("fillRect", new Class[]{int.class, int.class, int.class, int.class}));
			offimg.args.add(new Object[]{x, y, width, height});
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void clearRect(int x, int y, int width, int height) {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public void drawRoundRect(int x, int y, int width, int height,
			int arcWidth, int arcHeight) {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public void fillRoundRect(int x, int y, int width, int height,
			int arcWidth, int arcHeight) {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public void drawOval(int x, int y, int width, int height) {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public void fillOval(int x, int y, int width, int height) {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public void drawArc(int x, int y, int width, int height, int arcStart,
			int arcAngle) {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public void fillArc(int x, int y, int width, int height, int arcStart,
			int arcAngle) {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public void drawPolyline(int[] xPoints, int[] yPoints, int npoints) {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public void drawPolygon(int[] xPoints, int[] yPoints, int npoints) {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public void fillPolygon(int[] xPoints, int[] yPoints, int npoints) {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public boolean drawImage(Image image, int x, int y, ImageObserver observer) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return false;
	}

	@Override
	public boolean drawImage(Image image, int x, int y, int width, int height,
			ImageObserver observer) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return false;
	}

	@Override
	public boolean drawImage(Image image, int x, int y, Color bgcolor,
			ImageObserver observer) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return false;
	}

	@Override
	public boolean drawImage(Image image, int x, int y, int width, int height,
			Color bgcolor, ImageObserver observer) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return false;
	}

	@Override
	public boolean drawImage(Image image, int dx1, int dy1, int dx2, int dy2,
			int sx1, int sy1, int sx2, int sy2, ImageObserver observer) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return false;
	}

	@Override
	public boolean drawImage(Image image, int dx1, int dy1, int dx2, int dy2,
			int sx1, int sy1, int sx2, int sy2, Color bgcolor,
			ImageObserver observer) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return false;
	}

	@Override
	public void dispose() {
		//nothig to do here
	}

}
