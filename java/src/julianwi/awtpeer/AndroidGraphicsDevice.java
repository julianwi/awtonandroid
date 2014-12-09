package julianwi.awtpeer;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;

public class AndroidGraphicsDevice extends GraphicsDevice {
	
	private AndroidGraphicsConfiguration defaultConfiguration;

	@Override
	public GraphicsConfiguration[] getConfigurations() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public GraphicsConfiguration getDefaultConfiguration() {
		if(defaultConfiguration == null) {
			defaultConfiguration = new AndroidGraphicsConfiguration(this);
		}
		return defaultConfiguration;
	}

	@Override
	public String getIDstring() {
		throw new UnsupportedOperationException("Not yet implemented.");
		//return null;
	}

	@Override
	public int getType() {
		return TYPE_RASTER_SCREEN;
	}

}
