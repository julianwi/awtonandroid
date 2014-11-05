package julianwi.awtpeer;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.geom.Rectangle2D;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.nio.ByteBuffer;

public class AndroidGraphics2D extends CairoGraphics2D {
	
	private WritableRaster destraster;
	private boolean disposed;
	
	private native long directraster(ByteBuffer buffer, int width, int height);

	public AndroidGraphics2D(WritableRaster destinationRaster) {
		super();
		if(destinationRaster==null)Thread.dumpStack();
		destraster = destinationRaster;
		long pointer = directraster(((DirectDataBufferInt)destinationRaster.getDataBuffer()).buffer, destinationRaster.getWidth(), destinationRaster.getHeight());
		setup(pointer);
	    disposed = false;
	}

	@Override
	public GraphicsConfiguration getDeviceConfiguration() {
		throw new UnsupportedOperationException("Not yet implemented.");
	}

	@Override
	public Graphics create() {
		return new AndroidGraphics2D(destraster);
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
	
}
