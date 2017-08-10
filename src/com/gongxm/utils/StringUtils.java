package com.gongxm.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class StringUtils {
	/**
	 * 把一个流里面的内容 转化成一个字符串
	 * @param is 流里面的内容
	 * @return null解析失败
	 */
    public static String readStream(InputStream is, String encoding){
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = -1;
            while((len = is.read(buffer))!=-1){
                baos.write(buffer, 0, len);
            }
            is.close();
            return new String(baos.toByteArray(),encoding);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 把一个流里面的内容 转化成一个字符串,默认使用utf-8编码
     * @param is 流里面的内容
     * @return null解析失败
     */
    public static String readStream(InputStream is){
        return readStream(is,MyConstants.DEFAULT_ENCODING);
    }
	/**
	 * 生成32位随机字符串
	 * @param length
	 * @return
	 */
	public static String getRandomStringByLength(int length) {  
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";  
        Random random = new Random();  
        StringBuffer sb = new StringBuffer();  
        for (int i = 0; i < length; i++) {  
            int number = random.nextInt(base.length());  
            sb.append(base.charAt(number));  
        }  
        return sb.toString();  
    } 
	
}
