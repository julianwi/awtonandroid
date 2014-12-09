package julianwi.awtpeer;

import java.awt.AWTException;
import java.awt.Checkbox;
import java.awt.CheckboxMenuItem;
import java.awt.Choice;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.List;
import java.awt.PopupMenu;
import java.awt.PrintJob;
import java.awt.ScrollPane;
import java.awt.Scrollbar;
import java.awt.TextArea;
import java.awt.Window;
import java.awt.datatransfer.Clipboard;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.peer.DragSourceContextPeer;
import java.awt.font.TextAttribute;
import java.awt.im.InputMethodHighlight;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.peer.CheckboxMenuItemPeer;
import java.awt.peer.CheckboxPeer;
import java.awt.peer.ChoicePeer;
import java.awt.peer.DialogPeer;
import java.awt.peer.FileDialogPeer;
import java.awt.peer.FontPeer;
import java.awt.peer.FramePeer;
import java.awt.peer.ListPeer;
import java.awt.peer.PopupMenuPeer;
import java.awt.peer.RobotPeer;
import java.awt.peer.ScrollPanePeer;
import java.awt.peer.ScrollbarPeer;
import java.awt.peer.TextAreaPeer;
import java.awt.peer.WindowPeer;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

import javax.imageio.ImageIO;

import gnu.java.awt.EmbeddedWindow;
import gnu.java.awt.image.ImageConverter;
import gnu.java.awt.java2d.AbstractGraphics2D;
import gnu.java.awt.peer.ClasspathFontPeer;
import gnu.java.awt.peer.EmbeddedWindowPeer;
import gnu.java.awt.peer.swing.SwingCheckboxPeer;
import gnu.java.awt.peer.swing.SwingTextAreaPeer;
import gnu.java.awt.peer.swing.SwingToolkit;

public class AndroidToolkit extends SwingToolkit {
	
	private EventQueue eventQueue;

	@Override
	public EmbeddedWindowPeer createEmbeddedWindow(EmbeddedWindow arg0) {
		throw new UnsupportedOperationException("Not yet implemented.");
	}

	@Override
	public Font createFont(int arg0, InputStream arg1) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public RobotPeer createRobot(GraphicsDevice arg0) throws AWTException {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public ClasspathFontPeer getClasspathFontPeer(String name, Map<?, ?> attrs) {
		return new FreetypeFontPeer(name, attrs);
	}

	@Override
	public GraphicsEnvironment getLocalGraphicsEnvironment() {
		return new AndroidGraphicsEnvironment();
	}

	@Override
	public void beep() {
		throw new UnsupportedOperationException("Not yet implemented.");
		
	}

	@Override
	public int checkImage(Image arg0, int arg1, int arg2, ImageObserver arg3) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return 0;
	}

	@Override
	protected CheckboxPeer createCheckbox(Checkbox checkbox) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	protected CheckboxMenuItemPeer createCheckboxMenuItem(CheckboxMenuItem target) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	protected ChoicePeer createChoice(Choice arg0) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	protected DialogPeer createDialog(Dialog arg0) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public DragSourceContextPeer createDragSourceContextPeer(
			DragGestureEvent arg0) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	protected FileDialogPeer createFileDialog(FileDialog arg0) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	protected FramePeer createFrame(Frame arg0) {
		return new AndroidFramePeer(arg0);
	}

	@Override
	public Image createImage(String arg0) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public Image createImage(URL url) {
		BufferedImage buffered;
		try {
			buffered = ImageIO.read(url.openStream());
		} catch (IOException e) {
			throw new UnsupportedOperationException("Not yet implemented.");
		}
		if(buffered != null){
			return buffered;
		}
		else{
			throw new UnsupportedOperationException("Not yet implemented.");
		}
	}

	@Override
	public Image createImage(ImageProducer producer) {
		ImageConverter conv = new ImageConverter();
		producer.startProduction(conv);
		return conv.getImage();
	}

	@Override
	public Image createImage(byte[] arg0, int arg1, int arg2) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	protected ListPeer createList(List arg0) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	protected PopupMenuPeer createPopupMenu(PopupMenu arg0) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	protected ScrollPanePeer createScrollPane(ScrollPane arg0) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	protected ScrollbarPeer createScrollbar(Scrollbar arg0) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	protected TextAreaPeer createTextArea(TextArea textArea) {
		return new SwingTextAreaPeer(textArea);
	}

	@Override
	protected WindowPeer createWindow(Window arg0) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public ColorModel getColorModel() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public String[] getFontList() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public FontMetrics getFontMetrics(Font font) {
		System.out.println("get font : "+font.getFontName());
		ClasspathFontPeer peer = (ClasspathFontPeer) font.getPeer();
		return peer.getFontMetrics(font);
	}

	@Override
	protected FontPeer getFontPeer(String arg0, int arg1) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public Image getImage(String arg0) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public Image getImage(URL url) {
		return createImage(url);
	}

	@Override
	public PrintJob getPrintJob(Frame arg0, String arg1, Properties arg2) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public int getScreenResolution() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return 0;
	}

	@Override
	public Dimension getScreenSize() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public Clipboard getSystemClipboard() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	protected EventQueue getSystemEventQueueImpl() {
		System.out.println("loaded event queue");
		if (eventQueue == null)
			eventQueue = new EventQueue();
		return eventQueue;
	}

	@Override
	public Map<TextAttribute, ?> mapInputMethodHighlight(
			InputMethodHighlight arg0) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public boolean prepareImage(Image image, int width, int height, ImageObserver observer) {
		AbstractGraphics2D.prepareImage(image, width, height);
		return true;
	}

	@Override
	public void sync() {
		throw new UnsupportedOperationException("Not yet implemented.");
		
	}

}
