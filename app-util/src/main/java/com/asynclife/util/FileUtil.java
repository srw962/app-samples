package com.asynclife.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.apache.commons.io.IOUtils;

public class FileUtil 
{
    public static InputStream getInputStreamFromClasspath(String filename) {
    	
    	return FileUtil.class.getClassLoader().getResourceAsStream(filename);
    }
    
    public static String toString(InputStream is) throws IOException {
    	return IOUtils.toString(is);
    }
    
    public static void copyByCharset(InputStream fis, String inCharset, 
    		OutputStream fos, String outCharset) throws IOException {
		// 输入流的字符编码表
		InputStreamReader isr = new InputStreamReader(fis, inCharset);
		
		// 输出流的字符编码表
		OutputStreamWriter osw = new OutputStreamWriter(fos, outCharset);
		
		IOUtils.copy(isr, osw);
		osw.flush();
		
		IOUtils.closeQuietly(isr);
		IOUtils.closeQuietly(osw);
    }
}
