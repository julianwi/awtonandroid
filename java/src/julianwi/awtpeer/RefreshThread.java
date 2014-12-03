package julianwi.awtpeer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

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
				ByteBuffer buffer = ((DirectDataBufferInt)awp.destinationRaster.getDataBuffer()).buffer;
				awp.pipeout.write(0x08);
				buffer.position(0);
				byte[] bytes = new byte [buffer.remaining()];
			    buffer.get(bytes);
			    awp.pipeout.write(bytes);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
