package julianwi.awtpeer;

import java.awt.image.DataBuffer;
import java.nio.ByteBuffer;

public class DirectDataBufferInt extends DataBuffer {
	
	public ByteBuffer buffer;

	public DirectDataBufferInt(int size) {
		super(TYPE_INT, size, 1, 0);
		buffer = ByteBuffer.allocateDirect(size*4);
	}

	@Override
	public int getElem(int bank, int i) {
		return buffer.getInt(bank*size+i+offsets[bank]);
	}

	@Override
	public void setElem(int bank, int i, int val) {
		buffer.putInt(bank*size+i+offsets[bank], val);
	}

}
