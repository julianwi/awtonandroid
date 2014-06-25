package julianwi.awtpeer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.ByteBuffer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Bitmap.Config;
import android.view.View;

public class GraphicsView extends View {
	
	public Canvas canvas;
	public Bitmap bitmap;

	public GraphicsView(Context context) {
		super(context);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		System.out.println("set height and width");
		setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));
		System.out.println("height: "+MeasureSpec.getSize(widthMeasureSpec)+" width: "+MeasureSpec.getSize(heightMeasureSpec));
		System.out.println("height: "+getHeight()+" width: "+getWidth());
		bitmap = Bitmap.createBitmap(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec), Config.ARGB_8888);
		canvas = new Canvas(bitmap);
		/*try {
			OutputStream returnpipe = new FileOutputStream(new File("/data/data/julianwi.awtpeer/returnpipe"));
			ByteBuffer bb = ByteBuffer.allocate(16);
			bb.putInt(MeasureSpec.getSize(widthMeasureSpec));
			bb.putInt(MeasureSpec.getSize(heightMeasureSpec));
			returnpipe.write(0x01);
			returnpipe.write(bb.array());
			returnpipe.close();
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}
	
	@SuppressLint("NewApi")
	protected void onDraw(Canvas canvas) {
		System.out.println("drawing");
		canvas.drawBitmap(bitmap, 0, 0, null);
		super.onDraw(canvas);
    }

}
