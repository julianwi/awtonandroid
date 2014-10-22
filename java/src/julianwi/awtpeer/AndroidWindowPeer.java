package julianwi.awtpeer;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.Window;
import java.awt.event.ComponentEvent;
import java.awt.event.PaintEvent;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

import gnu.java.awt.ComponentReshapeEvent;
import gnu.java.awt.peer.swing.SwingWindowPeer;

public class AndroidWindowPeer extends SwingWindowPeer {

	private Insets insets;
	public OutputStream pipeout;

	public AndroidWindowPeer(Window window) {
		super(window);
		System.out.println("window constructed");
	    insets = new Insets(0, 0, 0, 0);
	    awtComponent.setBackground(SystemColor.window);
	}
	
	@Override
	public void show() {
		try {
			Process p;
			p = Runtime.getRuntime().exec(new String[]{"sh", "/system/bin/am", "start", "--user", "0", "julianwi.awtpeer/.WindowActivity"});
			p.waitFor();
		} catch (Exception e) {
			System.out.println("error while starting activity");
			e.printStackTrace();
		}
		try {
			pipeout = new BufferedOutputStream(new FileOutputStream("/data/data/julianwi.awtpeer/pipe"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		EventQueue eq = AndroidToolkit.getDefaultToolkit().getSystemEventQueue();
		Window w = (Window) super.awtComponent;
		eq.postEvent(new WindowEvent(w, WindowEvent.WINDOW_OPENED));
		eq.postEvent(new PaintEvent(w, PaintEvent.PAINT, new Rectangle(0, 0, w.getWidth(), w.getHeight())));
		Graphics g = getGraphics();
		g.clearRect(0, 0, w.getWidth(), w.getHeight());
		g.dispose();
		
		int height = 0, width = 0;
		try {
			pipeout.write(0x02);
			pipeout.flush();
			FileInputStream fr = new FileInputStream(new File("/data/data/julianwi.awtpeer/returnpipe"));
			byte[] array = new byte[4*2];
			while(fr.read() != 0x01){
				Thread.sleep(10);
			}
			for(int i=0;i<4*2;i++){
				System.out.println("reading"+i);
				array[i] = (byte) fr.read();
				System.out.println("readed "+array[i]);
			}
			fr.close();
			ByteBuffer wrapped = ByteBuffer.wrap(array);
			width = wrapped.getInt();
			height = wrapped.getInt();
			System.out.println("reading width: "+width+" heigth: "+height);
			//awtComponent.setSize(width, height);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Rectangle r = new Rectangle(0, 0, width, height);
		Graphics g1 = awtComponent.getGraphics();
		g1.clearRect(r.x, r.y, r.width, r.height);
	    g1.dispose();
		ComponentReshapeEvent cre = new ComponentReshapeEvent(awtComponent, awtComponent.getX(), awtComponent.getY(), width, height);
		awtComponent.dispatchEvent(cre);
		ComponentEvent ce = new ComponentEvent(awtComponent, ComponentEvent.COMPONENT_RESIZED);
		awtComponent.dispatchEvent(ce);
		System.out.println("setted size to: "+awtComponent.getWidth()+" "+awtComponent.getHeight());
		//awtComponent.invalidate();
	}
	
	@Override
	public Graphics getGraphics() {
		AndroidGraphics2D g = new AndroidGraphics2D(this);
		g.setColor(awtComponent.getForeground());
		g.setBackground(awtComponent.getBackground());
		g.setFont(awtComponent.getFont());
		return g;
	}
	
	public Image createImage(int w, int h) {
		System.out.println("creating image "+w+" * "+h);
		// FIXME: Should return a buffered image.
		//return createVolatileImage(w, h);
		//return new OffScreenImage(w, h);
		return new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	}
	
	@Override
	public VolatileImage createVolatileImage(int width, int height) {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		GraphicsConfiguration gc = gd.getDefaultConfiguration();
		return gc.createCompatibleVolatileImage(width, height);
	}

	@Override
	public void toBack() {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public void toFront() {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public void updateAlwaysOnTop() {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public boolean requestWindowFocus() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return false;
	}

}
