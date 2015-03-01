package julianwi.awtpeer;

import java.awt.AWTException;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.PaintEvent;
import java.awt.image.DataBuffer;
import java.awt.image.Raster;
import java.awt.image.SampleModel;
import java.awt.image.SinglePixelPackedSampleModel;
import java.io.IOException;

public class WindowPipe extends Thread{
	
	private AndroidWindowPeer awp;

	public WindowPipe(AndroidWindowPeer awp) {
		this.awp = awp;
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				int id = awp.pipein.read();
				System.out.println("reading "+id);
				if(id == 0x01) {
					int width = awp.pipein.readInt();
					int height = awp.pipein.readInt();
					Rectangle r = new Rectangle(0, 0, width, height);
					awp.bounds = r;
					int[] bandMasks = new int[]{ 0xFF0000, 0xFF00, 0xFF };
					SampleModel sm = new SinglePixelPackedSampleModel(DataBuffer.TYPE_INT, width, height, bandMasks);
					DataBuffer db = new DirectDataBufferInt(width*height);
					awp.destinationRaster = Raster.createWritableRaster(sm, db, null);
					Graphics g = awp.getGraphics();
					g.clearRect(0, 0, width, height);
					g.dispose();
					Window w = (Window) awp.getComponent();
					w.setSize(width, height);
					w.validate();
					EventQueue eq = AndroidToolkit.getDefaultToolkit().getSystemEventQueue();
					eq.postEvent(new ComponentEvent(w, ComponentEvent.COMPONENT_RESIZED));
					eq.postEvent(new PaintEvent(w, PaintEvent.PAINT, new Rectangle(0, 0, w.getWidth(), w.getHeight())));
				}
				if(id == 0x02){
					int x = awp.pipein.readInt();
					int y = awp.pipein.readInt();
					MouseEvent me = new MouseEvent(awp.getComponent(), MouseEvent.MOUSE_PRESSED, System.currentTimeMillis(), MouseEvent.BUTTON1_MASK, x, y, 1, false, MouseEvent.BUTTON1);
					Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(me);
				}
				if(id == 0x03){
					int x = awp.pipein.readInt();
					int y = awp.pipein.readInt();
					MouseEvent me = new MouseEvent(awp.getComponent(), MouseEvent.MOUSE_RELEASED, System.currentTimeMillis(), MouseEvent.BUTTON1_MASK, x, y, 1, false, MouseEvent.BUTTON1);
					Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(me);
				}if(id == 0x04){
					int x = awp.pipein.readInt();
					int y = awp.pipein.readInt();
					MouseEvent me = new MouseEvent(awp.getComponent(), MouseEvent.MOUSE_DRAGGED, System.currentTimeMillis(), MouseEvent.BUTTON1_MASK, x, y, 1, false, MouseEvent.BUTTON1);
					Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(me);
				}
				if(id == -1){
					awp.pipein.close();
					throw new AWTException("conection to android activity lost");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (AWTException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

}
