package com.gongxm.test;

import java.io.IOException;

import org.junit.Test;

import com.gongxm.bean.BookChapter;
import com.gongxm.services.BookChapterService;
import com.gongxm.utils.CollectUtils;
import com.gongxm.utils.ServiceUtils;

public class MyTest {

	
	@Test
	public void test6(){
		//https://31412947.qcloud.la/BookReaderServer/collect?id=1
	}
	
	public static void main(String[] args) throws IOException {
//		demo1();
//		demo2();
//		demo3();
//		demo4();
		
	}

	private static void demo4() {
		BookChapterService service = ServiceUtils.getBookChapterService();
		BookChapter chapter = service.findOne(8140);
//		System.out.println(chapter.getChapterContent().getContent());
		String content = chapter.getChapterContent().getContent();
		for(int i=0;i<4;i++) {
			System.out.println(content.charAt(i));
		}
		
	}

	public static void demo3() {
		String startStr = "<div class=\"yd_text2\">";
		String endStr = "<div class=\"yd_ad1\">";
		System.out.println("第三步启动");
		CollectUtils.collectBookChapter(startStr,endStr);
	}

	public static void demo2() {
		String[] regexs={"<meta property=\"og:novel:book_name\" content=\".{1,20}\">",
				"<meta property=\"og:novel:author\" content=\".{1,10}\">",
				"<meta property=\"og:novel:category\" content=\".{2,8}\">",
				"<meta property=\"og:novel:status\" content=\".{2,5}\">",
				"<meta property=\"og:image\" content=\".{5,100}\">",
				"og:description\" content=\".{10,1500}",
				"<li><a href=\"\\d+\\.(html){1}\">",
				"html\">.{1,100}</a></li>"
				};
		String startStr="<div class=\"mulu\">";
		String endStr="<div id=\"footer\">";
		String charset="gbk";
		try {
			System.out.println("第二步启动");
			CollectUtils.collectBookInfo(null, regexs, startStr, endStr, charset);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static void demo1() {
		String book_source="www.88dushu.com";
		String baseUrl="http://www.88dushu.com/sort2/(*)/";
		String flag="(*)";
		int startIndex=1;
		int endIndex=53;
		String startStr="<div class=\"booklist\">";
		String endStr="<div class=\"pagelink\" id=\"pagelink\">";
		String regex="<span class=\"sm\"><a href=\"(/xiaoshuo/)[0-9/]*\">";
		boolean repeat = true;
		String charset = "gbk";
		String concatUrl = "http://www.88dushu.com";
		System.out.println("第一步启动");
		CollectUtils.collectBookList(book_source,baseUrl, flag, startIndex, endIndex, startStr, endStr, regex, repeat,charset,concatUrl);
	}
	
	//查询重复记录
	//select book_link ,count(*) as count  from book_list group by book_link having count>1; 

}
