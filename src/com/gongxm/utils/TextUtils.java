package com.gongxm.utils;

public class TextUtils {

	/**
	 * 判断字符串不是空
	 * 
	 * @param strings
	 * @return
	 */
	public static boolean notEmpty(String... strings) {
		return !isEmpty(strings);
	}

	/**
	 * 判断一个字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return (str == null || str.trim().length() == 0);
	}

	/**
	 * 判断多个字符串是否有为空的
	 * 
	 * @param strings
	 * @return
	 */
	public static boolean isEmpty(String... strings) {
		for (int i = 0; i < strings.length; i++) {
			if (strings[i] == null || strings[i].trim().length() == 0) {
				return true;
			}
		}
		return false;
	}

	
	/**
	 * 判断字符串是否是手机号码
	 * 
	 * @param phone
	 * @return
	 */
	public static boolean isPhone(String phone) {
		String regex = "1[345678]\\d{9}";
		return phone != null && phone.matches(regex);
	}

}
