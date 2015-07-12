package com.asynclife.util;

import org.joda.time.DateTime;

public class DateTimeHelper {
	
	public static long now() {
		return new DateTime().getMillis();
	}
	
}
