package org.lanbo.util;

import org.joda.time.DateTime;

public class DateTimeHelper {
	
	public static long now() {
		return new DateTime().getMillis();
	}
	
}
