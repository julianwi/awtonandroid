package julianwi.awtpeer;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.ColorModel;
import java.awt.image.ImageObserver;
import java.awt.image.Raster;
import java.awt.peer.FontPeer;
import java.io.IOException;
import java.nio.ByteBuffer;

import gnu.java.awt.java2d.AbstractGraphics2D;
import gnu.java.awt.java2d.ScanlineCoverage;

public class AndroidGraphics2D extends AbstractGraphics2D {
	
	private AndroidWindowPeer awp;

	public AndroidGraphics2D(AndroidWindowPeer androidWindowPeer) {
		awp = androidWindowPeer;
	}
	
	@Override
	  protected void rawDrawLine(int x0, int y0, int x1, int y1)
	  {
		throw new UnsupportedOperationException("Not yet implemented.");
	  }

	  @Override
	  protected void rawDrawRect(int x, int y, int w, int h)
	  {
		  throw new UnsupportedOperationException("Not yet implemented.");
	  }

	@Override
	protected void rawFillRect(int x, int y, int w, int h) {
		System.out.println("drawing reckt");
		try {
			ByteBuffer bb = ByteBuffer.allocate(16);
			bb.putInt(x);
			bb.putInt(y);
			bb.putInt(w);
			bb.putInt(h);
			awp.pipeout.write(0x01);
			awp.pipeout.write(bb.array());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	  /**
	   * Returns the color model of this Graphics object.
	   *
	   * @return the color model of this Graphics object
	   */
	  protected ColorModel getColorModel()
	  {
		  throw new UnsupportedOperationException("Not yet implemented.");
	  }

	  /**
	   * Returns the color model of the target device.
	   *
	   * @return the color model of the target device
	   */
	  protected ColorModel getDestinationColorModel()
	  {
		  throw new UnsupportedOperationException("Not yet implemented.");
	  }

	  /**
	   * Returns the bounds of the target.
	   *
	   * @return the bounds of the target
	   */
	  protected Rectangle getDeviceBounds()
	  {
		  throw new UnsupportedOperationException("Not yet implemented.");
	  }

	  public GraphicsConfiguration getDeviceConfiguration()
	  {
		  throw new UnsupportedOperationException("Not yet implemented.");
	  }

	public void dispose() {
		try {
			awp.pipeout.write(0x00);
			awp.pipeout.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	  public Graphics create()
	  {
		  throw new UnsupportedOperationException("Not yet implemented.");
	  }

	  public void setClip(Shape c)
	  {
	    super.setClip(c);
	    throw new UnsupportedOperationException("Not yet implemented.");
	  }

	  /**
	   * Notifies the backend that the raster has changed in the specified
	   * rectangular area. The raster that is provided in this method is always
	   * the same as the one returned in {@link #getDestinationRaster}.
	   * Backends that reflect changes to this raster directly don't need to do
	   * anything here.
	   *
	   * @param raster the updated raster, identical to the raster returned
	   *        by {@link #getDestinationRaster()}
	   * @param x the upper left corner of the updated region, X coordinate
	   * @param y the upper lef corner of the updated region, Y coordinate
	   * @param w the width of the updated region
	   * @param h the height of the updated region
	   */
	  protected void updateRaster(Raster raster, int x, int y, int w, int h)
	  {
		  throw new UnsupportedOperationException("Not yet implemented.");
	  }

	  @Override
	  public void renderScanline(int y, ScanlineCoverage c)
	  {
		  throw new UnsupportedOperationException("Not yet implemented.");
	  }

	  protected void init()
	  {
	    super.init();
	    throw new UnsupportedOperationException("Not yet implemented.");
	  }

	public void setPaint(Paint p) {
		super.setPaint(p);
		System.out.println("have to set forground color");
	}

	  protected void fillShape(Shape s, boolean isFont)
	  {
		  throw new UnsupportedOperationException("Not yet implemented.");
	  }

	  //private static WeakHashMap<Image,ZPixmap> imageCache = new WeakHashMap<Image,ZPixmap>();

	  protected boolean rawDrawImage(Image image, int x, int y, ImageObserver obs)
	  {
		  throw new UnsupportedOperationException("Not yet implemented.");
	  }

	public void setFont(Font f) {
		super.setFont(f);
			//TODO implement Android fonts
			System.out.println("android Fonts are not implemented");
	}

	  public void drawString(String s, int x, int y)
	  {
		  throw new UnsupportedOperationException("Not yet implemented.");
	  }

	  /**
	   * Extracts an image instance out of an AsyncImage. If the image isn't
	   * an AsyncImage, then the original instance is returned.
	   *
	   * @param im the image
	   *
	   * @return the image to render
	   */
	  private Image unwrap(Image im)
	  {
		  throw new UnsupportedOperationException("Not yet implemented.");
	  }

}
