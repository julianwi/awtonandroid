package julianwi.awtpeer;

import android.annotation.SuppressLint;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback2;

@SuppressLint("NewApi")
public class ListenerNewApi extends ListenerOldApi implements Callback2 {

	public ListenerNewApi(WindowActivity wa) {
		super(wa);
	}

	@Override
	public void surfaceRedrawNeeded(SurfaceHolder holder) {
	}

}
