package julianwi.awtpeer;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.nio.ByteBuffer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.BitmapDrawable;
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
        		if(buf == 0x01){ //check if id is 01
        			byte[] array = new byte[4*4];
        			for(int i=0;i<4*4;i++){
        				array[i] = (byte) fr.read();
        				//System.out.println("readed "+array[i]);
        			}
        			ByteBuffer wrapped = ByteBuffer.wrap(array);
        			//System.out.println("have to  fill rect "+wrapped.getInt()+wrapped.getInt()+wrapped.getInt()+wrapped.getInt());
        			paint.setStyle(Paint.Style.FILL);
        			int x1 = wrapped.getInt();
        			int y1 = wrapped.getInt();
        			c.drawRect(new Rect(x1, y1, x1 + wrapped.getInt(), y1+wrapped.getInt()), paint);
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
        		if(buf == 0x03){
        			byte[] array = new byte[4];
        			for(int i=0;i<4;i++){
        				array[i] = (byte) fr.read();
        			}
        			ByteBuffer wrapped = ByteBuffer.wrap(array);
        			paint.setARGB(wrapped.get(), wrapped.get(), wrapped.get(), wrapped.get());
        		}
        		if(buf == 0x04){
        			byte[] array = new byte[3*4];
        			for(int i=0;i<3*4;i++){
        				array[i] = (byte) fr.read();
        			}
        			ByteBuffer wrapped = ByteBuffer.wrap(array);
        			int x = wrapped.getInt();
        			int y = wrapped.getInt();
        			byte[] label = new byte[wrapped.getInt()]; //read length of the lable
        			for(int i=0;i<label.length;i++){
        				label[i]=(byte) fr.read();
        				System.out.println("new char "+label[i]);
        			}
        			c.drawText(new String(label), x, y, paint);
        		}
        		if(buf == 0x05){
        			byte[] array = new byte[4*4];
        			for(int i=0;i<4*4;i++){
        				array[i] = (byte) fr.read();
        				System.out.println("readed "+array[i]);
        			}
        			ByteBuffer wrapped = ByteBuffer.wrap(array);
        			paint.setStyle(Paint.Style.FILL);
        			c.drawLine(wrapped.getInt(), wrapped.getInt(), wrapped.getInt(), wrapped.getInt(), paint);
        		}
        		if(buf == 0x06){
        			paint.setAlpha(fr.read());
        		}
        		if(buf == 0x07){
        			DataInputStream stream = (DataInputStream) fr;
        			int width = stream.readInt();
        			int height = stream.readInt();
        			System.out.println(width+" "+height);
        			Bitmap bm = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        			for(int x = 0; x < width; x++){
						for(int y = 0; y < height; y++){
							int color = stream.readInt();
							if(color != 0){
								bm.setPixel(x, y, color);
							}
						}
        			}
        			c.drawBitmap(bm, 0, 0, null);
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
        			System.exit(0);
        		}
        	}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
