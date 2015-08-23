package util.base64;

import java.io.UnsupportedEncodingException;

import org.junit.Assert;
import org.junit.Test;

import com.asynclife.util.base64.Base64;

public class Base64Test {

	@Test
	public void test() throws UnsupportedEncodingException {
		String str = "参数1";
		
		// 获取指定编码格式的字节数组
		byte[] srcBytes = str.getBytes("UTF-8");
		String base64Str = Base64.encode(srcBytes);
		System.out.println(base64Str);
		
		byte[] decodedBytes = Base64.decode(base64Str);
		String recoverStr = new String(decodedBytes, "UTF-8");
		System.out.println(recoverStr);
		
		Assert.assertEquals(str, recoverStr);
	}
	
	
}
