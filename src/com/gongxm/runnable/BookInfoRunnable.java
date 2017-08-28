package com.gongxm.runnable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.gongxm.utils.HttpUtils;

public class BookInfoRunnable implements Runnable {

	private String charset;
	private String endStr;
	private String startStr;
	private String url;
	private String[] regexs;
	private String concatUrl;

	public BookInfoRunnable(String url, String[] regexs, String startStr, String endStr, String concatUrl, String charset) {
		this.url = url;
		this.regexs = regexs;
		this.startStr = startStr;
		this.endStr = endStr;
		this.concatUrl=concatUrl;
		this.charset = charset;
	}

	@Override
	public void run() {
		if(regexs==null||regexs.length<7){
			return;
		}
		try {
			String html = HttpUtils.executGet(url, charset);

			// 标题
//			String titleRegex = regexs[0];
			// 作者
//			String authorRegex = regexs[1];
			// 类别
//			String categoryRegex = regexs[2];
			// 状态
//			String statusRegex = regexs[3];
			// 封面
//			String coverRegex = regexs[4];
			// 简介
//			String shortIntroduceRegex = regexs[5];
			
			List<String> list = new ArrayList<>();
			
			for(int i=0;i<6;i++){
				String regex = regexs[i];
				Pattern p = Pattern.compile(regex);
				Matcher m = p.matcher(html);
				if(m.find()){
					String text = m.group();
					text = dealWithText(text, regex);
					list.add(text);
				}
			}
			
			//列表正则
			String chapterLinkRegex = regexs[6];
			//标题正则
			String chapterTitleRegex = regexs[7];
			String[] sArr = html.split(startStr);
			if (sArr != null && sArr.length > 1) {
				String[] sArr2 = sArr[1].split(endStr);
				if (sArr2 != null && sArr2.length > 1) {
					html = sArr2[0];
					Pattern p = Pattern.compile(chapterLinkRegex);
					Matcher m = p.matcher(html);
					while(m.find()){
						String chapterLink = m.group();
						System.out.println(concatUrl+dealWithText(chapterLink, chapterLinkRegex));
					}
					
					Pattern p2 = Pattern.compile(chapterTitleRegex);
					Matcher m2 = p2.matcher(html);
					while(m2.find()){
						String chapterTitle = m2.group();
						System.out.println(dealWithText(chapterTitle, chapterTitleRegex));
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String dealWithText(String src,String reg){
		int length =0;
		for(int i=0;i<reg.length();i++){
			if(src.charAt(i)!=reg.charAt(i)){
				length=i;
				break;
			}
		}
		String str = src.substring(length);
		length=0;
		
		str = new StringBuilder(str).reverse().toString();
		reg = new StringBuilder(reg).reverse().toString();
		for(int i=0;i<reg.length();i++){
			if(str.charAt(i)!=reg.charAt(i)){
				length=i;
				break;
			}
		}
		String result = str.substring(length);
		return new StringBuilder(result).reverse().toString();
	}

}
