package com.asynclife.basic.nio;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

public class NIOFiles {

	@Test
	public void testPath() {
		Path currentDir = Paths.get(".");
		System.out.println(currentDir.toAbsolutePath());
		System.out.println(currentDir.normalize().toAbsolutePath());
	}
	
}
