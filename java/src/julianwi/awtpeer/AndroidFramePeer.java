package julianwi.awtpeer;

import java.awt.Frame;
import java.awt.Image;
import java.awt.MenuBar;
import java.awt.Rectangle;
import java.awt.peer.FramePeer;

public class AndroidFramePeer extends AndroidWindowPeer implements FramePeer {
	
	private String title;

	/*Process p = Runtime.getRuntime().exec(new String[]{"sh", "/system/bin/am", "start", "julianwi.awtpeer/.WindowActivity"});
			p.waitFor();*/

	public AndroidFramePeer(Frame frame) {
		super(frame);
	    setTitle(frame.getTitle());
	}

	@Override
	public void setIconImage(Image image) {
		throw new UnsupportedOperationException("Not yet implemented.");
		
	}

	@Override
	public void setMenuBar(MenuBar mb) {
		throw new UnsupportedOperationException("Not yet implemented.");
		
	}

	@Override
	public void setResizable(boolean resizable) {
		throw new UnsupportedOperationException("Not yet implemented.");
		
	}

	@Override
	public void setTitle(String title) {
		this.title = title; 
	}

	@Override
	public int getState() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return 0;
	}

	@Override
	public void setState(int state) {
		throw new UnsupportedOperationException("Not yet implemented.");
		
	}

	@Override
	public void setMaximizedBounds(Rectangle r) {
		throw new UnsupportedOperationException("Not yet implemented.");
		
	}

	@Override
	public void setBoundsPrivate(int x, int y, int width, int height) {
		throw new UnsupportedOperationException("Not yet implemented.");
		
	}

	@Override
	public Rectangle getBoundsPrivate() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

}
