package com.front.chuchuPurchasingAgent.tool.tag;

import java.net.URLEncoder;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.TimeZone;

import org.springframework.util.StringUtils;

import com.front.chuchuPurchasingAgent.tool.util.NumberUtil;
import com.front.chuchuPurchasingAgent.tool.util.ObjectUtil;
import com.front.chuchuPurchasingAgent.tool.util.StringUtil;

/**
 *
 * @author Ringo
 */
public final class Functions {
	
	private Functions() {
	}
	
	public static String urlEncode(final String value) {
		try {
			return URLEncoder.encode(value, "UTF-8");
		} catch (Exception e) {
			//logger.error("", e);
			return "";
		}
	}
	
	public static String nl2br(final String value) {
		try {
			return value.replaceAll("\r\n", "<br/>").replaceAll("\r", "<br/>").replaceAll("\n", "<br/>");
		} catch (Exception e) {
			//logger.error("", e);
			return "";
		}
	}
	
	public static String newString(final byte[] value) {
		try {
			return new String(value);
		} catch (Exception e) {
			//logger.error("", e);
			return "";
		}
	}
	
	public static String singleNewsUrl(final String altLink, final String singleUrl, final String newsId) {		
		return (StringUtils.hasText(altLink)) ? altLink : singleUrl + newsId;
	}
	/**
	 * 換行
	 * @param source
	 * @param words
	 * @return
	 * @throws Exception
	 */
	public static String nextLine(String source,int words) throws Exception {
			if(null==source) return ""; 
			int width = words * 2;
			StringBuilder sb = new StringBuilder();
			for (char c : source.toCharArray()) {
				width -= (c < 128) ? 1 : 2;
				sb.append(c);
				if (width < 0) {
					sb.append("<br/>");
					width = words * 2;
				}
			}
			return sb.toString();
		}
	/**
	 * ascii = 0.5 word, others = 1 word
	 *
	 * @param source
	 * @param words
	 * @return
	 * @throws Exception
	 */
	public static String cutByWord(String source,
		final int words) throws Exception {
		source = source.replaceAll("&hellip;", "...");
		if(null==source) return ""; 
		int width = words * 2;
		StringBuilder sb = new StringBuilder();
		boolean more = false;
		for (char c : source.toCharArray()) {
			width -= (c < 128) ? 1 : 2;
			if (width < 0) {
				more = true;
				break;
			}
			sb.append(c);
		}
		if(more)sb.append("...");
		return sb.toString();
	}
	/**
	 * 四捨五入
	 * @param money
	 * @param scale
	 * @return
	 * @throws Exception
	 */
	public static Double round(Double money, int scale) throws Exception {
		return NumberUtil.round(money, scale)/*delZero(String.format("%f", NumberUtil.round(money, scale)))*/;
	}
	
	/**
	 * 金錢進位，千位逗點 含金錢單位並用萬元進位
	 * @param money
	 * @return
	 * @throws Exception
	 */
	public static String moneyFormatIncludeUnitByTenThousand(Double money) throws Exception {
		if(!ObjectUtil.containsNonSpace(money))
			return "";
		try{
			money = NumberUtil.round(money, 0);
			
			String unit = "元";
			if(money>=10000){
				money = money/10000;
				unit = "萬";
			}
			String m=String.format("%f", money);
			String d="";
			if(m.indexOf(".")>0){
				d=m.substring(0,m.indexOf("."));
			}else{
				d=m;
			}
			StringBuilder sb = new StringBuilder();
			int count=1;
			for(int i=d.length()-1;i>=0;i--){
				sb.append(d.charAt(i));
				if(count%3==0 && i!=0)
					sb.append(",");
				count++;
			}
			String fin="";
			if(m.indexOf(".")>0){
				fin=sb.reverse().toString()+"."+m.substring(m.indexOf(".")+1, m.indexOf(".")+3); //小數點只需顯示2位數,無條件捨去
			}else{
				fin=sb.reverse().toString();
			}
			return delZero(fin)+unit;
		}catch(Exception e){
			return "";
		}
	}
	/**
	 * 金錢進位，千位逗點 含金錢單位並用萬元進位
	 * @param money
	 * @return
	 * @throws Exception
	 */
	public static String strMoneyFormatIncludeUnitByTenThousand(String strMoney) throws Exception {
		
		strMoney = strMoney.replaceAll("[^0-9]+", "");
		if (StringUtils.isEmpty(strMoney)) {
			strMoney ="0";
		}
		Double money = Double.parseDouble(strMoney);
		
		if(!ObjectUtil.containsNonSpace(money))
			return "";
		try{
			money = NumberUtil.round(money, 0);
			
			String unit = "元";
			if(money>=10000){
				money = money/10000;
				unit = "萬";
			}
			String m=String.format("%f", money);
			String d="";
			if(m.indexOf(".")>0){
				d=m.substring(0,m.indexOf("."));
			}else{
				d=m;
			}
			StringBuilder sb = new StringBuilder();
			int count=1;
			for(int i=d.length()-1;i>=0;i--){
				sb.append(d.charAt(i));
				if(count%3==0 && i!=0)
					sb.append(",");
				count++;
			}
			String fin="";
			if(m.indexOf(".")>0){
				fin=sb.reverse().toString()+"."+m.substring(m.indexOf(".")+1, m.indexOf(".")+3); //小數點只需顯示2位數,無條件捨去
			}else{
				fin=sb.reverse().toString();
			}
			return delZero(fin)+unit;
		}catch(Exception e){
			return "";
		}
	}
	/**
	 * 金錢進位，千位逗點
	 * @param money
	 * @return
	 * @throws Exception
	 */
	public static String moneyFormat(Double money) throws Exception {
		if(!ObjectUtil.containsNonSpace(money))
			return "";
		try{
			
			String m=String.format("%f", money);
			String d="";
			if(m.indexOf(".")>0){
				d=m.substring(0,m.indexOf("."));
			}else{
				d=m;
			}
			StringBuilder sb = new StringBuilder();
			int count=1;
			for(int i=d.length()-1;i>=0;i--){
				sb.append(d.charAt(i));
				if(count%3==0 && i!=0)
					sb.append(",");
				count++;
			}
			String fin="";
			if(m.indexOf(".")>0){
				fin=sb.reverse().toString()+"."+m.substring(m.indexOf(".")+1);
			}else{
				fin=sb.reverse().toString();
			}
			return delZero(fin);
		}catch(Exception e){
			return "";
		}
	}
	/**
	 * 金錢進位，千位逗點
	 * @param money
	 * @return
	 * @throws Exception
	 */
	public static String strMoneyFormat(String strMoney) throws Exception {
		
		if (StringUtils.isEmpty(strMoney)) return "0";
		strMoney = strMoney.replaceAll("[^0-9]+", "");
		if (StringUtils.isEmpty(strMoney)) {
			strMoney ="0";
		}
		Double money = Double.parseDouble(strMoney);
		
		if(!ObjectUtil.containsNonSpace(money))
			return "";
		try{
			
			String m=String.format("%f", money);
			String d="";
			if(m.indexOf(".")>0){
				d=m.substring(0,m.indexOf("."));
			}else{
				d=m;
			}
			StringBuilder sb = new StringBuilder();
			int count=1;
			for(int i=d.length()-1;i>=0;i--){
				sb.append(d.charAt(i));
				if(count%3==0 && i!=0)
					sb.append(",");
				count++;
			}
			String fin="";
			if(m.indexOf(".")>0){
				fin=sb.reverse().toString()+"."+m.substring(m.indexOf(".")+1);
			}else{
				fin=sb.reverse().toString();
			}
			return delZero(fin);
		}catch(Exception e){
			return "";
		}
	}
	/**
	 * 刪除小數點多餘的0
	 * @param num
	 * @return
	 */
	public static String delZero(String num){
		if(num.indexOf(".")<0){
			return num;
		}
		StringBuffer sb=new StringBuffer();
		boolean isDone=false;
		for(int i=num.length()-1;i>=0;i--){
			if(num.charAt(i)!='0' && !isDone){
				isDone=true;
				if(num.charAt(i)!='.')
				sb.append(num.charAt(i));
			}else if(isDone){
				sb.append(num.charAt(i));
			}
		}
		return sb.reverse().toString();
	}
	
	/**
	 * 計算保險年齡
	 */
	public static String calAge(String birthday, String baseDay) {
		String year = "0", month = "0", day = "0";
		if (birthday == null || birthday.length() < 8) {
			return "0";
		}
		if (birthday.indexOf("/") > 0) {
			String[] dates = birthday.split("/");
			year = dates[0];
			month = dates[1];
			day = dates[2];
		} else if (birthday.indexOf("-") > 0) {
			String[] dates = birthday.split("-");
			year = dates[0];
			month = dates[1];
			day = dates[2];
		} else if (birthday.length() == 8) {
			year = birthday.substring(0, 4);
			month = birthday.substring(4, 6);
			day = birthday.substring(6);
		} else {
			return "0";
		}

		int y = Integer.parseInt(year);
		//民國
		if (y < 1000) {
			y = y + 1911;
		}
		LocalDate birthTime = LocalDate.now().withYear(y).withMonth(Integer.parseInt(month))
				.withDayOfMonth(Integer.parseInt(day));

		LocalDate today = LocalDate.now();
		if (baseDay != null && baseDay.length() >= 8) {
			if (baseDay.indexOf("/") > 0) {
				String[] dates = baseDay.split("/");
				year = dates[0];
				month = dates[1];
				day = dates[2];
			} else if (baseDay.indexOf("-") > 0) {
				String[] dates = baseDay.split("-");
				year = dates[0];
				month = dates[1];
				day = dates[2];
			} else if (baseDay.length() == 8) {
				year = baseDay.substring(0, 4);
				month = baseDay.substring(4, 6);
				day = baseDay.substring(6);
			}
			today = LocalDate.now().withYear(Integer.parseInt(year)).withMonth(Integer.parseInt(month))
					.withDayOfMonth(Integer.parseInt(day));
		}
		//保險年齡：超過6個月又1天則多算1歲
		Period period = Period.between(birthTime, today);
		int between_year = period.getYears();
		int between_month = period.getMonths();
		int between_day = period.getDays();
		if (between_month >= 6 && between_day >= 0) {
			between_year++;
		}
		return String.valueOf(between_year);
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(calAge("1989/6/9", "2018/12/10"));
		System.out.println(delZero("2,920.000000"));
		System.out.println(strMoneyFormatIncludeUnitByTenThousand(""));
	}
	/**
	 * 轉換民國年
	 * @param time
	 * @return
	 */
	public static String getMingoYear(Timestamp time){
		LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(time.getTime()), TimeZone.getDefault().toZoneId());
		int year = dateTime.getYear();
		return String.valueOf(year-1911);
	}
	
	/**
	 * 
	 * 方法名稱: getIccodeCate 
	 * 方法描述: 取得商品Iccode分類
	 * @param iccode
	 * @return
	 */
	public static String getIccodeCate(String iccode){
		String cate = "";
		if(!StringUtil.isTrimEmptyString(iccode)&&iccode.length()>4){
			cate = iccode.substring(0,4)+"0000";
		}
		return cate;
	}
	
	/**
	 * 方法名稱: getCorrectMonth 
	 * 方法描述: 取得正確月份
	 * @param month
	 * @return
	 */
	public static int getCorrectMonth(int month){
		if(month > 12){
			month -= 12;
		}
		return month;
	}
	/**
	 * 方法名稱: getCorrectMonthIndex 
	 * 方法描述: 取得正確月份index
	 * @param month
	 * @return
	 */
	public static int getCorrectMonthIndex(int index){
		if(index > 11){
			index -= 12;
		}
		return index;
	}
}
