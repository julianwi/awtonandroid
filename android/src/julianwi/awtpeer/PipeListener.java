package julianwi.awtpeer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.nio.ByteBuffer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Bitmap.Config;

public class PipeListener extends Thread {
	
	private WindowActivity context;
	public static Paint paint;
	
	public PipeListener(WindowActivity context) {
		this.context = context;
		paint = new Paint();
        paint.setColor(0xFF0000);
        paint.setAlpha(255);
        paint.setStrokeWidth(2.0f);
        paint.setStyle(Paint.Style.STROKE);
	}

	@Override
    public void run() {
        try {
        	FileReader fr = new FileReader(new File("/data/data/julianwi.awtpeer/pipe"));
        	while(true){
        		byte buf =(byte) fr.read();
        		if(buf == 0x01){ //check if id is 01
        			System.out.println("get from pipe: "+buf);
        			byte[] array = new byte[4*4];
        			for(int i=0;i<4*4;i++){
        				array[i] = (byte) fr.read();
        				System.out.println("readed "+array[i]);
        			}
        			ByteBuffer wrapped = ByteBuffer.wrap(array);
        			//System.out.println("have to  fill rect "+wrapped.getInt()+wrapped.getInt()+wrapped.getInt()+wrapped.getInt());
        			paint.setStyle(Paint.Style.FILL);
        			context.view.canvas.drawRect(new Rect(wrapped.getInt(), wrapped.getInt(), wrapped.getInt(), wrapped.getInt()), paint);
        			context.view.postInvalidate();
        			System.out.println("invalidated");
        			
					//Method m = Canvas.class.getMethod("drawRect", new Class[]{Rect.class, Paint.class});
					//Object[] args = new Object[]{new Rect(0, 0, 100, 200), paint};
        			/*byte[] label = new byte[fr.read()]; //read length of the lable
        			for(int i=0;i<label.length;i++){
        				label[i]=(byte) fr.read();
        				System.out.println("new char "+label[i]);
        			}
        			final Button b1 = new Button(context);
        			b1.setText(new String(label));
        			context.runOnUiThread(new Runnable() {
						@Override
						public void run() {
							//context.view.addView(b1);
						}
					});*/
        		}
        		if(buf == 0x02){
        			FileOutputStream pipeout = new FileOutputStream("/data/data/julianwi.awtpeer/returnpipe");
        			ByteBuffer bb = ByteBuffer.allocate(4*2);
        			System.out.println("writing width: "+context.view.getWidth()+" heigth: "+context.view.getHeight());
        			bb.putInt(context.view.getWidth());
        			bb.putInt(context.view.getHeight());
        			pipeout.write(0x01);
        			pipeout.write(bb.array());
        			System.out.println("writing bytes "+bb.array().length);
        			pipeout.flush();
        			pipeout.close();
        		}
        	}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
