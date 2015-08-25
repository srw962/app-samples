package com.asynclife.util.format;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

public class StringFormatter {
	
	@Test
	public void test() {
		String str = "129018";
		String str2 = String.format("%10s", str).replace(' ', '0');
		System.out.println(str2);
		
		String str3 = StringUtils.leftPad(str2, 10, '0');
		System.out.println(str3);
	}
	
	
}
