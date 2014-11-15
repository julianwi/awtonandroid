package julianwi.awtpeer;

import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.MouseEvent;
import java.awt.event.PaintEvent;
import java.awt.event.WindowEvent;
import java.awt.image.DataBuffer;
import java.awt.image.Raster;
import java.awt.image.SampleModel;
import java.awt.image.SinglePixelPackedSampleModel;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class WindowPipe extends Thread{
	
	private AndroidWindowPeer awp;

	public WindowPipe(AndroidWindowPeer awp) {
		this.awp = awp;
	}
	
	@Override
	public void run() {
		try {
			FileInputStream stream = new FileInputStream("/data/data/julianwi.awtpeer/returnpipe");
			while(true) {
				int id = stream.read();
				System.out.println("reading "+id);
				/*if(id == 0x01) {
					int height = 0, width = 0;
					byte[] array = new byte[4*2];
					for(int i=0;i<4*2;i++){
						System.out.println("reading"+i);
						array[i] = (byte) stream.read();
						System.out.println("readed "+array[i]);
					}
					ByteBuffer wrapped = ByteBuffer.wrap(array);
					width = wrapped.getInt();
					height = wrapped.getInt();
					System.out.println("reading width: "+width+" heigth: "+height);
					//awtComponent.setSize(width, height);
					Rectangle r = new Rectangle(0, 0, width, height);
					awp.bounds = r;
					int[] bandMasks = new int[]{ 0xFF0000, 0xFF00, 0xFF };
					SampleModel sm = new SinglePixelPackedSampleModel(DataBuffer.TYPE_INT, width, height, bandMasks);
					DataBuffer db = new DirectDataBufferInt(width*height);
					awp.destinationRaster = Raster.createWritableRaster(sm, db, null);
					//Graphics g1 = awtComponent.getGraphics();
					//g1.clearRect(r.x, r.y, r.width, r.height);
				    //g1.dispose();
					//ComponentReshapeEvent cre = new ComponentReshapeEvent(awtComponent, awtComponent.getX(), awtComponent.getY(), width, height);
					//eq.postEvent(cre);//awtComponent.dispatchEvent(cre);
					//reshape(awtComponent.getX(), awtComponent.getY(), width, height);
					Graphics g = awp.getGraphics();
					g.clearRect(0, 0, width, height);
					g.dispose();
					//awtComponent.setBounds(0, 0, width, height);
					Window w = (Window) awp.getComponent();
					w.setSize(width, height);
					w.validate();//w.pack();
					EventQueue eq = AndroidToolkit.getDefaultToolkit().getSystemEventQueue();
					eq.postEvent(new WindowEvent(w, WindowEvent.WINDOW_OPENED));
					eq.postEvent(new PaintEvent(w, PaintEvent.PAINT, new Rectangle(0, 0, w.getWidth(), w.getHeight())));
					System.out.println("showing: "+w.isShowing());
					System.out.println("setted size to: "+w.getWidth()+" "+w.getHeight());
					//awtComponent.invalidate();
				}*/
				if(id == 0x02){
					DataInputStream datastr = new DataInputStream(stream);
					int x = (int) datastr.readFloat();
					int y = (int) datastr.readFloat();
					System.out.println("touchevent"+x+"|"+y);
					MouseEvent me = new MouseEvent(awp.getComponent(), MouseEvent.MOUSE_PRESSED, System.currentTimeMillis(), MouseEvent.BUTTON1_MASK, x, y, 1, false, MouseEvent.BUTTON1);
					Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(me);
					//System.out.println(((Frame)awp.getComponent()).getComponentAt(x, y));
					Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(new PaintEvent(awp.getComponent(), PaintEvent.PAINT, awp.bounds));
				}if(id == 0x03){
					DataInputStream datastr = new DataInputStream(stream);
					int x = (int) datastr.readFloat();
					int y = (int) datastr.readFloat();
					System.out.println("touchevent"+x+"|"+y);
					MouseEvent me = new MouseEvent(awp.getComponent(), MouseEvent.MOUSE_RELEASED, System.currentTimeMillis(), MouseEvent.BUTTON1_MASK, x, y, 1, false, MouseEvent.BUTTON1);
					Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(me);
					Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(new PaintEvent(awp.getComponent(), PaintEvent.PAINT, awp.bounds));
				}if(id == 0x04){
					DataInputStream datastr = new DataInputStream(stream);
					int x = (int) datastr.readFloat();
					int y = (int) datastr.readFloat();
					System.out.println("touchevent"+x+"|"+y);
					MouseEvent me = new MouseEvent(awp.getComponent(), MouseEvent.MOUSE_MOVED, System.currentTimeMillis(), MouseEvent.BUTTON1_MASK, x, y, 1, false, MouseEvent.BUTTON1);
					Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(me);
					Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(new PaintEvent(awp.getComponent(), PaintEvent.PAINT, awp.bounds));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
