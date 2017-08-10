package com.gongxm.utils;

public class TextUtils {

	/**
	 * �ж��ַ������ǿ�
	 * 
	 * @param strings
	 * @return
	 */
	public static boolean notEmpty(String... strings) {
		return !isEmpty(strings);
	}

	/**
	 * �ж�һ���ַ����Ƿ�Ϊ��
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return (str == null || str.trim().length() == 0);
	}

	/**
	 * �ж϶���ַ����Ƿ���Ϊ�յ�
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
	 * �ж��ַ����Ƿ����ֻ�����
	 * 
	 * @param phone
	 * @return
	 */
	public static boolean isPhone(String phone) {
		String regex = "1[345678]\\d{9}";
		return phone != null && phone.matches(regex);
	}

}
