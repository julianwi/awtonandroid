package julianwi.awtpeer;

import java.awt.Font;
import java.awt.FontMetrics;
import java.util.HashMap;

public class AndroidFontMetrics extends FontMetrics {

	private HashMap metricsCache;

	protected AndroidFontMetrics(Font arg0) {
		super(arg0);
		System.out.println("constructing font metrics");
		metricsCache = new HashMap();
	}

}
