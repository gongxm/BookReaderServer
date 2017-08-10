package com.gongxm.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class StringUtils {
	/**
	 * ��һ������������� ת����һ���ַ���
	 * @param is �����������
	 * @return null����ʧ��
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
     * ��һ������������� ת����һ���ַ���,Ĭ��ʹ��utf-8����
     * @param is �����������
     * @return null����ʧ��
     */
    public static String readStream(InputStream is){
        return readStream(is,MyConstants.DEFAULT_ENCODING);
    }
	/**
	 * ����32λ����ַ���
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
