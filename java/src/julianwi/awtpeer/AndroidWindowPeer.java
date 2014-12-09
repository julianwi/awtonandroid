package julianwi.awtpeer;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.Window;
import java.awt.event.FocusEvent;
import java.awt.event.PaintEvent;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.Raster;
import java.awt.image.SampleModel;
import java.awt.image.SinglePixelPackedSampleModel;
import java.awt.image.VolatileImage;
import java.awt.image.WritableRaster;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

import gnu.java.awt.peer.ClasspathFontPeer;
import gnu.java.awt.peer.swing.SwingWindowPeer;
import gnu.java.net.local.LocalSocket;
import gnu.java.net.local.LocalSocketAddress;

public class AndroidWindowPeer extends SwingWindowPeer {

	public OutputStream pipeout;
	public DataInputStream pipein;
	public Rectangle bounds;
	public WritableRaster destinationRaster;
	public RefreshThread rt = new RefreshThread(this);
	public boolean grchanged = false;
	public LocalSocket ls;

	public AndroidWindowPeer(Window window) {
		super(window);
		System.out.println("window constructed");
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
			ls = new LocalSocket(new LocalSocketAddress("/data/data/julianwi.awtpeer/socket"));
			pipeout = ls.getOutputStream();
			pipein = new DataInputStream(ls.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
		//Graphics g = getGraphics();
		//g.clearRect(0, 0, w.getWidth(), w.getHeight());
		//g.dispose();
		
		int height = 0, width = 0;
		try {
			width = pipein.readInt();
			height = pipein.readInt();
			System.out.println("reading width: "+width+" heigth: "+height);
			//awtComponent.setSize(width, height);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Rectangle r = new Rectangle(0, 0, width, height);
		bounds = r;
		int[] bandMasks = new int[]{ 0xFF0000, 0xFF00, 0xFF };
		SampleModel sm = new SinglePixelPackedSampleModel(DataBuffer.TYPE_INT, width, height, bandMasks);
		DataBuffer db = new DirectDataBufferInt(width*height);
		destinationRaster = Raster.createWritableRaster(sm, db, null);
		//Graphics g1 = awtComponent.getGraphics();
		//g1.clearRect(r.x, r.y, r.width, r.height);
	    //g1.dispose();
		//ComponentReshapeEvent cre = new ComponentReshapeEvent(awtComponent, awtComponent.getX(), awtComponent.getY(), width, height);
		//eq.postEvent(cre);//awtComponent.dispatchEvent(cre);
		//reshape(awtComponent.getX(), awtComponent.getY(), width, height);
		Graphics g = getGraphics();
		g.clearRect(0, 0, width, height);
		g.dispose();
		//awtComponent.setBounds(0, 0, width, height);
		Window w = (Window) super.awtComponent;
		w.setSize(width, height);
		w.validate();//w.pack();
		EventQueue eq = AndroidToolkit.getDefaultToolkit().getSystemEventQueue();
		eq.postEvent(new WindowEvent(w, WindowEvent.WINDOW_OPENED));
		eq.postEvent(new PaintEvent(w, PaintEvent.PAINT, new Rectangle(0, 0, w.getWidth(), w.getHeight())));
		System.out.println("setted size to: "+awtComponent.getWidth()+" "+awtComponent.getHeight());
		//awtComponent.invalidate();
		new WindowPipe(this).start();
	}
	
	@Override
	public Graphics getGraphics() {
		AndroidGraphics2D g = new OnScreenGraphics2D(destinationRaster, this);
		g.setColor(awtComponent.getForeground());
		g.setBackground(awtComponent.getBackground());
		g.setFont(awtComponent.getFont());
		return g;
	}
	
	public Image createImage(int w, int h) {
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
	
	@SuppressWarnings("deprecation")
	@Override
	public FontMetrics getFontMetrics(Font font) {
		if(font.getPeer() instanceof ClasspathFontPeer){
			return ((ClasspathFontPeer) font.getPeer()).getFontMetrics(font);
		}
		else{
			throw new UnsupportedOperationException("Not yet implemented.");
		}
	}
	
	@Override
	protected void handleFocusEvent(FocusEvent e) {
		System.out.println("handling focus evenet "+e);
	}

}
