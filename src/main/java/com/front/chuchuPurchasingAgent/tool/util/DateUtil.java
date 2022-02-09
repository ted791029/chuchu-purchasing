package com.front.chuchuPurchasingAgent.tool.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;


public class DateUtil {
	
	/**
	* 取得系統版本編號日期
	* @param date
	* @param day
	* @return Date
	*/
	public static String getVersionDate() {
		Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR) - 1911;
        String strYear = year < 100 ? "0" + year : "" + year;
        String month = DateUtil.format(cal.getTime(), "MM");
        String date = DateUtil.format(cal.getTime(), "dd");
        String hour = DateUtil.format(cal.getTime(), "HH");
        return strYear + "" + month + "" + date + "" +hour;
	}
	
	/**
	* 取得指定日期幾天前 / 後的日期
	* @param date
	* @param day
	* @return Date
	*/
	public static Date addDay(final Date date, final int day) {
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		c.add(Calendar.DATE, day);
		return c.getTime();
	}
    /**
	* 取得指定日期幾天前 / 後的日期
	* @param date
	* @param day
	* @return Date
	*/
    public static Timestamp addDay(Timestamp date, int day) {
    	Calendar c = Calendar.getInstance();
    	c.setTime(date);
    	c.add(Calendar.DATE, day);
    	return new Timestamp(c.getTime().getTime());
    }
	/**
	 * 取得指定日期幾月前 / 後的日期
	 * @param date
	 * @param month
	 * @return
	 */
	public static Date addMonth(Date date, int month)
	  {
	    Calendar c = new GregorianCalendar();
	    c.setTime(date);
	    c.add(Calendar.MONTH, month);
	    return c.getTime();
	  }
	/**
	 * 取得指定日期幾月前 / 後的日期
	 * @param date
	 * @param month
	 * @return
	 */
	public static Timestamp addMonth(Timestamp date, int month)
	{
	   Calendar c = new GregorianCalendar();
	   c.setTime(date);
	   c.add(Calendar.MONTH, month);
	   return new Timestamp(c.getTime().getTime());
	 }
	public static Date addHour(Date date, int hour)
	{
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		c.add(Calendar.HOUR, hour);
		return c.getTime();
	}
	
	/**
	 * 取得指定日期幾年前 / 後的日期
	 */
	public static Timestamp addYear(Timestamp date, int year) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.YEAR, year);
		return new Timestamp(c.getTime().getTime());
	}
	
	/**
	 * 傳入年月日轉Date
	 * @param year
	 * @param month
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static Date parse(String year, String month, String date) throws Exception {
		int iYear = Integer.parseInt(year);
		int iMonth = Integer.parseInt(month);
		int iDate = Integer.parseInt(date);
		if (iMonth < 1 || iMonth > 12) {
			throw new Exception("invalid month!");
		}
		iMonth -= 1; // zero-base for Calendar's month
		Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.YEAR, iYear);
		calendar.set(Calendar.MONTH, iMonth);
		calendar.set(Calendar.DATE, 1);

		int minDate = calendar.getActualMinimum(Calendar.DATE);
		int maxDate = calendar.getActualMaximum(Calendar.DATE);
		if (iDate < minDate || iDate > maxDate) {
			throw new Exception("invalid date!");
		}
		calendar.set(Calendar.DATE, iDate);
		return calendar.getTime();
	}

    /**
     * 日期String轉Date
     * 例:yyyy/MM/dd HH:mm:ss
     * yyyy-MM-dd HH:mm:ss
     * yyyyMMddHHmmss
     * @param time
     * @param pattern
     * @return
     * @throws Exception
     */
    public static Date parse(String time, String pattern) throws Exception {
        return parse(time, pattern, null, null);
    }

    public static Date parse(String time, String pattern, Locale locale, String timeZone) throws Exception {
        if (locale == null) {
            locale = Locale.getDefault();
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, locale);
        if (timeZone != null) {
            sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
        }
        return sdf.parse(time);
    }
    
    /**
	 * 傳入年月日轉Timestamp
	 */
    public static Timestamp parseTimestamp(String time, String pattern) throws Exception {
        return parseTimestamp(time, pattern, null, null);
    }
    
    public static Timestamp parseTimestamp(String time, String pattern, Locale locale, String timeZone) throws Exception {
        if (locale == null) {
            locale = Locale.getDefault();
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, locale);
        if (timeZone != null) {
            sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
        }
        return new Timestamp(sdf.parse(time).getTime());
    }

    /**
     * 日期Date轉String,例:yyyy/MM/dd
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        return format(date, pattern, null, null);
    }

    /**
     * 日期timestamp轉String,例:yyyy/MM/dd
     * @param timestamp
     * @param pattern
     * @return
     */
    public static String format(Timestamp timestamp, String pattern) {
        return format(timestamp, pattern, null, null);
    }

    public static String format(Date date, String pattern, Locale locale) {
        return format(date, pattern, locale, null);
    }

    public static String format(Date date, String pattern, String timeZone) {
        return format(date, pattern, null, timeZone);
    }

    public static String format(Date date, String pattern, Locale locale, String timeZone) {
        try {
            if (locale == null) {
                locale = Locale.getDefault();
            }
            DateFormat df = new SimpleDateFormat(pattern, locale);
            if (timeZone != null) {
                df.setTimeZone(TimeZone.getTimeZone(timeZone));
            } else {    // to default timezone
                df.setTimeZone(TimeZone.getDefault());
            }
            return df.format(date);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 將傳入日期物件的時分秒更改為當年的第一秒
     * @param date date
     * @return Date
     */
    public static Date getYearBegin(Date date) {
    	Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MONTH, 0);
        cal.set(Calendar.DATE, 1);
        cal.set(Calendar.HOUR_OF_DAY, 00);
        cal.set(Calendar.MINUTE, 00);
        cal.set(Calendar.SECOND, 00);
        return cal.getTime();
    }

    /**
     * 將傳入日期物件的時分秒更改為當年的最後一秒
     * @param date date
     * @return Date
     */
    public static Date getYearEnd(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MONTH, 11);
        cal.set(Calendar.DATE, 31);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    /**
     * 將傳入日期物件的時分秒更改為當年的最後一秒
     * @param Timestamp
     * @return Timestamp
     */
    public static Timestamp getYearEndTimestamp(Timestamp date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MONTH, 11);
        cal.set(Calendar.DATE, 31);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return new Timestamp(cal.getTimeInMillis());
    }

    /**
     * 將傳入日期物件的時分秒更改為當日的第一秒
     * @param date date
     * @return Date
     */
    public static Date getDayBegin(Date date) {
    	Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }

    /**
     * 將傳入日期物件的時分秒更改為當日的最後一秒
     * @param date date
     * @return Date
     */
    public static Date getDayEnd(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }
    
    public static Timestamp getDayBegin(Timestamp date) {
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	cal.set(Calendar.HOUR_OF_DAY, 0);
    	cal.set(Calendar.MINUTE, 0);
    	cal.set(Calendar.SECOND, 0);
    	cal.set(Calendar.MILLISECOND, 0);
    	return new Timestamp(cal.getTime().getTime());
    }
    public static Timestamp getDayEnd(Timestamp date) {
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	cal.set(Calendar.HOUR_OF_DAY, 23);
    	cal.set(Calendar.MINUTE, 59);
    	cal.set(Calendar.SECOND, 59);
    	return new Timestamp(cal.getTime().getTime());
    }

    /**
     * 取得當前日期所在周的第一天
     * @param date date
     * @return Date
     */
    public static Date getFirstDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
        return c.getTime();
    }

    /**
     * 取得當前日期所在周的最後一天
     * @param date date
     * @return Date
     */
    public static Date getLastDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
        return c.getTime();
    }

    /**
     * 取得指定日期所在月的第一天
     * @param date date
     * @return Date
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar c = new GregorianCalendar();
        c.setTime(date);
        int minDate = c.getActualMinimum(Calendar.DATE);
        c.set(Calendar.DATE, minDate);
        Date first = getDayBegin(c.getTime());
        return first;
    }

    /**
     * 取得指定日期所在月的最後一天
     * @param date date
     * @return Date
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar c = new GregorianCalendar();
        c.setTime(date);
        int maxDate = c.getActualMaximum(Calendar.DATE);
        c.set(Calendar.DATE, maxDate);
        Date end = getDayEnd(c.getTime());
        return end;
    }
    
    /**
     * 返回指定日期的季的第一天
     * @param year
     * @param quarter
     * @return
     */
	public static Date getFirstDayOfQuarter(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return getFirstDayOfQuarter(calendar.get(Calendar.YEAR), getQuarterOfYear(date));
	}
	
	/**
     * 返回指定日期的季的最後一天
     * @param year
     * @param quarter
     * @return
     */
	public static Date getLastDayOfQuarter(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return getLastDayOfQuarter(calendar.get(Calendar.YEAR), getQuarterOfYear(date));
	}
    
    /**
     * 返回指定日期的上一季的最後一天
     * @param year
     * @param quarter
     * @return
     */
	public static Date getLastDayOfLastQuarter(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return getLastDayOfLastQuarter(calendar.get(Calendar.YEAR), getQuarterOfYear(date));
	}
	
	/**
     * 返回指定年季的上一季的最後一天
     * @param year
     * @param quarter
     * @return
     */
	public static Date getLastDayOfLastQuarter(Integer year, Integer quarter) {
		Calendar calendar = Calendar.getInstance();
		Integer month = new Integer(0);
		if (quarter == 1) {
			month = 12 - 1;
		} else if (quarter == 2) {
			month = 3 - 1;
		} else if (quarter == 3) {
			month = 6 - 1;
		} else if (quarter == 4) {
			month = 9 - 1;
		} else {
			month = calendar.get(Calendar.MONTH);
		}
		return getLastDayOfMonth(year, month);
	}
	
	/**
     * 返回指定年月的月的第一天
     * @param year
     * @param month
     * @return
     */
    public static Date getFirstDayOfMonth(Integer year, Integer month) {
        Calendar calendar = Calendar.getInstance();
        if (year == null) {
            year = calendar.get(Calendar.YEAR);
        }
        if (month == null) {
            month = calendar.get(Calendar.MONTH);
        }
        calendar.set(year, month, 1);
        return calendar.getTime();
    }
    
    /**
     * 返回指定年月的月的最後一天
     * @param year
     * @param month
     * @return
     */
    public static Date getLastDayOfMonth(Integer year, Integer month) {
        Calendar calendar = Calendar.getInstance();
        if (year == null) {
            year = calendar.get(Calendar.YEAR);
        }
        if (month == null) {
            month = calendar.get(Calendar.MONTH);
        }
        calendar.set(year, month, 1);
        calendar.roll(Calendar.DATE, -1);
        return calendar.getTime();
    }
    
	/**
     * 返回指定年季的季的第一天
     * @param year
     * @param quarter
     * @return
     */
    public static Date getFirstDayOfQuarter(Integer year, Integer quarter) {
        Calendar calendar = Calendar.getInstance();
        Integer month = new Integer(0);
        if (quarter == 1) {
            month = 1 - 1;
        } else if (quarter == 2) {
            month = 4 - 1;
        } else if (quarter == 3) {
            month = 7 - 1;
        } else if (quarter == 4) {
            month = 10 - 1;
        } else {
            month = calendar.get(Calendar.MONTH);
        }
        return getFirstDayOfMonth(year, month);
    }
    
    /**
     * 返回指定年季的季的最後一天
     * @param year
     * @param quarter
     * @return
     */
    public static Date getLastDayOfQuarter(Integer year, Integer quarter) {
        Calendar calendar = Calendar.getInstance();
        Integer month = new Integer(0);
        if (quarter == 1) {
            month = 3 - 1;
        } else if (quarter == 2) {
            month = 6 - 1;
        } else if (quarter == 3) {
            month = 9 - 1;
        } else if (quarter == 4) {
            month = 12 - 1;
        } else {
            month = calendar.get(Calendar.MONTH);
        }
        return getLastDayOfMonth(year, month);
    }
    
	/**
     * 返回指定日期的季度
     * @param date
     * @return
     */
    public static int getQuarterOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) / 3 + 1;
    }

    /**
     * 取得幾年前後的日期
     * @param date date
     * @param amount amount
     * @return Date
     */
    public static Date getCalYear(Date date, int amount) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);

        c.set(year, month, day);
        c.add(Calendar.YEAR, amount);
        return c.getTime();
    }

    /**
     * 取得幾月前後的日期
     * @param date
     * @param amount
     * @return
     */
    public static Date getCalMonth(Date date, int amount) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);

        c.set(year, month, day);
        c.add(Calendar.MONTH, amount);
        return c.getTime();
    }

    /**
     * 取得幾天前後的日期
     * @param date date
     * @param amount amount
     * @return Date
     */
    public static Date getCalDay(Date date, int amount) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);

        c.set(year, month, day);
        c.add(Calendar.DATE, amount);
        return c.getTime();
    }

    /**
     * 取得幾小時前後的日期
     * @param date date
     * @param amount amount
     * @return Date
     */
    public static Date getCalHour(Date date, int amount) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);

        int hh = c.get(Calendar.HOUR_OF_DAY);
        int mm = c.get(Calendar.MINUTE);
        int ss = c.get(Calendar.SECOND);

        c.set(year, month, day, hh, mm, ss);
        c.add(Calendar.HOUR_OF_DAY, amount);
        return c.getTime();
    }

    /**
     * 取得幾分鐘前後的日期
     * @param date date
     * @param amount amount
     * @return Date
     */
    public static Date getCalMin(Date date, int amount) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);

        int hh = c.get(Calendar.HOUR_OF_DAY);
        int mm = c.get(Calendar.MINUTE);
        int ss = c.get(Calendar.SECOND);

        c.set(year, month, day, hh, mm, ss);
        c.add(Calendar.MINUTE, amount);
        return c.getTime();
    }

    /**
     * 計算二日期相差幾天
     * @param dateStr1	:	字串日期1
     * @param dateStr2	:	字串日期2
     * @return
     */
    public static long getCalDayDiff(String dateStr1, String dateStr2) {
		long dateDiff = 0;
		try {
			Date date1 = parse(dateStr1, "yyyyMMdd");
			Date date2 = parse(dateStr2, "yyyyMMdd");
			if (date1.getTime() < date2.getTime()) {
				dateDiff = ((date2.getTime() / 1000 - date1.getTime() / 1000) / (24 * 60 * 60));
			} else {
				dateDiff = ((date1.getTime() / 1000 - date2.getTime() / 1000) / (24 * 60 * 60));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateDiff;
	}

    /**
     * 計算二日期相差幾天
     * @param date1	:	Date日期1
     * @param date2	:	Date日期2
     * @return
     */
    public static long getCalDayDiff(Date date1, Date date2) {
		long dateDiff = 0;
		try {
			//日期1,2會被更改為當天的第一秒
			date1 = getDayBegin(date1);
			date2 = getDayBegin(date2);
			if (date1.getTime() < date2.getTime()) {
				dateDiff = ((date2.getTime() / 1000 - date1.getTime() / 1000) / (24 * 60 * 60));
			} else {
				dateDiff = ((date1.getTime() / 1000 - date2.getTime() / 1000) / (24 * 60 * 60));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateDiff;
	}
    
    /**
     * 計算二日期相差幾小時
     * @param dateStr1	:	字串日期1
     * @param dateStr2	:	字串日期2
     * @return
     */
	public static long getCalHoursDiff(String dateStr1, String dateStr2) {
		long dateDiff = 0;
		try {
			Date date1 = parse(dateStr1, "yyyyMMdd HH:mm");
			Date date2 = parse(dateStr2, "yyyyMMdd HH:mm");
			if (date1.getTime() < date2.getTime()) {
				dateDiff = ((date2.getTime() / 1000 - date1.getTime() / 1000) / (60 * 60));
			} else {
				dateDiff = ((date1.getTime() / 1000 - date2.getTime() / 1000) / (60 * 60));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateDiff;
	}

    /**
     * 格式化民國年月日 至 時分秒 ex:101/01/02 18:37:22
     * @param now now
     * @return String
     */
    public static String convertTwyDateTime(Date now) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(now);

        int year = cal.get(Calendar.YEAR) - 1911;
        String strYear = year < 100 ? "0" + year : "" + year;
        String month = DateUtil.format(now, "MM");
        String date = DateUtil.format(now, "dd");
        String hh = DateUtil.format(now, "HH");
        String mm = DateUtil.format(now, "mm");
        String ss = DateUtil.format(now, "ss");
        return strYear + "/" + month + "/" + date + " " + hh + ":" + mm + ":" + ss;
    }

    /**
     * 格式化民國年月日 ex:1010102
     * @param now now
     * @return String
     */
    public static String convertTwyDate(Date now) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(now);

        int year = cal.get(Calendar.YEAR) - 1911;
        String strYear = year < 100 ? "0" + year : "" + year;
        String month = DateUtil.format(now, "MM");
        String date = DateUtil.format(now, "dd");
        return strYear + "" + month + "" + date;
    }

    /**
     * 格式化民國年
     * @param now now
     * @return String
     */
    public static String convertTwyYear(Date now) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        int year = cal.get(Calendar.YEAR) - 1911;
        return year < 100 ? "0" + year : "" + year;
    }

    /**
     * 格式化民國年月日yyy年M月d日
     * @param date date
     * @return String
     */
	public static final String formatToChineseDate(Date date) {
		if (date == null) {
			return "";
		} else {
			StringBuffer chiDate = new StringBuffer();
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			chiDate.append(cal.get(Calendar.YEAR) - 1911);
			chiDate.append("年");
			chiDate.append(cal.get(Calendar.MONTH) + 1);
			chiDate.append("月");
			chiDate.append(cal.get(Calendar.DAY_OF_MONTH));
			chiDate.append("日");
			return chiDate.toString();
		}
	}
	
    /**
     * 格式化民國年月日 yyy/M/d
     * @param date          :   date
     * @param separator     :   "/",可指定格式
     * @return
     */
	public static final String formatToChineseDate(Date date, String separator) {
		if (date == null) {
			return "";
		} else {
			StringBuffer chiDate = new StringBuffer();
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			chiDate.append(cal.get(Calendar.YEAR) - 1911);
			chiDate.append(separator);
			chiDate.append(cal.get(Calendar.MONTH) + 1);
			chiDate.append(separator);
			chiDate.append(cal.get(Calendar.DAY_OF_MONTH));
			return chiDate.toString();
		}
	}
	
	/**
     * 格式化民國年月日,XXX年XX月XX日
     * @param date          :   date
     * @return
     */
	public static String formatToChineseDate(String strDate) {
		String twyDate = "";
		if (strDate == null) {
			return "";
		} else {
			try {
				Date date = DateUtil.parse(strDate, "yyyy-MM-dd");
				twyDate = formatToChineseDate(date);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return twyDate;
	}
	
	/**
     * 格式化民國年月日 yyy/M/d
     * @param date          :   date
     * @param separator     :   "/",可指定格式
     * @return
     */
	public static String formatToTwyDate(String strDate, String separator) {
		String twyDate = "";
		if (strDate == null) {
			return "";
		} else {
			try {
				Date date = DateUtil.parse(strDate, "yyyy-MM-dd");
				twyDate = formatToChineseDate(date, separator);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return twyDate;
	}
	
    /**
     * 民國年轉西元年,例:1011231
     * @param date date
     * @return Date
     */
    public static Date twyConvertDate(String date) {
        Date dd = null;
        SimpleDateFormat sdf;
        try {
            if (date != null && date.length() == 7) {
                String yr = date.substring(0, 3);
                int year = Integer.parseInt(yr) + 1911;
                date = year + date.substring(3);
                sdf = new SimpleDateFormat("yyyyMMdd");
                dd = sdf.parse(date);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dd;
    }

    /**
     * 取得該日期所屬星期幾(中文)
     * @param date
     * @return
     */
    public static String getChineseWeekDayName(Date date) {
    	SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
		String week = sdf.format(date);
		return week;
    }

    /**
     * 取得該日期所屬星期幾(數值)
     * @param date
     * @return
     */
    public static int getWeekDay(Date date) {
    	Calendar cal = Calendar.getInstance();
        cal.setTime(date);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		return dayOfWeek - 1;
	}

    /******	LocalDate,LocalDateTime	******/
    
    public static String format(LocalDate date,String delimiter){
    	String format = "";
    	int year = date.getYear();
    	String month = date.getMonthValue()<10?"0"+date.getMonthValue():""+date.getMonthValue();
    	String day = date.getDayOfMonth()<10?"0"+date.getDayOfMonth():""+date.getDayOfMonth();
    	if(delimiter!=null){
    		format = year+delimiter+month+delimiter+day;
    	}else{
    		format = year+month+day;
    	}
    	return format;
    }
    public static String formatDateTime(LocalDateTime date,String delimiter){
    	String format = "";
    	int year = date.getYear();
    	String month = date.getMonthValue()<10?"0"+date.getMonthValue():""+date.getMonthValue();
    	String day = date.getDayOfMonth()<10?"0"+date.getDayOfMonth():""+date.getDayOfMonth();
    	String hour = date.getHour()<10?"0"+date.getHour():""+date.getHour();
    	String min = date.getMinute()<10?"0"+date.getMinute():""+date.getMinute();
    	if(delimiter!=null){
    		format = year+delimiter+month+delimiter+day+" "+hour+":"+min;
    	}else{
    		format = year+month+day+" "+hour+":"+min;
    	}
    	return format;
    }
    
    public static LocalDate toLocalDate(String str,String delimiter){
    	String[] days = str.split(delimiter);
		Integer year = Integer.parseInt(days[0]);
		Integer month = Integer.parseInt(days[1]);
		Integer day = Integer.parseInt(days[2]);
		LocalDate localDate = LocalDate.of(year, month, day);
		return localDate;
    }
    public static LocalDateTime toLocalDateTime(String date_str,String date_delimiter,String time_str,String time_delimiter){
    	String[] days = date_str.split(date_delimiter);
		String[] times = time_str.split(time_delimiter);
		Integer year = Integer.parseInt(days[0]);
		Integer month = Integer.parseInt(days[1]);
		Integer day = Integer.parseInt(days[2]);
		Integer hour = Integer.parseInt(times[0]);
		Integer minute = Integer.parseInt(times[1]);
		LocalDateTime localDateTime = LocalDateTime.of(year, month, day, hour, minute);
    	return localDateTime;
    }
    
    public static boolean checkLegalDate(String str,String delimiter){
    	boolean pass = true;
    	try {
			toLocalDate(str, delimiter);
		} catch (Exception e) {
			pass = false;
		}
    	return pass;
    }   
    
    
    public static void main(String[] args) throws Exception{
		String _value = "2016-03-21 11:00:17.0";
		System.out.println(formatToChineseDate(_value));
		System.out.println(formatToTwyDate(_value, "/"));
	}
    
    /**
     * 傳入民國轉 Timestamp 格式:108/01/01
     * @param date
     * @return
     * @throws Exception
     */
    public static Timestamp twyConvertTimestamp(String date) throws Exception{
		Date tempDate = (Date) twyConvertDate(date.replace("/", ""));
		String tempStr = format(tempDate, "yyyMMdd");
		return parseTimestamp(tempStr, "yyyyMMdd");
	}
    /**
     * 傳入民國轉 Timestamp 格式:108/01/01
     * @param date
     * @return
     * @throws Exception
     */
    public static String twyConvertString(String date) throws Exception{
		Date tempDate = (Date) twyConvertDate(date.replace("/", ""));
		return format(tempDate, "yyyMMdd");
	}
    
    
    /**
	 * 時間顯示轉換
	 * @param sendDate
	 * @param now
	 * @return
	 */
    public static  String formatDuration(Timestamp sendDate, Timestamp now) {
			String duration = "";
			long diff = now.getTime() - sendDate.getTime();
			
			if(diff < 0){
				return duration = format(sendDate, "yyyy/MM/dd");
			}
			
			double seconds = diff / 1000;
			double minutes = seconds / 60;
			double hours = minutes / 60;
			double days = hours / 24;
			
			if (seconds < 60) {
				//X秒前
				duration = String.valueOf((int)Math.floor(seconds))+" 秒前";
			} else if (minutes < 60) {
				//X分鐘前
				duration = String.valueOf((int)Math.floor(minutes))+" 分鐘前";
			} else if (hours < 24) {
				//X小時前
				duration = String.valueOf((int)Math.floor(hours))+" 小時前";
			} else if (days < 7) {
				//X天前
				duration = String.valueOf((int)Math.floor(days))+" 天前";
			} else {
				//YYYY/MM/DD
				duration = format(sendDate, "yyyy/MM/dd");
			}
			
		   return duration;
	     
	}
    
    /**
     * Stirng 日期驗證  yyyy/MM/dd HH:mm:ss
     * @param input_date
     * @return
     * @throws Exception
     */
    public static Boolean isValidDateToTime(String input_date) throws Exception{
    	String regex = "^([0-9]{4})[./]{1}([0-9]{2})[./]{1}([0-9]{2})[ ]+([0-9]{2})[.:]{1}([0-9]{2})$";
    	if(!input_date.matches(regex)){
    		return false;
    	}
    	String[] temp = input_date.split(" ");  //將日期和時間分開
    	String date = temp[0];
    	String time = temp[temp.length - 1];
    	String[] dateArr = date.split("/");
    	int year = Integer.parseInt(dateArr[0]);
    	int month = Integer.parseInt(dateArr[1]);
    	int day = Integer.parseInt(dateArr[2]);
    	String[] timeArr = time.split(":");
    	int hour = Integer.parseInt(timeArr[0]);
    	int min = Integer.parseInt(timeArr[1]);
    	int[] limitInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    	Date newDate = parse(dateArr[0], "1", "29"); // 是否為閏年?
    	if(newDate.getDate() == 29){
    		limitInMonth[1] = 29;
    	}
    	if(year <= 0 || year > 9999){
    		return false;
    	}
    	if(month <= 0 || month > 12){
    		return false;
    	}
    	if(day <= 0 || day > limitInMonth[month - 1]){
    		return false;
    	}
    	if(hour < 0 || hour >= 24){
    		return false;
    	}
    	if(min < 0 || min >= 60){
    		return false;
    	}
    	return true;
    }
    
    /**
     * Stirng 日期驗證  yyyy/MM/dd HH:mm:ss
     * @param input_date
     * @return
     * @throws Exception
     */
    public static Boolean isValidDate(String input_date) throws Exception{
    	String regex = "^([0-9]{4})[./]{1}([0-9]{2})[./]{1}([0-9]{2})$";
    	if(!input_date.matches(regex)){
    		return false;
    	}
    	String[] dateArr = input_date.split("/");
    	int year = Integer.parseInt(dateArr[0]);
    	int month = Integer.parseInt(dateArr[1]);
    	int day = Integer.parseInt(dateArr[2]);
    	int[] limitInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    	Date newDate = parse(dateArr[0], "1", "29"); // 是否為閏年?
    	if(newDate.getDate() == 29){
    		limitInMonth[1] = 29;
    	}
    	if(year <= 0 || year > 9999){
    		return false;
    	}
    	if(month <= 0 || month > 12){
    		return false;
    	}
    	if(day <= 0 || day > limitInMonth[month - 1]){
    		return false;
    	}
    	return true;
    }
    
    /**
     * 民國字串轉西元字串
     * @param input_date
     * @return
     */
    public static String MingoConvertCE(String input_date, String delimiter, String new_delimiter){
		int year = 0;
		String month = "";
		String day = "";
		try {           
			String[] dateArr = input_date.split(" ")[0].split(delimiter);
			year = Integer.parseInt(dateArr[0]) + 1911;
			month = Integer.parseInt(dateArr[1]) < 10 ? "0" + Integer.parseInt(dateArr[1]) : Integer.parseInt(dateArr[1]) + "";
			day = Integer.parseInt(dateArr[2]) < 10 ? "0" + Integer.parseInt(dateArr[2]) : Integer.parseInt(dateArr[2]) + "";
		} catch (Exception e) {
		    e.printStackTrace();
		}
    	return year + new_delimiter + month + new_delimiter + day;
    }
    
    /**
     * 西元字串轉民國字串
     * @param input_date
     * @return
     */
    public static String CEConvertMingo(String input_date, String delimiter, String new_delimiter){
    	int year = 0;
		String month = "";
		String day = "";
		try {
			String[] dateArr = input_date.split(" ")[0].split(delimiter);
			year = Integer.parseInt(dateArr[0]) - 1911;
			month = Integer.parseInt(dateArr[1]) < 10 ? "0" + Integer.parseInt(dateArr[1]) : Integer.parseInt(dateArr[1]) + "";
			day = Integer.parseInt(dateArr[2]) < 10 ? "0" + Integer.parseInt(dateArr[2]) : Integer.parseInt(dateArr[2]) + "";
		} catch (Exception e) {
		    e.printStackTrace();
		}
    	return year + new_delimiter + month + new_delimiter + day;
    }

	/**
	 * 天數相減
	 * @param start_date
	 * @param end_date
	 * @return
	 * @throws ParseException
	 */
    public static int daySubtraction(Timestamp start_date, Timestamp end_date) throws ParseException{
		SimpleDateFormat dfDate = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(dfDate.parse(dfDate.format(start_date)));
		Date date1 = cal.getTime(); // 日期1
		cal.setTime(dfDate.parse(dfDate.format(end_date)));
		Date date2 = cal.getTime(); // 日期2
		long daterange = date2.getTime() - date1.getTime();
		long time = 1000 * 3600 * 24; // A day in milliseconds
		int result = (int) (daterange / time);
		return result;
    }
    
    /**
     * Timestamp 轉民國 String
     * @param timestamp
     * @param pattern
     * @param delimiter
     * @param new_delimiter
     * @return
     */
    public static String formatMingoDate(Timestamp timestamp, String pattern, String delimiter, String new_delimiter) {
    	
        return CEConvertMingo(format(timestamp, pattern, null, null), delimiter, new_delimiter);
    }
    /**
     * 民國 String 轉 Timestamp
     * @param input_date
     * @param delimiter
     * @param new_delimiter
     * @param pattern
     * @return
     * @throws Exception
     */
    public static Timestamp mingoParseTimestamp(String input_date, String delimiter, String new_delimiter, String pattern) throws Exception{
    	return parseTimestamp(MingoConvertCE(input_date, delimiter, new_delimiter), pattern);
    }
}
