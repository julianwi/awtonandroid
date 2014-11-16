package julianwi.awtpeer;

import java.awt.image.WritableRaster;
import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;

public class OnScreenGraphics2D extends AndroidGraphics2D {

	private AndroidWindowPeer awp;

	public OnScreenGraphics2D(WritableRaster destinationRaster, AndroidWindowPeer androidWindowPeer) {
		super(destinationRaster);
		awp = androidWindowPeer;
	}
	
	@Override
	public void dispose() {
		try {
			synchronized (awp.pipeout) {
				awp.pipeout.write(0x08);
				WritableByteChannel channel = Channels.newChannel(awp.pipeout);
				((DirectDataBufferInt)awp.destinationRaster.getDataBuffer()).buffer.position(0);
				channel.write(((DirectDataBufferInt)awp.destinationRaster.getDataBuffer()).buffer);
				awp.pipeout.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		super.dispose();
	}

}
