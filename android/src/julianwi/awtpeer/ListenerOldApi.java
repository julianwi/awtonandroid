package julianwi.awtpeer;

import java.io.IOException;

import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;

public class ListenerOldApi implements Callback {

	private WindowActivity window;
	private PipeListener pl;

	public ListenerOldApi(WindowActivity wa) {
		window = wa;
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		System.out.println("surface created");
		if(pl == null){
			pl = new PipeListener(holder, window);
			pl.start();
		}
		else{
			try {
				DisplayMetrics metrics = new DisplayMetrics();
				window.getWindowManager().getDefaultDisplay().getMetrics(metrics);
				synchronized (window.pipeout) {
					window.pipeout.write(0x01);
					window.pipeout.writeInt(metrics.widthPixels);
					window.pipeout.writeInt(metrics.heightPixels);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		System.out.println("surfaceChanged "+width+" * "+height);
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
	}

}
