package com.asynclife.util.excel;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;

public class DateTimeUtil {

	public static Date getCurrentDate() {
		return DateTime.now().toDate();
	}

	public static String formatDate(Date date, String pattern) {
		return new SimpleDateFormat(pattern).format(date);
	}

}
