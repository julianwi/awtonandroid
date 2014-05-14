package java.awt;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Frame extends Window {
	public String title;

	public Frame(){
	    this("");
	}
	
	public Frame(String title){
	    super();
	    this.title = title;
	    visible = false;
	}
	
	@Override
	public void show(){
	    if(! visible){
	    	visible = true;
	    	System.out.println("am start julianwi.javainstaller/julianwi.javainstaller.MainActivity");
	    	//Process p = Runtime.getRuntime().exec("su -c");
	    	Process p;
			try {
				p = Runtime.getRuntime().exec(new String[]{"sh", "/system/bin/am", "start", "julianwi.awtpeer/.WindowActivity"});
				p.waitFor();
				BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
	            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
	            String s = null;
	            // read the output from the command
	            System.out.println("Here is the standard output of the command:\n");
	            while ((s = stdInput.readLine()) != null) {
	                System.out.println(s);
	            }
	            // read any errors from the attempted command
	            System.out.println("Here is the standard error of the command (if any):\n");
	            while ((s = stdError.readLine()) != null) {
	                System.out.println(s);
	            }
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
	}
}