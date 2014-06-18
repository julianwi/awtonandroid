package julianwi.awtpeer;

import java.io.File;
import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class WindowActivity extends Activity {
	
	public LinearLayout view;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(!new File("/data/data/julianwi.awtpeer/pipe").exists()){
			try {
				String bbpath = createPackageContext("julianwi.javainstaller", 0).getSharedPreferences("settings", 1).getString("path1", "busybox");
				System.out.println(bbpath);
				Runtime.getRuntime().exec(bbpath+" mkfifo /data/data/julianwi.awtpeer/pipe");
				Runtime.getRuntime().exec(bbpath+" chmod 0666 /data/data/julianwi.awtpeer/pipe");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		new PipeListener(this).start();
		view = new LinearLayout(this);
		setContentView(view);
	}
}