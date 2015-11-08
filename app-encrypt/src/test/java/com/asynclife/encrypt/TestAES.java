package com.asynclife.encrypt;

import org.junit.Test;

public class TestAES {
	
	@Test
	public void testAES() throws Exception {
		String text = "一段文字";
		
		sop("加密前："+text);

		byte[] keyBytes = MyEncrypt.genMyAESKey(128, "my simple seed for aes".getBytes());
		
		byte[] srcBytes = text.getBytes("UTF-8");
		byte[] encodedBytes = MyEncrypt.encryptAES(MyEncrypt.KEY_AES, keyBytes, srcBytes);
		String hexStr = MyEncrypt.encodeHexString(encodedBytes);
		sop("加密后的字符串："+hexStr);
		
		byte[] hex2Bytes = MyEncrypt.decodeHexString(hexStr.toCharArray());
		byte[] decodedBytes = MyEncrypt.decryptAES(MyEncrypt.KEY_AES, keyBytes, hex2Bytes);
		String decodeStr = new String(decodedBytes, "UTF-8");
		sop("解密后："+decodeStr);
		//3516587e767912edcb20f08a2d85a199
	}

	private void sop(String string) {
		System.out.println(string);
	}
	
}
