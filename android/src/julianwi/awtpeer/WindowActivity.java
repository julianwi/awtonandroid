package julianwi.awtpeer;

import java.io.File;
import java.io.IOException;

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
				Runtime.getRuntime().exec("busybox mkfifo /data/data/julianwi.awtpeer/pipe");
				Runtime.getRuntime().exec("busybox chmod 0666 /data/data/julianwi.awtpeer/pipe");
			} catch (IOException e) {
				// TODO implement error dialogs
				e.printStackTrace();
			}
		}
		new PipeListener(this).start();
		view = new LinearLayout(this);
		setContentView(view);
	}
}