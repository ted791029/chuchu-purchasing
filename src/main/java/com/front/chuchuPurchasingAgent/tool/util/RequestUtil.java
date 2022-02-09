package com.front.chuchuPurchasingAgent.tool.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * 建立日期：2016/10/19
 * 程式摘要：com.qsquare.util<P> 
 * 類別名稱：RequestUtil.java<P>
 * 程式內容說明：<P>
 * @author claud
 * @version 1.0 
 * @since 1.0
 *
 */
public class RequestUtil {
	
	/**
	 * 解析request資料
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static JSONObject parse(HttpServletRequest request) throws Exception {
		String body = null;
	    StringBuilder stringBuilder = new StringBuilder();
	    BufferedReader bufferedReader = null;
		try {
	        InputStream inputStream = request.getInputStream();
	        if (inputStream != null) {
	            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
	            char[] charBuffer = new char[128];
	            int bytesRead = -1;
	            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
	                stringBuilder.append(charBuffer, 0, bytesRead);
	            }
	        } else {
	            stringBuilder.append("");
	        }
	        body = stringBuilder.toString();
	    	JSONObject json = new JSONObject(body);
		    return json;
	    } catch (IOException ex) {
	        LogUtil.setErrorLog("parse", ex);
	        return null; 
	    } 
	}
	
	public static String getJsonStringEmpty(JSONObject object,String key) throws JSONException{
		return getJsonString(object, key, "");
	}
	public static String getJsonString(JSONObject object,String key, String default_value) throws JSONException{
		if (checkJsonEmpty(object, key)) {
			return WebUtil.removeControlCharacter(object.getString(key)).trim();
		} else {
			return default_value;
		}
	}
	
	public static double getJsonDouble(JSONObject object,String key, double default_value) throws JSONException{
		double d = 0;
		if (checkJsonEmpty(object, key)) {
			try{
				d = object.getDouble(key);
			} catch(Exception e) {
				LogUtil.setErrorLog("getJsonDouble", e);
				d = default_value;
			}
		} else {
			return default_value;
		}
		return d;
	}
	/**
	 * 
	 * @param object
	 * @param key
	 * @return
	 * @throws JSONException 
	 */
	public static boolean checkJsonEmpty(JSONObject object,String key) throws JSONException{
		boolean check = false;
		try{
			if(object.has(key) && !object.isNull(key)){
				check = true;
			}
			
			if(check==true){
				if (!ObjectUtil.containsNonSpace(object.get(key))) {
					check = false;
				}
			}
			
		} catch (Exception ex) {
	        LogUtil.setErrorLog("checkJsonEmpty", ex);
	        return false;
	    }
		
		return check;
	}
	
	/**
	 * 
	 * @param object
	 * @param key
	 * @return
	 * @throws JSONException
	 */
	public static boolean checkJsonInt(JSONObject object,String key) throws JSONException{
		boolean check = false;
		try{
			if(object.has(key) && !object.isNull(key)){
				check = true;
			}
			
			if(check==true){
				if (!ObjectUtil.containsNonSpace(object.getInt(key))) {
					check = false;
				}
			}
			
		} catch (Exception ex) {
	        LogUtil.setErrorLog("checkJsonEmpty", ex);
	        return false;
	    }
		
		return check;
	}
	
	/**
	 * 
	 * @param object
	 * @param key
	 * @return
	 * @throws JSONException
	 */
	public static boolean checkJsonBoolean(JSONObject object,String key) throws JSONException{
		boolean check = false;
		try{
			if(object.has(key) && !object.isNull(key)){
				check = true;
			}
			
			if(check==true){
				if (!ObjectUtil.containsNonSpace(object.getBoolean(key))) {
					check = false;
				}
			}
			
		} catch (Exception ex) {
	        LogUtil.setErrorLog("checkJsonEmpty", ex);
	        return false;
	    }
		
		return check;
	}
	
	
	
	
}
