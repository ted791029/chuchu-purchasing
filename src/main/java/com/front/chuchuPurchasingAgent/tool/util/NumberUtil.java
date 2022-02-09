package com.front.chuchuPurchasingAgent.tool.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class NumberUtil {

	/**
	 * @param number
	 * @param rate
	 * @param mod
	 * @return
	 */
	public static double long2double(long number, int rate, int mod) {
		double r = 0;
		if (rate > mod) {
			r = Math.pow(10, rate - mod);
		}
		double m = Math.pow(10, mod);
		double result = (r == 0) ? 0 : ((double) number / r);
		result = (m == 0) ? 0 : ((double) Math.round(result) / m);
		return result;
	}

	/**
	 * @param value
	 * @return
	 */
	public static String formatFloat(double value) {
		try {
			DecimalFormat formatter = new DecimalFormat("#,###,###,###,###.##");
			String format = formatter.format(value);
			if (format.trim().length() == 0)
				format = "0.00";
			return format;
		} catch (Exception e) {
			return "0.00";
		}
	}

	/**
	 * @param value
	 * @return
	 */
	public static String formatInteger(long value) {
		try {
			DecimalFormat formatter = new DecimalFormat("#,###,###,###,###");
			String format = formatter.format(value);
			if (format.trim().length() == 0)
				format = "0";
			return format;
		} catch (Exception e) {
			return "0";
		}
	}

	/**
	 * @param value
	 * @return
	 */
	public static double getDouble(String value) {
		try {
			return Double.parseDouble(value);
		} catch (Exception e) {
			return 0.0;
		}
	}

	/**
	 * @param value
	 * @return
	 */
	public static int getInt(String value) {
		try {
			return Integer.parseInt(value);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * @param value
	 * @return
	 */
	public static long getLong(String value) {
		try {
			return Long.parseLong(value);
		} catch (Exception e) {
			return 0;
		}
	}
	
	/**
	 * 四捨五入
	 * @param v
	 * @param scale
	 * @return
	 */
	public static double round(double v, int scale) {
		if ( scale < 0 ) {
			return 0;
		}

		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	/**
	 * 大數運算 加
	 */
	public static String bigAdd(String val, String val2) {
		//令val2為較長者
		if (val.length() > val2.length()) {
			String t = val;
			val = val2;
			val2 = t;
		}
		int cha = val2.length() - val.length();
		for (int i = 0; i < cha; i++) {
			val = '0' + val;
		}
		
		String result = "";
		int carry = 0;
		for (int i = val2.length() - 1; i >= 0; i--) {
			int c = val2.charAt(i) + val.charAt(i) - 96 + carry;
			carry = c / 10;
			result = (c % 10) + result; 
		}

		if (carry == 1) result = 1 + result;
		return result;
	}
	
	/**
	 * 大數運算 乘
	 */
	public static String bigMultiply(String val, String val2) {
		String result = "";
		for (int i = 0; i < val2.length(); i++) {
			String temp = per(val, val2.charAt(i));
			result = bigAdd(result, add_0(temp, val2.length() - 1 - i));
		}
		return result;
	}
	
	/**
	 * 避免不精確位數造成誤差值
	 * @param num
	 * @return
	 */
	public static double strip(double num){
		int target = 12; //精確位數
		double temp = Math.pow(10, target); 
		return Math.round(num * temp) / temp; 
	}
	
	/**
	 * 大數運算 乘 by 1位數
	 */
	private static String per(String val, char c) {
		int n = c - '0';
		String result = "";
		int w = 0;
		for (int i = val.length() - 1; i >= 0; i--) {
			// 計算當前位置的積
			int m = (val.charAt(i) - '0') * n + w;
			// m/10就是進位值
			w = m / 10;
			// m%10就是當前位置的值
			result = m % 10 + result;
		}
		// 對第一位進行判斷
		if (w != 0)
			result = w + result;
		return result;
	}
	
	/**
	 * 補位
	 */
	private static String add_0(String temp, int i) {
		for (int j = 0; j < i; j++) {
			temp = temp + '0';
		}
		return temp;
	}
}
