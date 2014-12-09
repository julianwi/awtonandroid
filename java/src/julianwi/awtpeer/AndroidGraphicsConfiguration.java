package julianwi.awtpeer;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.Raster;
import java.awt.image.SampleModel;
import java.awt.image.SinglePixelPackedSampleModel;
import java.awt.image.VolatileImage;

public class AndroidGraphicsConfiguration extends GraphicsConfiguration {
	
	private AndroidGraphicsDevice device;

	public AndroidGraphicsConfiguration(AndroidGraphicsDevice device) {
		this.device = device;
	}

	@Override
	public BufferedImage createCompatibleImage(int width, int height) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public BufferedImage createCompatibleImage(int width, int height, int transparency) {
		if(transparency != Transparency.TRANSLUCENT){
			System.out.println(transparency);
			throw new UnsupportedOperationException("Not yet implemented.");
		}
		return new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	}

	@Override
	public VolatileImage createCompatibleVolatileImage(int width, int height) {
		return createCompatibleVolatileImage(width, height, Transparency.OPAQUE);
	}

	@Override
	public VolatileImage createCompatibleVolatileImage(int width, int height, int transparency) {
		if(transparency != Transparency.OPAQUE){
			throw new UnsupportedOperationException("Not yet implemented.");
		}
		return new AndroidVolatileImage(width, height);
	}

	@Override
	public Rectangle getBounds() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public ColorModel getColorModel() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public ColorModel getColorModel(int arg0) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public AffineTransform getDefaultTransform() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public GraphicsDevice getDevice() {
		return device;
	}

	@Override
	public AffineTransform getNormalizingTransform() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

}
