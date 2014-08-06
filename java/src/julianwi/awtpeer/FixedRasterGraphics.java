package julianwi.awtpeer;

import java.awt.CompositeContext;
import java.awt.PaintContext;
import java.awt.Point;
import java.awt.image.ColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;
import java.awt.image.Raster;
import java.awt.image.SampleModel;
import java.awt.image.WritableRaster;

import gnu.java.awt.java2d.RasterGraphics;
import gnu.java.awt.java2d.ScanlineCoverage;

public class FixedRasterGraphics extends RasterGraphics {
	
	private PaintContext paintContext = null;

	public FixedRasterGraphics(WritableRaster r, ColorModel cm) {
		super(r, cm);
	}
	
	@Override
	public void renderScanline(int y, ScanlineCoverage c)
	  {
	    PaintContext pCtx = getPaintContext();
	    
	    int x0 = c.getMinX();
	    int x1 = c.getMaxX();
	    Raster paintRaster = pCtx.getRaster(x0, y, x1 - x0, 1);

	    // Do the anti aliasing thing.
	    float coverageAlpha = 0;
	    float maxCoverage = c.getMaxCoverage();
	    ColorModel cm = pCtx.getColorModel();
	    DataBuffer db = paintRaster.getDataBuffer();
	    Point loc = new Point(paintRaster.getMinX(), paintRaster.getMinY());
	    SampleModel sm = paintRaster.getSampleModel();
	    WritableRaster writeRaster = Raster.createWritableRaster(sm, db, loc);
	    WritableRaster alphaRaster = cm.getAlphaRaster(writeRaster);
	    int pixel;
	    ScanlineCoverage.Iterator iter = c.iterate();
	    while (iter.hasNext())
	      {
	        ScanlineCoverage.Range range = iter.next();
	        coverageAlpha = range.getCoverage() / maxCoverage;
	        if (coverageAlpha < 1.0)
	          {
	            for (int x = range.getXPos(); x < range.getXPosEnd(); x++)
	              {
	                pixel = alphaRaster.getSample(x-x0, 0, 0);
	                pixel = (int) (pixel * coverageAlpha);
	                alphaRaster.setSample(x-x0, 0, 0, pixel);
	              }
	          }
	      }
	    ColorModel paintColorModel = pCtx.getColorModel();
	    CompositeContext cCtx = getComposite().createContext(paintColorModel,
	                                                    getColorModel(),
	                                                    getRenderingHints());
	    WritableRaster raster = getDestinationRaster();
	    WritableRaster targetChild = raster.createWritableTranslatedChild(-x0, -y);
	    
	    cCtx.compose(paintRaster, targetChild, targetChild);
	    updateRaster(raster, x0, y, x1 - x0, 1);
	    cCtx.dispose();
	  }
	
	private PaintContext getPaintContext()
	  {
	    if (this.paintContext == null)
	      {
	        this.paintContext =
	          getColor().createContext(getColorModel(),
	                                        getDeviceBounds(),
	                                        getClipBounds(),
	                                        getTransform(),
	                                        getRenderingHints());
	      }
	   
	    return this.paintContext;
	  }

}
