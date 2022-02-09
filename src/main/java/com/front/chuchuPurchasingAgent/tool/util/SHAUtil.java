package com.front.chuchuPurchasingAgent.tool.util;

import java.security.MessageDigest;

import javax.xml.bind.DatatypeConverter;


/**
 * 
 * 建立日期：2016/11/12
 * 程式摘要：com.mdbs.util<P> 
 * 類別名稱：SHAUtil.java<P>
 * 程式內容說明：<P>
 * @author claud
 * @version 1.0 
 * @since 1.0
 *
 */
public class SHAUtil {
	
	public static final String SHA_KEY = "esunsec";
	
	/**
	 * 
	 * @param info
	 * @return
	 */
	public static String encryptToSHA256(String data){
		String digestStr = "";
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.update((data + SHA_KEY).getBytes());
			byte[] digestBytes = digest.digest();
			digestStr = DatatypeConverter.printHexBinary(digestBytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return digestStr;
	}
	
}