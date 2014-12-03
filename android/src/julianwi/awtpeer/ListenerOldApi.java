package julianwi.awtpeer;

import java.io.DataOutputStream;
import java.io.FileDescriptor;
import java.io.IOException;

import android.util.DisplayMetrics;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;

public class ListenerOldApi implements Callback {

	private WindowActivity window;
	
	private native void listsocket(Surface suface, FileDescriptor fd);

	public ListenerOldApi(WindowActivity wa) {
		window = wa;
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		System.out.println("surface created");
		if(window.socket == null){
			DisplayMetrics metrics = new DisplayMetrics();
			window.getWindowManager().getDefaultDisplay().getMetrics(metrics);
			try {
				window.socket = WindowActivity.server.accept();
				window.pipeout = new DataOutputStream(window.socket.getOutputStream());
				synchronized (window.pipeout) {
	    			window.pipeout.writeInt(metrics.widthPixels);
	    			window.pipeout.writeInt(metrics.heightPixels);
				}
				window.pipeout.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			listsocket(holder.getSurface(), window.socket.getFileDescriptor());
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
