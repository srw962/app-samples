package com.asynclife.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

public class FileHelper 
{
    public static InputStream getInputStreamFromClasspath(String filename) {
    	
    	return FileHelper.class.getClassLoader().getResourceAsStream(filename);
    }
    
    public static String toString(InputStream is) throws IOException {
    	return IOUtils.toString(is);
    }
}
