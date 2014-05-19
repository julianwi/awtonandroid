package julianwi.awtpeer;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.util.Locale;

public class AndroidGraphicsEnvironment extends GraphicsEnvironment {
	
	private AndroidGraphicsDevice defaultDevice;
	
	public AndroidGraphicsEnvironment() {
		defaultDevice = new AndroidGraphicsDevice();
	}

	@Override
	public Graphics2D createGraphics(BufferedImage arg0) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public Font[] getAllFonts() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public String[] getAvailableFontFamilyNames() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public String[] getAvailableFontFamilyNames(Locale arg0) {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public GraphicsDevice getDefaultScreenDevice() {
		return defaultDevice;
	}

	@Override
	public GraphicsDevice[] getScreenDevices() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

}
