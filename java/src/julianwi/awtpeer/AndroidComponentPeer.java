package julianwi.awtpeer;

import java.awt.AWTEvent;
import java.awt.AWTException;
import java.awt.BufferCapabilities;
import java.awt.BufferCapabilities.FlipContents;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.PaintEvent;
import java.awt.image.ColorModel;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.VolatileImage;
import java.awt.peer.ComponentPeer;
import java.awt.peer.ContainerPeer;

import sun.awt.CausedFocusEvent.Cause;

public class AndroidComponentPeer implements ComponentPeer {
	
	protected Component awtComponent;
	
	public AndroidComponentPeer(Component component){
		awtComponent = component;
	}

	@Override
	public boolean canDetermineObscurity() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return false;
	}

	@Override
	public int checkImage(Image arg0, int arg1, int arg2, ImageObserver arg3) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return 0;
	}

	@Override
	public void coalescePaintEvent(PaintEvent arg0) {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public void createBuffers(int arg0, BufferCapabilities arg1)
			throws AWTException {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public Image createImage(ImageProducer arg0) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public Image createImage(int arg0, int arg1) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public VolatileImage createVolatileImage(int arg0, int arg1) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public void destroyBuffers() {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public void disable() {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public void dispose() {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public void enable() {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public void flip(FlipContents arg0) {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public Image getBackBuffer() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public Rectangle getBounds() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public ColorModel getColorModel() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public FontMetrics getFontMetrics(Font arg0) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public Graphics getGraphics() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public GraphicsConfiguration getGraphicsConfiguration() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public Point getLocationOnScreen() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public Dimension getMinimumSize() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public Dimension getPreferredSize() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public Toolkit getToolkit() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public void handleEvent(AWTEvent arg0) {
		if(arg0.getID()==FocusEvent.FOCUS_GAINED){
			return;
		}
		throw new UnsupportedOperationException("Not yet implemented.");
	}

	@Override
	public boolean handlesWheelScrolling() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return false;
	}

	@Override
	public void hide() {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public boolean isFocusTraversable() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return false;
	}

	@Override
	public boolean isFocusable() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return false;
	}

	@Override
	public boolean isObscured() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return false;
	}

	@Override
	public boolean isReparentSupported() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return false;
	}

	@Override
	public void layout() {
		//nothing to do here
	}

	@Override
	public Dimension minimumSize() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public void paint(Graphics arg0) {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public Dimension preferredSize() {
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
	public void print(Graphics arg0) {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public void repaint(long arg0, int arg1, int arg2, int arg3, int arg4) {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public void reparent(ContainerPeer arg0) {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public void requestFocus() {
		throw new UnsupportedOperationException("Not yet implemented.");
	}

	@Override
	public boolean requestFocus(Component arg0, boolean arg1, boolean arg2, long arg3) {
		Toolkit tk = Toolkit.getDefaultToolkit();
	    EventQueue q = tk.getSystemEventQueue();
	    q.postEvent(new FocusEvent(awtComponent, FocusEvent.FOCUS_GAINED, false));
		return true;
	}

	@Override
	public boolean requestFocus(Component arg0, boolean arg1, boolean arg2, long arg3, Cause arg4) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return false;
	}

	@Override
	public void reshape(int arg0, int arg1, int arg2, int arg3) {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public void setBackground(Color arg0) {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public void setBounds(int arg0, int arg1, int arg2, int arg3) {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public void setBounds(int arg0, int arg1, int arg2, int arg3, int arg4) {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public void setCursor(Cursor arg0) {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public void setEnabled(boolean arg0) {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public void setEventMask(long arg0) {
		//not longer needed
	}

	@Override
	public void setFont(Font arg0) {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public void setForeground(Color arg0) {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public void setVisible(boolean arg0) {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public void show() {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public void updateCursorImmediately() {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

}
