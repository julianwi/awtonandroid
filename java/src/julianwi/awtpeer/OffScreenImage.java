package julianwi.awtpeer;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class OffScreenImage extends Image {

	private int width;
	private int height;
	public List<Method> methods = new ArrayList<Method>();
	public List<Object[]> args = new ArrayList<Object[]>();

	public OffScreenImage(int w, int h) {
		width = w;
		height = h;
	}

	@Override
	public int getWidth(ImageObserver observer) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return 0;
	}

	@Override
	public int getHeight(ImageObserver observer) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return 0;
	}

	@Override
	public ImageProducer getSource() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public Graphics getGraphics() {
		return new OffScreenGraphics2D(this);
	}

	@Override
	public Object getProperty(String name, ImageObserver observer) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public void flush() {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

}
