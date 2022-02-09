/*
 * Created on : 2011/5/3
 */
package com.front.chuchuPurchasingAgent.tool.util;

import java.security.MessageDigest;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * 
 * @author Ringo
 */
public class DataEncrypt {
	public final static String CIPHER_MODE = "AES/CBC/PKCS5Padding"; 
//	public static void main(String[] args) throws Exception {
//		System.out.println(sha("111111")); //3d4f2bf07dc1be38b20cd6e46949a1071f9d0e3d
//	}

	public static String sha(String text) throws Exception {
		MessageDigest md = MessageDigest.getInstance("SHA");
		md.update(text.getBytes());
		byte[] digest = md.digest();
		return decodeHexStr(digest, 0, digest.length);
	}

	public static String md5(String text) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(text.getBytes());
		byte[] digest = md.digest();
		return decodeHexStr(digest, 0, digest.length);
	}

	static String decodeHexStr(byte buf[], int pos, int len) {
		StringBuilder hex = new StringBuilder();
		while (len-- > 0) {
			byte ch = buf[pos++];
			int d = (ch >> 4) & 0xf;
			hex.append((char) (d >= 10 ? 'a' - 10 + d : '0' + d));
			d = ch & 0xf;
			hex.append((char) (d >= 10 ? 'a' - 10 + d : '0' + d));
		}
		return hex.toString();
	}
	public static String sha256(String text) throws Exception {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(text.getBytes());
		byte[] digest = md.digest();
		return decodeHexStr(digest, 0, digest.length);
	}
	public static String aesEncode(String input, String key) {
		byte[] crypted = null;
		try {
			String sha2 = sha256(key);
			String aesKey = sha2.substring(0, 16);
			String aesIv = sha2.substring(sha2.length()-16);
			byte[] raw = aesKey.getBytes("ASCII");
			SecretKeySpec skey = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance(CIPHER_MODE);
			IvParameterSpec iv = new IvParameterSpec(aesIv.getBytes());
			cipher.init(Cipher.ENCRYPT_MODE, skey, iv);
			crypted = cipher.doFinal(input.getBytes("utf-8"));
		} catch (Exception e) {
			LogUtil.setErrorLog("encode", e);
			return null;
		}
		return new String(Base64.encodeBase64(crypted));
	}

	public static String aesDecode(String input, String key) {
		input = input.replaceAll(" ", "+");
		byte[] output = null;
		try {
			String sha2 = sha256(key);
			String aesKey = sha2.substring(0, 16);
			String aesIv = sha2.substring(sha2.length()-16);
			byte[] raw = aesKey.getBytes("ASCII");
			SecretKeySpec skey = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance(CIPHER_MODE);
			IvParameterSpec iv = new IvParameterSpec(aesIv.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, skey, iv);
			output = cipher.doFinal(Base64.decodeBase64(input));
		} catch (Exception e) {
			LogUtil.setErrorLog("decode", e);
			return null;
		}
		return new String(output);
	}
	
	public static String getCheckSumKey(String text) throws Exception {
		return sha256(text).substring(0, 32);
	}
}
