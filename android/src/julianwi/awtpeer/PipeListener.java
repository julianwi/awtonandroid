package julianwi.awtpeer;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import android.app.NativeActivity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.view.SurfaceHolder;

public class PipeListener extends Thread {
	
	private SurfaceHolder holder;
	public static Paint paint;
	
	public PipeListener(SurfaceHolder holder) {
		this.holder = holder;
		paint = new Paint();
        paint.setColor(0xFF0000);
        paint.setAlpha(255);
        paint.setStrokeWidth(2.0f);
        paint.setStyle(Paint.Style.STROKE);
	}

	@Override
    public void run() {
        try {
        	Thread.sleep(1000);
        	InputStream fr = new DataInputStream(new BufferedInputStream(new FileInputStream(new File("/data/data/julianwi.awtpeer/pipe"))));
        	Canvas c = null;
        	while(true){
        		byte buf =(byte) fr.read();
        		System.out.println("get from pipe: "+buf);
        		if(c == null && buf != 0x00){
        			c = holder.lockCanvas(null);
        		}
        		if(buf == 0x02){
        			FileOutputStream pipeout = new FileOutputStream("/data/data/julianwi.awtpeer/returnpipe");
        			ByteBuffer bb = ByteBuffer.allocate(4*2);
        			System.out.println("writing width: "+c.getWidth()+" heigth: "+c.getHeight());
        			bb.putInt(c.getWidth());
        			bb.putInt(c.getHeight());
        			pipeout.write(0x01);
        			pipeout.write(bb.array());
        			System.out.println("writing bytes "+bb.array().length);
        			pipeout.flush();
        			pipeout.close();
        		}
        		if(buf == 0x08){
        			byte[] buffer = new byte[c.getWidth()*c.getHeight()*4];
        			fr.read(buffer);
        			IntBuffer intBuf =
					   ByteBuffer.wrap(buffer)
					     .order(ByteOrder.LITTLE_ENDIAN)
					     .asIntBuffer();
					int[] array = new int[intBuf.remaining()];
					intBuf.get(array);
					c.drawBitmap(array, 0, c.getWidth(), 0, 0, c.getWidth(), c.getHeight(), false, null);
        			buf = 0x00;
        		}
        		if(buf == 0x00){
        			//context.view.postInvalidate();
        			if(c != null){
	        			holder.unlockCanvasAndPost(c);
	        			c = null;
	        			System.out.println("invalidated");
        			}
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
