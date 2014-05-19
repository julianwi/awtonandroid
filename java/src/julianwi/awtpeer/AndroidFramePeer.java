package julianwi.awtpeer;

import java.awt.AWTEvent;
import java.awt.AWTException;
import java.awt.BufferCapabilities;
import java.awt.BufferCapabilities.FlipContents;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Insets;
import java.awt.MenuBar;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.PaintEvent;
import java.awt.image.ColorModel;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.VolatileImage;
import java.awt.peer.ContainerPeer;
import java.awt.peer.FramePeer;
import sun.awt.CausedFocusEvent.Cause;

public class AndroidFramePeer implements FramePeer {

	@Override
	public boolean requestWindowFocus() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return false;
	}

	@Override
	public void setAlwaysOnTop(boolean arg0) {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public void setModalBlocked(Dialog arg0, boolean arg1) {
		throw new UnsupportedOperationException("Not yet implemented.");

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
	public void updateFocusableWindowState() {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public void updateIconImages() {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public void updateMinimumSize() {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public void beginLayout() {
		//nothing to do here
	}

	@Override
	public void beginValidate() {
		//nothing to do here
	}

	@Override
	public void cancelPendingPaint(int arg0, int arg1, int arg2, int arg3) {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public void endLayout() {
		//nothing to do here
	}

	@Override
	public void endValidate() {
		//nothing to do here
	}

	@Override
	public Insets getInsets() {
		return new Insets(0, 0, 0, 0);
	}

	@Override
	public Insets insets() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public boolean isPaintPending() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return false;
	}

	@Override
	public void restack() {
		throw new UnsupportedOperationException("Not yet implemented.");

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
		throw new UnsupportedOperationException("Not yet implemented.");

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
	public boolean requestFocus(Component arg0, boolean arg1, boolean arg2,
			long arg3) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return false;
	}

	@Override
	public boolean requestFocus(Component arg0, boolean arg1, boolean arg2,
			long arg3, Cause arg4) {
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
		try {
			Process p = Runtime.getRuntime().exec(new String[]{"sh", "/system/bin/am", "start", "julianwi.awtpeer/.WindowActivity"});
			p.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateCursorImmediately() {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public Rectangle getBoundsPrivate() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public int getState() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return 0;
	}

	@Override
	public boolean isRestackSupported() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return false;
	}

	@Override
	public void setBoundsPrivate(int arg0, int arg1, int arg2, int arg3) {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public void setIconImage(Image arg0) {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public void setMaximizedBounds(Rectangle arg0) {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public void setMenuBar(MenuBar arg0) {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public void setResizable(boolean arg0) {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public void setState(int arg0) {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

	@Override
	public void setTitle(String arg0) {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

}
