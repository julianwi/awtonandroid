package julianwi.awtpeer;

import gnu.java.awt.ClasspathGraphicsEnvironment;
import gnu.java.awt.java2d.RasterGraphics;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;
import java.awt.image.Raster;
import java.awt.image.SampleModel;
import java.awt.image.SinglePixelPackedSampleModel;
import java.awt.image.WritableRaster;
import java.util.Locale;

public class AndroidGraphicsEnvironment extends ClasspathGraphicsEnvironment {
	
	private AndroidGraphicsDevice defaultDevice;
	
	public AndroidGraphicsEnvironment() {
		defaultDevice = new AndroidGraphicsDevice();
	}

	@Override
	public Graphics2D createGraphics(BufferedImage image) {
		System.out.println("creating image graphics");
		return new AndroidGraphics2D(image.getRaster());
		//return new RasterGraphics(image.getRaster(), image.getColorModel());
	}

	@Override
	public Font[] getAllFonts() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public String[] getAvailableFontFamilyNames() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public String[] getAvailableFontFamilyNames(Locale arg0) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public GraphicsDevice getDefaultScreenDevice() {
		return defaultDevice;
	}

	@Override
	public GraphicsDevice[] getScreenDevices() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}
	
	@Override
	public WritableRaster createRaster(ColorModel cm, SampleModel sm) {
		DataBuffer db = new DirectDataBufferInt(sm.getWidth()*sm.getHeight());
		return Raster.createWritableRaster(sm, db, new Point(0, 0));
	}

}
