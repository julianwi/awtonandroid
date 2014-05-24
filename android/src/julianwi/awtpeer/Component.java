package julianwi.awtpeer;

import android.view.View;
import android.view.ViewGroup;

public class Component implements Runnable {
	
	private ViewGroup vg;
	private View component;
	
	public Component(ViewGroup vg, View component) {
		this.vg = vg;
		this.component = component;
	}

	@Override
	public void run() {
		vg.addView(component);
		
	}

}
