package julianwi.awtpeer;

import java.io.File;
import android.app.Activity;
import android.os.Bundle;

public class WindowActivity extends Activity {
	
	public GraphicsView view;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(!new File("/data/data/julianwi.awtpeer/pipe").exists()){
			try {
				String bbpath = createPackageContext("julianwi.javainstaller", 0).getSharedPreferences("settings", 1).getString("path1", "busybox");
				System.out.println(bbpath);
				Runtime.getRuntime().exec(bbpath+" mkfifo /data/data/julianwi.awtpeer/pipe");
				Runtime.getRuntime().exec(bbpath+" mkfifo /data/data/julianwi.awtpeer/returnpipe");
				Runtime.getRuntime().exec(bbpath+" chmod 0666 /data/data/julianwi.awtpeer/pipe");
				Runtime.getRuntime().exec(bbpath+" chmod 0666 /data/data/julianwi.awtpeer/returnpipe");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		view = new GraphicsView(this);
		setContentView(view);
		new PipeListener(this).start();
	}
}