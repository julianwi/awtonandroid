package julianwi.awtpeer;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;

public class PipeListener extends Thread {
	
	private SurfaceHolder holder;
	private WindowActivity window;
	
	public PipeListener(SurfaceHolder holder, WindowActivity wa) {
		this.holder = holder;
		window = wa;
	}

	@Override
    public void run() {
        try {
        	Thread.sleep(1000);
        	InputStream fr = new FileInputStream(new File("/data/data/julianwi.awtpeer/pipe"));
        	Canvas c = null;
        	while(true){
        		byte buf =(byte) fr.read();
        		System.out.println("get from pipe: "+buf);
        		if(buf == 0x02){
        			DisplayMetrics metrics = new DisplayMetrics();
        			window.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        			synchronized (window.pipeout) {
        				window.pipeout.write(0x01);
            			window.pipeout.writeInt(metrics.widthPixels);
            			window.pipeout.writeInt(metrics.heightPixels);
					}
        			window.pipeout.flush();
        		}
        		if(buf == 0x08){
        			DisplayMetrics metrics = new DisplayMetrics();
        			window.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        			System.out.println(metrics.widthPixels+"*"+metrics.heightPixels);
        			byte[] buffer = new byte[metrics.widthPixels*metrics.heightPixels*4];
        			fr.read(buffer);
        			IntBuffer intBuf =
					   ByteBuffer.wrap(buffer)
					     .order(ByteOrder.LITTLE_ENDIAN)
					     .asIntBuffer();
					int[] array = new int[intBuf.remaining()];
					intBuf.get(array);
        			c = holder.lockCanvas(null);
					c.drawBitmap(array, 0, metrics.widthPixels, 0, 0, metrics.widthPixels, metrics.heightPixels, false, null);
					holder.unlockCanvasAndPost(c);
        			c = null;
        		}
        		//exit if pipe is closed
        		if(buf == -1){
        			fr.close();
        			System.exit(0);
        		}
        	}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
