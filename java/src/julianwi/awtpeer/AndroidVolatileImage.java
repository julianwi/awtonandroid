package julianwi.awtpeer;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.ImageCapabilities;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.VolatileImage;

public class AndroidVolatileImage extends VolatileImage {

	private int width;
	private int height;

	public AndroidVolatileImage(int width, int height) {
		this.width = width;
		this.height = height;
		System.out.println("creating pixmap");
	}

	@Override
	public BufferedImage getSnapshot() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public int getWidth() {
		System.out.println("VolatileImageWidh: "+width);
		return width;
	}

	@Override
	public int getHeight() {
		System.out.println("VolatileImageHeight: "+height);
		return height;
	}

	@Override
	public Graphics2D createGraphics() {
		System.out.println("ceating graphics");
		Graphics2D g2d = new AndroidGraphics2D(null);
		return g2d;
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
