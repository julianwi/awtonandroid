package julianwi.awtpeer;

import java.awt.image.WritableRaster;

public class OnScreenGraphics2D extends AndroidGraphics2D {

	private AndroidWindowPeer awp;

	public OnScreenGraphics2D(WritableRaster destinationRaster, AndroidWindowPeer androidWindowPeer) {
		super(destinationRaster);
		awp = androidWindowPeer;
	}
	
	@Override
	public void dispose() {
		awp.grchanged = true;
		if(!awp.rt.isAlive()){
			awp.rt = new RefreshThread(awp);
			awp.rt.start();
		}
		super.dispose();
	}

}
