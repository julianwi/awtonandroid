package julianwi.awtpeer;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import android.util.Log;
import android.widget.Button;

public class PipeListener extends Thread {
	
	private WindowActivity context;
	
	public PipeListener(WindowActivity context) {
		this.context = context;
	}

	@Override
    public void run() {
        try {
        	FileReader fr = new FileReader(new File("/data/data/julianwi.awtpeer/pipe"));
        	while(true){
        		byte buf =(byte) fr.read();
        		if(buf == 0x01){ //check if id is 01
        			System.out.println("get from pipe: "+buf);
        			byte[] label = new byte[fr.read()]; //read length of the lable
        			for(int i=0;i<label.length;i++){
        				label[i]=(byte) fr.read();
        				System.out.println("new char "+label[i]);
        			}
        			final Button b1 = new Button(context);
        			b1.setText(new String(label));
        			context.runOnUiThread(new Runnable() {
						@Override
						public void run() {
							context.view.addView(b1);
						}
					});
        		}
        	}
        } catch (IOException e) {
        	//TODO implement error dialogs
            Log.e(getClass().getName(), e.getMessage());
        }
    }
}
