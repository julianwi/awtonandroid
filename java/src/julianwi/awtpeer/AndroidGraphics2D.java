package julianwi.awtpeer;

import java.awt.GraphicsConfiguration;
import java.awt.Rectangle;
import java.awt.image.ColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;
import java.awt.image.DirectColorModel;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.nio.ByteBuffer;
import java.util.Arrays;

import gnu.java.awt.java2d.AbstractGraphics2D;

public class AndroidGraphics2D extends AbstractGraphics2D {
	
	private AndroidWindowPeer awp;
	private boolean disposed;
	private ColorModel cm;

	public AndroidGraphics2D(AndroidWindowPeer androidWindowPeer) {
		super();
		if(androidWindowPeer==null)Thread.dumpStack();
		awp = androidWindowPeer;
		cm = new DirectColorModel( 32, 0xff0000, 0xff00, 0xff, 0xff000000 );
		init();
	    disposed = false;
	}

	@Override
	protected ColorModel getColorModel() {
		return cm;
	}

	@Override
	protected Rectangle getDeviceBounds() {
		return awp.bounds;
	}

	@Override
	public GraphicsConfiguration getDeviceConfiguration() {
		throw new UnsupportedOperationException("Not yet implemented.");
	}
	
	@Override
	public void dispose() {
		if(!disposed){
			System.out.println("disposing");
			//Thread.dumpStack();
			try {
				awp.pipeout.write(0x08);
				DataBufferInt data   = (DataBufferInt) getDestinationRaster().getDataBuffer();
				ByteBuffer byteBuffer = ByteBuffer.allocate(getDeviceBounds().width * getDeviceBounds().height * 4);
				byteBuffer.asIntBuffer().put(data.getData());
				awp.pipeout.write(byteBuffer.array());
				System.out.println("disposed");
				disposed = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	protected WritableRaster getDestinationRaster() {
		return awp.destinationRaster;
	}
	
}
