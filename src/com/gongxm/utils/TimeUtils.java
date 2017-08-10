package com.gongxm.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtils {

	public static final int TODAY = 1;
	public static final int TOMORROW = 2;
	public static final int AFTER_TOMORROW = 3;

	public static String getTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}


	public static String getMillisTime() {
		return System.currentTimeMillis() + "";
	}

	public static long getTimeMillis() {
		return System.currentTimeMillis();
	}

	public static Date parseTime(String time) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.parse(time);
	}

	public static String parseTime(long time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date(time));
	}

	public static String parseTime(Date time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(time);
	}

	public static Date parseShortTime(String time) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.parse(time);
	}

	public static String parseShortTime(long time) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date(time));
	}


	// 获取当前日期对象
	public static Date getCurrentTime() {
		return new Date();
	}

	/**
	 * 把字符串日期转换成毫秒值
	 * 
	 * @param time
	 * @return
	 */
	public static long parseToMillis(String time) {
		long millis = 0;
		try {
			Date date = parseTime(time);
			millis = date.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return millis;
	}

	/**
	 * 判断时间值是否比当前时间大
	 * 
	 * @param time
	 * @return
	 */
	public static boolean checkTime(String time) {
		long millis = parseToMillis(time);
		long currentTime = getTimeMillis();
		return currentTime < millis;
	}

	/**
	 * 判断时间值是否比当前时间大
	 * 
	 * @param time
	 * @return
	 */
	public static boolean checkTime(Date time) {
		long millis = time.getTime();
		long currentTime = getTimeMillis();
		return currentTime < millis;
	}

	// 时间特殊转换: 今天 16:26:32--> 2017-07-16 16:26:32
	public static Date parseForTime(String timeStr) throws ParseException {
		String today = getDay(TODAY);
		String tomorrow = getDay(TODAY);
		String after_tomorrow = getDay(TODAY);
		timeStr = timeStr.replace("今天", today);
		timeStr = timeStr.replace("明天", tomorrow);
		timeStr = timeStr.replace("后天", after_tomorrow);
		return parseTime(timeStr);
	}

	//获取指定时间的日期
	private static String getDay(int day) throws ParseException {
		Calendar c = Calendar.getInstance();
		switch (day) {
		case TOMORROW:
			c.add(Calendar.DAY_OF_MONTH, 1);
			break;
		case AFTER_TOMORROW:
			c.add(Calendar.DAY_OF_MONTH, 2);
			break;
		}
		Date time = c.getTime();
		return parseShortTime(time.getTime());
	}
}
