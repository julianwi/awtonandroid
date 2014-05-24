package julianwi.awtpeer;

import java.awt.Dialog;
import java.awt.Image;
import java.awt.Insets;
import java.awt.MenuBar;
import java.awt.Rectangle;
import java.awt.peer.FramePeer;

public class AndroidFramePeer extends AndroidComponentPeer implements FramePeer {

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
	
	@Override
	public void show() {
		try {
			Process p = Runtime.getRuntime().exec(new String[]{"sh", "/system/bin/am", "start", "julianwi.awtpeer/.WindowActivity"});
			p.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
