package com.front.chuchuPurchasingAgent.tool.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class StringUtil {

	/**
	 * @param object
	 * @return
	 */
	public static boolean isTrimEmptyString(Object object) {
		return object == null || object.toString().trim().length() == 0;
	}

	/**
	 * @param object
	 * @return
	 */
	public static boolean isEmptyString(Object object) {
		return object == null || object.toString().length() == 0;
	}

	/**
	 * @param object
	 * @return
	 */
	public static String getTrimString(Object object) {
		return getTrimString(object, null);
	}

	/**
	 * @param obj
	 * @return
	 */
	public static boolean isEmptyOrZero(Object obj) {
		try {
			if (obj == null) {
				return true;
			} else {
				if (obj instanceof String)
					return "".equals(obj);
				else
					return "0".equals(String.valueOf(obj));
			}
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * @param obj
	 * @return
	 */
	public static boolean isEmpty(Object obj) {
		try {
			if (obj == null) {
				return true;
			} else {
				if (obj instanceof String)
					return "".equals(obj);
			}
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * @param object
	 * @param defaultValue
	 * @return
	 */
	public static String getTrimString(Object object, String defaultValue) {
		if (object == null)
			return defaultValue;
		return object.toString().trim();
	}

	public static long getLong(Object object, long l) {
		Long result = getLong(object);
		if (result == null) {
			result = l;
		}
		return result;
	}

	public static Long getLong(Object object) {
		if (object != null) {
			if (object instanceof Number)
				return ((Number) object).longValue();
			try {
				return Long.parseLong(String.valueOf(object));
			} catch (NumberFormatException e) {
			}
		}
		return null;
	}

	public static int getInteger(Object object, int i) {
		Integer result = getInteger(object);
		if (result == null)
			result = i;
		return result;
	}

	public static Integer getInteger(Object object) {
		if (object != null) {
			if (object instanceof Number)
				return ((Number) object).intValue();
			try {
				return Integer.parseInt(String.valueOf(object));
			} catch (NumberFormatException e) {
			}
		}
		return null;
	}

	public static String formatFloat(String valueString) {
		try {
			double value = Double.parseDouble(valueString);
			return NumberUtil.formatFloat(value);
		} catch (Exception e) {
			return "";
		}
	}

	public static String formatInteger(String valueString) {
		try {
			int value = Integer.parseInt(valueString);
			return NumberUtil.formatInteger(value);
		} catch (Exception e) {
			return "";
		}
	}

	public static String toDoubleString(String string, int rate, int mod) {
		try {
			long value = Long.parseLong(string);
			return String.valueOf(NumberUtil.long2double(value, rate, mod));
		} catch (Exception e) {

		}
		return string;
	}

	public static Date jsonDateToDate(String jsonDate) {
		try {
			// "/Date(1321867151710)/"
			int idx1 = jsonDate.indexOf("(");
			int idx2 = jsonDate.indexOf(")");
			String s = jsonDate.substring(idx1 + 1, idx2);
			long l = Long.valueOf(s);
			return new Date(l);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * @param keys
	 *            keys
	 * @return String
	 */
	public static String urlEncoderBig5(String keys) {
		String encode = "BIG5";
		return urlEncoder(keys, encode);
	}

	/**
	 * 
	 * @param keys
	 *            keys
	 * @param encoding
	 *            encoding
	 * @return String
	 */
	public static String urlEncoder(String keys, String encoding) {
		try {
			return java.net.URLEncoder.encode(keys, encoding);
		} catch (Exception e) {
			return keys;
		}
	}

	/**
	 * 取代
	 * 
	 * @param source
	 * @param keyword
	 * @param result
	 * @return String
	 * @throws Exception
	 */
	public static String replaceAllWord(String source, String keyword, String result) throws Exception {
		String str = source;
		/** 弱掃需註解 **/
		if (keyword != null && !keyword.equals("")) {
			str = source.replaceAll("(?i)" + keyword, result);
		}
		return str;
	}

	/**
	 * 去除HTML標籤
	 * 
	 * @param inputString
	 * @return
	 */
	public static String removeHtmlTag(String inputString) {
		return (null != inputString) ? inputString.replaceAll("\\<.*?>", "").replace("&amp;", "&").replace("&lt;", "<")
				.replace("&gt;", ">").replace("&nbsp;", "").replace("\r", "").replace("\n", "") : "";
	}
	
	/**
	 * 去除 SQL 特殊字元(contains 用)
	 */
	public static String removeSqlSpecialSymbol (String inputString) {
		return (StringUtils.isNotBlank(inputString)) ? inputString.replaceAll(" |\\(|\\)|\\.|\\+|\\?|_|'|\"|\\*", "") : "";
	}

	public static String[] splitRemoveBlank(String word, String delimiter) {
		if (StringUtils.isBlank(word)) {
			return null;
		}
		String[] array = word.split(delimiter, -1);
		List<String> list = new ArrayList<>();
		for (String s : array) {
			if (StringUtils.isNotBlank(s)) {
				list.add(s);
			}
		}
		if (list.size() <= 0) {
			return null;
		}
		String[] result = new String[list.size()];
		result = list.toArray(result);
		return result;
	}

	public static String arrayToString(String[] array, String symbo) {
		StringBuffer str = new StringBuffer();
		if (array != null && array.length > 0) {
			for (int i = 0; i < array.length; i++) {
				if (i != 0) {
					str.append(symbo);
				}
				str.append(array[i]);
			}
		}
		return str.toString();
	}

	public static Integer captureNumbers(String data) {
		try {
			Pattern p = Pattern.compile("[^0-9]");
			Matcher m = p.matcher(data);
			return getInteger(m.replaceAll("").trim());
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取得數字(包含".")以外的字串 轉微浮點數
	 * 
	 * @param data
	 * @return
	 */
	public static Float captureNumbersToFloat(String data) {
		try {
			Pattern p = Pattern.compile("[^0-9\\.]");
			Matcher m = p.matcher(data);
			return Float.valueOf(m.replaceAll("").trim());
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取得非數字(包含","和".")以外的字串
	 * 
	 * @param data
	 * @return
	 */
	public static String captureNotNumbers(String data) {
		try {
			Pattern p = Pattern.compile("[0-9\\,\\.]");
			Matcher m = p.matcher(data);
			return m.replaceAll("").trim();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取得字串出現次數
	 * 
	 * @param srcText
	 * @param findText
	 * @return
	 */
	public static int appearNumber(String srcText, String findText) {
		int count = 0;
		Pattern p = Pattern.compile(findText);
		Matcher m = p.matcher(srcText);
		while (m.find()) {
			count++;
		}
		return count;
	}
}
