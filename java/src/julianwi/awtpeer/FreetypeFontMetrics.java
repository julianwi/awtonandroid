package julianwi.awtpeer;

import java.awt.Font;
import java.awt.FontMetrics;

public class FreetypeFontMetrics extends FontMetrics {

	protected FreetypeFontMetrics(Font font) {
		super(font);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int getAscent(){
		return 20;
	}
	
	@Override
	public int getLeading(){
		return 10;
	}
	
	@Override
	public int charWidth(char ch){
		return 40;
	}
	
	@Override
	public int charsWidth(char[] data, int off, int len){
		return 40*(len-off);
	}

}
