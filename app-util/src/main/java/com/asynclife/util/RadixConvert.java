package com.asynclife.util;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import org.apache.commons.codec.binary.BinaryCodec;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

public class RadixConvert {
	
	@Test
	public void test() {
		String hexStr = "0x11";
		Integer i = Integer.decode(hexStr);
		String binStr = Integer.toBinaryString(i);
		System.out.println(binStr);
		System.out.println(StringUtils.leftPad(binStr, 8, '0'));
		
		
		binStr = "00010001";
		int ii = Integer.parseInt(binStr, 2);
		System.out.println(ii);
	
		
		String temp = "ABFAB"; // without the "0x"
		String bytes = Integer.toBinaryString(Integer.parseInt(temp, 16)); 
		System.out.println(bytes);
	}
	
	@Test
	public void test02() throws Exception {
		String s = "13696084456";
		byte[] bytes = s.getBytes("GBK");
		System.out.println(Arrays.toString(bytes));
		
		// binary to hex
		System.out.println(bytesToHexString(bytes));
		
	}
	
	public static final String bytesToHexString(byte[] bArray) {
	    StringBuffer sb = new StringBuffer(bArray.length);
	    String sTemp;
	    for (int i = 0; i < bArray.length; i++) {
	     sTemp = Integer.toHexString(0xFF & bArray[i]);
	     if (sTemp.length() < 2)
	      sb.append(0);
	     sb.append(sTemp.toUpperCase());
	    }
	    return sb.toString();
	}
}
