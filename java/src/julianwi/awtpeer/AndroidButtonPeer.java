package julianwi.awtpeer;

import java.awt.AWTEvent;
import java.awt.Button;
import java.awt.event.FocusEvent;
import java.awt.peer.ButtonPeer;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class AndroidButtonPeer extends AndroidComponentPeer implements ButtonPeer {
	
	private Button awtcomponent;
	
	public AndroidButtonPeer(Button b){
		super(b);
		awtcomponent = b;
		if(b.isShowing()){
			showinactivity();
		}
	}
	
	private void showinactivity(){
		try {
			OutputStream pipeout = new FileOutputStream("/data/data/julianwi.awtpeer/pipe");
			pipeout.write(0x01);
			pipeout.write(awtcomponent.getLabel().length());
			pipeout.write(awtcomponent.getLabel().getBytes());
			pipeout.flush();
			pipeout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void handleEvent(AWTEvent arg0) {
		System.out.println("recieved event: "+arg0.getID());
		if(arg0.getID()==FocusEvent.FOCUS_GAINED){
			showinactivity();
			return;
		}
		throw new UnsupportedOperationException("Not yet implemented.");
	}

	@Override
	public void setLabel(String arg0) {
		throw new UnsupportedOperationException("Not yet implemented.");

	}

}
