package com.asynclife.encrypt;

import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

public class MyEncrypt {

	private static final String KEY_MD5 = "MD5";
	private static final String KEY_SHA256 = "SHA-256";

	/**
	 * BASE64编码字符串
	 * 
	 * @param bytes
	 * @return
	 */
	public static byte[] encodeBase64(byte[] bytes) {
		return Base64.encodeBase64(bytes);
	}

	/**
	 * BASE64解码字符串
	 * 
	 * @param base64Str
	 * @return
	 */
	public static byte[] decodeBase64(String base64Str) {
		return Base64.decodeBase64(base64Str);
	}

	/**
	 * MD5加密
	 * 
	 * @param data
	 * @return
	 */
	public static String encryptMD5(String string) {
		try {
			MessageDigest m = MessageDigest.getInstance(KEY_MD5);
			byte[] bytes = string.getBytes();
			m.update(bytes, 0, bytes.length);
			BigInteger i = new BigInteger(1, m.digest());
			return String.format("%1$032x", i);
			/**
			 * %1$032x %1$ 第1个参数的占位符 0 若内容长度不足最小宽度，则在左边用0来填充 32 字符串宽度为32位 x
			 * 数据类型：16进制整数
			 */
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 使用DigestUtils
	 * 
	 * @param text
	 * @return
	 */
	public static String getMD5(String text) {
		return DigestUtils.md5Hex(text);
	}

	/**
	 * SHA加密
	 * 
	 * @param data
	 * @return
	 */
	public static String encryptSHA256(String string) {
		try {
			MessageDigest m = MessageDigest.getInstance(KEY_SHA256);
			byte[] bytes = string.getBytes();
			m.update(bytes, 0, bytes.length);
			BigInteger i = new BigInteger(1, m.digest());
			return String.format("%1$032x", i);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 使用DigestUtils
	 * 
	 * @param text
	 * @return
	 */
	public static String getSHA256(String text) {
		return DigestUtils.sha256Hex(text);
	}

	public static String getSHA512(String text) {
		return DigestUtils.sha512Hex(text);
	}

	// ---> 3DES BEGIN

	public static final String KEY_TRIPLE_DES = "DESede";
	public static final String KEY_AES = "AES";

	/**
	 * keysize: must be equal to 112 or 168
	 * seed: 指定1个seed，确保每次加密、解密使用的都是同一个key
	 * @param seed
	 * @return
	 */
	public static byte[] genMyTripleDESKey(int keySize, byte[] seed) {
		if(keySize != 112 && keySize != 168) {
			throw new RuntimeException("3DES keysize: must be equal to 112 or 168");
		}
		try {
			SecureRandom secureRandom = null;
			if (seed != null) {
				secureRandom = new SecureRandom(seed);
			} else {
				secureRandom = new SecureRandom();
			}
			KeyGenerator kgen = KeyGenerator.getInstance(KEY_TRIPLE_DES);
			kgen.init(keySize, secureRandom);
			SecretKey secretKey = kgen.generateKey();
			byte[] keyBytes = secretKey.getEncoded();
			return keyBytes;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * AES keysize: must be equal to 128, 192 or 256（仅keysize=128测试成功，192和256报错）
	 * seed: 指定1个seed，确保每次加密、解密使用的都是同一个key
	 * @param seed
	 * @return
	 */
	public static byte[] genMyAESKey(int keySize, byte[] seed) {
		if(keySize != 128 && keySize != 192 && keySize != 256) {
			throw new RuntimeException("AES keysize: must be equal to 128, 192 or 256");
		}
		try {
			SecureRandom secureRandom = null;
			if (seed != null) {
				secureRandom = new SecureRandom(seed);
			} else {
				secureRandom = new SecureRandom();
			}
			KeyGenerator kgen = KeyGenerator.getInstance(KEY_AES);
			kgen.init(keySize, secureRandom);
			SecretKey secretKey = kgen.generateKey();
			byte[] keyBytes = secretKey.getEncoded();
			return keyBytes;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static byte[] encrypt3DES(String algorithm, byte[] keybyte,
			byte[] src) {
		return symmetryEncrypt(algorithm, keybyte, src);
	}

	public static byte[] decrypt3DES(String algorithm, byte[] keybyte,
			byte[] src) {
		return symmetryDecrypt(algorithm, keybyte, src);
	}

	public static byte[] encryptAES(String algorithm, byte[] keybyte, byte[] src) {
		return symmetryEncrypt(algorithm, keybyte, src);
	}

	public static byte[] decryptAES(String algorithm, byte[] keybyte, byte[] src) {
		return symmetryDecrypt(algorithm, keybyte, src);
	}

	private static byte[] symmetryEncrypt(String algorithm, byte[] keybyte,
			byte[] src) {
		try {
			SecretKey desKey = new SecretKeySpec(keybyte, algorithm);
			Cipher ci = Cipher.getInstance(algorithm);
			ci.init(Cipher.ENCRYPT_MODE, desKey);
			return ci.doFinal(src);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static byte[] symmetryDecrypt(String algorithm, byte[] keybyte,
			byte[] src) {
		try {
			SecretKey desKey = new SecretKeySpec(keybyte, algorithm);
			Cipher ci = Cipher.getInstance(algorithm);
			ci.init(Cipher.DECRYPT_MODE, desKey);
			return ci.doFinal(src);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	// <--- 3DES END

	/**
	 * 字节数组 to HEX
	 * 
	 * @param bytes
	 * @return
	 */
	public static String encodeHexString(byte[] bytes) {
		// DatatypeConverter.printHexBinary(bytes);
		return Hex.encodeHexString(bytes);
	}

	/**
	 * HEX to 字节数组
	 * 
	 * @param chars
	 * @return
	 */
	public static byte[] decodeHexString(char[] chars) {
		try {
			return Hex.decodeHex(chars);
		} catch (DecoderException e) {
			throw new RuntimeException(e);
		}
	}

	
	
	// ---> RSA BEGIN
	public static final String KEY_RSA = "RSA";
	public static final String PUBLIC_KEY = "RSAPublicKey";
	public static final String PRIVATE_KEY = "RSAPrivateKey";
	public final static String SIGNATURE_ALGORITHM = "MD5withRSA";
	
	public static Map<String, Object> genKeyPair() {
		Map<String, Object> keyPairMap = new HashMap<String, Object>();
		
		try {
			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_RSA);
			keyPairGen.initialize(1024);
			
			KeyPair pair = keyPairGen.generateKeyPair();
			
			RSAPublicKey publicKey = (RSAPublicKey) pair.getPublic();
			RSAPrivateKey pirvateKey = (RSAPrivateKey) pair.getPrivate();
			
			keyPairMap.put(PUBLIC_KEY, publicKey);
			keyPairMap.put(PRIVATE_KEY, pirvateKey);
			return keyPairMap;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	/**
	 * 获取RSA加密算法的公钥
	 * @param keyMap
	 * @return BASE64编码后的公钥
	 */
	public static String getPublicKey(Map<String, Object> keyMap) {
		Key key = (Key) keyMap.get(PUBLIC_KEY);  
		byte[] pubKeyBytes = key.getEncoded();
		byte[] base64 = encodeBase64(pubKeyBytes);
        return new String(base64);
	}
	
	/**
	 * 获取RSA加密算法的私钥
	 * @param keyMap
	 * @return BASE64编码后的私钥
	 */
	public static String getPrivateKey(Map<String, Object> keyMap) {
		Key key = (Key) keyMap.get(PRIVATE_KEY);  
		byte[] privateKeyBytes = key.getEncoded();
		byte[] base64 = encodeBase64(privateKeyBytes);
        return new String(base64);
	}
	
	/**
	 * 私钥对数据进行加密
	 * @param base64PrivateKey
	 * @param srcData
	 * @return
	 */
	public static byte[] encryptByPrivateKey(String base64PrivateKey, byte[] srcData) {
		try {
			Key privateKey = getPrivateKey(base64PrivateKey);
			
			// 对数据进行加密
			Cipher ci = Cipher.getInstance(privateKey.getAlgorithm());
			ci.init(Cipher.ENCRYPT_MODE, privateKey);
			return ci.doFinal(srcData);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 私钥对数据进行解密
	 * @param base64PrivateKey
	 * @param srcData
	 * @return
	 */
	public static byte[] decryptByPrivateKey(String base64PrivateKey, byte[] srcData) {
		try {
			Key privateKey = getPrivateKey(base64PrivateKey);
			
			// 对数据进行加密
			Cipher ci = Cipher.getInstance(privateKey.getAlgorithm());
			ci.init(Cipher.DECRYPT_MODE, privateKey);
			return ci.doFinal(srcData);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	
	/**
	 * 公钥对数据进行加密
	 * @param base64PublicKey
	 * @param srcData
	 * @return
	 */
	public static byte[] encryptByPublicKey(String base64PublicKey, byte[] srcData) {
		try {
			Key privateKey = getPublicKey(base64PublicKey);
			
			// 对数据进行加密
			Cipher ci = Cipher.getInstance(privateKey.getAlgorithm());
			ci.init(Cipher.ENCRYPT_MODE, privateKey);
			return ci.doFinal(srcData);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 公钥对数据进行解密
	 * @param base64PublicKey
	 * @param srcData
	 * @return
	 */
	public static byte[] decryptByPublicKey(String base64PublicKey, byte[] srcData) {
		try {
			Key privateKey = getPublicKey(base64PublicKey);
			
			// 对数据进行加密
			Cipher ci = Cipher.getInstance(privateKey.getAlgorithm());
			ci.init(Cipher.DECRYPT_MODE, privateKey);
			return ci.doFinal(srcData);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static Key getPublicKey(String base64PublicKey)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		// BASE64解码得到公钥
		byte[] keyBytes = decodeBase64(base64PublicKey);
		
		// 获取公钥
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_RSA);
		Key publicKey = keyFactory.generatePublic(x509KeySpec);
		return publicKey;
	}
	
	private static Key getPrivateKey(String base64PrivateKey)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		// BASE64解码得到私钥
		byte[] keyBytes = decodeBase64(base64PrivateKey);
		
		// 获取私钥
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_RSA);
		Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
		return privateKey;
	}
	
	/**
	 * 用私钥对信息生成数字签名 
	 * @param base64PrivateKey 私钥的base64字符串
	 * @param data	加密后的密文
	 * @return
	 */
	public static String sign(byte[] data, String base64PrivateKey) {
		try {
			// 私钥
			PrivateKey prikey = (PrivateKey) getPrivateKey(base64PrivateKey);
			
			// 签名
			Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
			signature.initSign(prikey);
			signature.update(data);
			
			byte[] base64Bytes = encodeBase64(signature.sign());
			String base64Sign = new String(base64Bytes);
			return base64Sign;
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}
	
	public static boolean verify(byte[] data, String sign, String base64PublicKey) {
		
		// 公钥
		try {
			PublicKey pubKey = (PublicKey) getPublicKey(base64PublicKey);
			Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
			signature.initVerify(pubKey);
			signature.update(data);
			
			return signature.verify(decodeBase64(sign));
		} catch (Exception e) {
			throw new RuntimeException(e);
			
		}
		
	}
	
	// <--- RSA END
	
}
