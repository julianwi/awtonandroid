package julianwi.awtpeer;

import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;

public class RefreshThread extends Thread {
	
	private AndroidWindowPeer awp;

	public RefreshThread(AndroidWindowPeer awp) {
		this.awp = awp;
	}

	@Override
	public void run() {
		while (awp.grchanged) {
			awp.grchanged = false;
			try {
				awp.pipeout.write(0x08);
				WritableByteChannel channel = Channels.newChannel(awp.pipeout);
				((DirectDataBufferInt)awp.destinationRaster.getDataBuffer()).buffer.position(0);
				channel.write(((DirectDataBufferInt)awp.destinationRaster.getDataBuffer()).buffer);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
