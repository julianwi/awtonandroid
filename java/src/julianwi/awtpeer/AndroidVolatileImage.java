package julianwi.awtpeer;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.ImageCapabilities;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.ImageObserver;
import java.awt.image.Raster;
import java.awt.image.SampleModel;
import java.awt.image.SinglePixelPackedSampleModel;
import java.awt.image.VolatileImage;
import java.awt.image.WritableRaster;

public class AndroidVolatileImage extends VolatileImage {

	public WritableRaster raster;

	public AndroidVolatileImage(int width, int height) {
		int[] bandMasks = new int[]{ 0xFF0000, 0xFF00, 0xFF };
		SampleModel sm = new SinglePixelPackedSampleModel(DataBuffer.TYPE_INT, width, height, bandMasks);
		DataBuffer db = new DirectDataBufferInt(width*height);
		raster = Raster.createWritableRaster(sm, db, null);
	}

	@Override
	public BufferedImage getSnapshot() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public int getWidth() {
		return raster.getWidth();
	}

	@Override
	public int getHeight() {
		return raster.getHeight();
	}

	@Override
	public Graphics2D createGraphics() {
		return new AndroidGraphics2D(raster);
	}

	@Override
	public int validate(GraphicsConfiguration gc) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return 0;
	}

	@Override
	public boolean contentsLost() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return false;
	}

	@Override
	public ImageCapabilities getCapabilities() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public int getWidth(ImageObserver observer) {
		return getWidth();
	}

	@Override
	public int getHeight(ImageObserver observer) {
		return getHeight();
	}

	@Override
	public Object getProperty(String name, ImageObserver observer) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

}
