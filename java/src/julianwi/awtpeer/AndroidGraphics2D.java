package julianwi.awtpeer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Transparency;
import java.awt.geom.PathIterator;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ImageObserver;
import java.awt.image.Raster;
import java.awt.peer.FontPeer;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;

import gnu.java.awt.image.AsyncImage;
import gnu.java.awt.java2d.AbstractGraphics2D;
import gnu.java.awt.java2d.ScanlineCoverage;

public class AndroidGraphics2D extends AbstractGraphics2D {
	
	private AndroidWindowPeer awp;
	private Rectangle clip;
	private boolean disposed;

	public AndroidGraphics2D(AndroidWindowPeer androidWindowPeer) {
		super();
		awp = androidWindowPeer;
		init();
	    disposed = false;
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

	public Graphics create() {
		return (AndroidGraphics2D)super.create();
	}

	public void setClip(Shape c) {
		super.setClip(c);
		if(c instanceof Rectangle){
			clip = (Rectangle) c;
		}
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

	protected void init() {
		super.init();
	}

	public void setPaint(Paint p) {
		super.setPaint(p);
		if(p instanceof Color){
			Color c = (Color) p;
			ByteBuffer bb = ByteBuffer.allocate(4);
			bb.putInt(c.getRGB());
			try {
				awp.pipeout.write(0x03);
				awp.pipeout.write(bb.array());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("have to set forground color");
		System.out.println(p);
	}

	  protected void fillShape(Shape s, boolean isFont)
	  {
		  super.fillShape(s, isFont);
		  throw new UnsupportedOperationException("Not yet implemented.");
	  }

	protected boolean rawDrawImage(Image image, int x, int y, ImageObserver obs) {
		image = unwrap(image);
		boolean ret;
		if(image instanceof OffScreenImage){
			OffScreenImage offimg = (OffScreenImage) image;
			for (int i=0; i<offimg.methods.size(); i++) {
				try {
					offimg.methods.get(i).invoke(this, offimg.args.get(i));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			ret = true;
		}
		else if(image instanceof BufferedImage){
			BufferedImage bi = (BufferedImage) image;
			int transparency = bi.getTransparency();
			int w = bi.getWidth();
			int h = bi.getHeight();
			if(transparency == Transparency.OPAQUE){
				System.out.println("unknown transparency");
				throw new UnsupportedOperationException("Not yet implemented.");
			}
			else{
				for(int yy = 0; yy < h; yy++){
					for(int xx = 0; xx < w; xx++){
						if(bi.getRGB(xx, yy) != 0){
							System.out.println("drawing pixel "+xx+"|"+yy + " color: "+Integer.toHexString(bi.getRGB(xx, yy)));
						}
					}
				}
            }
            ret = true;
		}
		else{
			ret = super.rawDrawImage(image, x, y, obs);
		}
		return ret;
	}

	public void setFont(Font f) {
		super.setFont(f);
			//TODO implement Android fonts
			System.out.println("android Fonts are not implemented");
	}

	public void drawString(String s, int x, int y) {
		System.out.println("drawing String");
		ByteBuffer bb = ByteBuffer.allocate(3*4);
		bb.putInt(x);
		bb.putInt(y);
		bb.putInt(s.length());
		try {
			awp.pipeout.write(0x04);
			awp.pipeout.write(bb.array());
			awp.pipeout.write(s.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Image unwrap(Image im) {
		Image image = im;
		if(image instanceof AsyncImage){
			throw new UnsupportedOperationException("Not yet implemented.");
		}
		return image;
	}

}
