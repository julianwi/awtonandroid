package julianwi.awtpeer;

import java.io.DataOutputStream;
import java.io.FileDescriptor;
import java.io.IOException;

import android.app.Activity;
import android.content.res.Configuration;
import android.net.LocalServerSocket;
import android.net.LocalSocket;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.Window;
import android.view.SurfaceView;

public class WindowActivity extends Activity {
	
	public DataOutputStream pipeout;
	public LocalSocket socket = new LocalSocket();
	public static LocalServerSocket server;
	
	static{
		System.loadLibrary("awtpeer");
	}
	
	private native FileDescriptor createsock();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			if(server == null){
				server = new LocalServerSocket(createsock());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			pipeout = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			Window.class.getMethod("takeSurface", Class.forName("android.view.SurfaceHolder$Callback2")).invoke(getWindow(), Class.forName("julianwi.awtpeer.ListenerNewApi").getConstructor(WindowActivity.class).newInstance(this));
			System.out.println("succes!");
		} catch (Exception e) {
			System.out.println("error");
			e.printStackTrace();
			//view = new GraphicsView(this);
			SurfaceView view = new SurfaceView(this);
			view.getHolder().addCallback(new ListenerOldApi(this));
			setContentView(view);
		}
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		if(-1<ev.getAction()&&ev.getAction()<3){
			try {
				synchronized (pipeout) {
					pipeout.write(0x02+ev.getAction());
					pipeout.writeInt((int) ev.getX());
					pipeout.writeInt((int) ev.getY());
				}
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		try {
			DisplayMetrics metrics = new DisplayMetrics();
			getWindowManager().getDefaultDisplay().getMetrics(metrics);
			synchronized (pipeout) {
				pipeout.write(0x01);
				pipeout.writeInt(metrics.widthPixels);
				pipeout.writeInt(metrics.heightPixels);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}