package julianwi.awtpeer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorModel;
import java.awt.image.ImageObserver;
import java.awt.image.WritableRaster;
import java.nio.ByteBuffer;

public class AndroidGraphics2D extends CairoGraphics2D {
	
	private WritableRaster destraster;
	private boolean disposed;
	
	private native long directraster(ByteBuffer buffer, int width, int height);
	
	private native void cairoDrawString(long pointer, String string);
	
	private native void cairoDrawImage(long pointer, ByteBuffer buffer, int x, int y, int width, int height);

	public AndroidGraphics2D(WritableRaster destinationRaster) {
		super();
		System.out.println("android graphics created");
		if(destinationRaster==null)Thread.dumpStack();
		destraster = destinationRaster;
		long pointer = directraster(((DirectDataBufferInt)destinationRaster.getDataBuffer()).buffer, destinationRaster.getWidth(), destinationRaster.getHeight());
		setup(pointer);
	    disposed = false;
	}

	public AndroidGraphics2D(AndroidGraphics2D androidGraphics2D) {
		super();
		destraster = androidGraphics2D.destraster;
		long pointer = directraster(((DirectDataBufferInt)destraster.getDataBuffer()).buffer, destraster.getWidth(), destraster.getHeight());
		copy(androidGraphics2D, pointer);
		disposed = false;
	}

	@Override
	public GraphicsConfiguration getDeviceConfiguration() {
		throw new UnsupportedOperationException("Not yet implemented.");
	}

	@Override
	public Graphics create() {
		return new AndroidGraphics2D(this);
	}

	@Override
	protected void copyAreaImpl(int x, int y, int width, int height, int dx, int dy) {
		throw new UnsupportedOperationException("Not yet implemented.");
	}

	@Override
	protected Rectangle2D getRealBounds() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	protected ColorModel getNativeCM() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}
	
	@Override
	public void drawString(String str, float x, float y) {
		System.out.println("drawing string: "+str+" to "+x+"|"+y);
		drawGlyphVector(font.createGlyphVector(getFontRenderContext(), str), x, y);
	}
	
	@Override
	public boolean drawImage(Image img, int x, int y, ImageObserver observer) {
		if(img instanceof BufferedImage && ((BufferedImage) img).getRaster().getDataBuffer() instanceof DirectDataBufferInt){
			DirectDataBufferInt data = (DirectDataBufferInt) ((BufferedImage) img).getRaster().getDataBuffer();
			System.out.println("drawing image to "+x+"|"+y);
			System.out.println("image bounds "+((BufferedImage)img).getRaster().getBounds());
			cairoDrawImage(nativePointer, data.buffer, x, y, ((BufferedImage) img).getRaster().getWidth(), ((BufferedImage) img).getRaster().getHeight());
			return true;
		}
		return super.drawImage(img, x, y, observer);
	}
	
	@Override
	public void drawGlyphVector(GlyphVector gv, float x, float y) {
		draw(gv.getOutline(x, y));
	}
	
}
