package julianwi.awtpeer;

import java.awt.AWTException;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.Checkbox;
import java.awt.CheckboxMenuItem;
import java.awt.Choice;
import java.awt.Dialog;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Label;
import java.awt.List;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.PopupMenu;
import java.awt.PrintJob;
import java.awt.ScrollPane;
import java.awt.Scrollbar;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Window;
import java.awt.datatransfer.Clipboard;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.peer.DragSourceContextPeer;
import java.awt.font.TextAttribute;
import java.awt.im.InputMethodHighlight;
import java.awt.image.ColorModel;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.peer.ButtonPeer;
import java.awt.peer.CanvasPeer;
import java.awt.peer.CheckboxMenuItemPeer;
import java.awt.peer.CheckboxPeer;
import java.awt.peer.ChoicePeer;
import java.awt.peer.DialogPeer;
import java.awt.peer.FileDialogPeer;
import java.awt.peer.FontPeer;
import java.awt.peer.FramePeer;
import java.awt.peer.LabelPeer;
import java.awt.peer.ListPeer;
import java.awt.peer.MenuBarPeer;
import java.awt.peer.MenuItemPeer;
import java.awt.peer.MenuPeer;
import java.awt.peer.PanelPeer;
import java.awt.peer.PopupMenuPeer;
import java.awt.peer.RobotPeer;
import java.awt.peer.ScrollPanePeer;
import java.awt.peer.ScrollbarPeer;
import java.awt.peer.TextAreaPeer;
import java.awt.peer.TextFieldPeer;
import java.awt.peer.WindowPeer;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

import gnu.java.awt.ClasspathToolkit;
import gnu.java.awt.EmbeddedWindow;
import gnu.java.awt.peer.ClasspathFontPeer;
import gnu.java.awt.peer.EmbeddedWindowPeer;
import gnu.java.awt.peer.swing.SwingLabelPeer;

public class AndroidToolkit extends ClasspathToolkit {
	
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
		return new AndroidFontPeer(name, attrs);
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
	protected ButtonPeer createButton(Button arg0) {
		return new AndroidButtonPeer(arg0);
	}

	@Override
	protected CanvasPeer createCanvas(Canvas arg0) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	protected CheckboxPeer createCheckbox(Checkbox arg0) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	protected CheckboxMenuItemPeer createCheckboxMenuItem(CheckboxMenuItem arg0) {
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
	public Image createImage(URL arg0) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public Image createImage(ImageProducer arg0) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public Image createImage(byte[] arg0, int arg1, int arg2) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	protected LabelPeer createLabel(Label arg0) {
		return new SwingLabelPeer(arg0);
	}

	@Override
	protected ListPeer createList(List arg0) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	protected MenuPeer createMenu(Menu arg0) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	protected MenuBarPeer createMenuBar(MenuBar arg0) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	protected MenuItemPeer createMenuItem(MenuItem arg0) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	protected PanelPeer createPanel(Panel arg0) {
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
	protected TextAreaPeer createTextArea(TextArea arg0) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	protected TextFieldPeer createTextField(TextField arg0) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
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
	public Image getImage(URL arg0) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
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
	public boolean isModalExclusionTypeSupported(ModalExclusionType arg0) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return false;
	}

	@Override
	public boolean isModalityTypeSupported(ModalityType arg0) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return false;
	}

	@Override
	public Map<TextAttribute, ?> mapInputMethodHighlight(
			InputMethodHighlight arg0) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public boolean prepareImage(Image arg0, int arg1, int arg2,
			ImageObserver arg3) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return false;
	}

	@Override
	public void sync() {
		throw new UnsupportedOperationException("Not yet implemented.");
		
	}

}
