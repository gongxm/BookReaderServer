package com.gongxm.test;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.gongxm.bean.Book;
import com.gongxm.bean.BookChapterContentRules;
import com.gongxm.dao.BookDao;
import com.gongxm.services.BookListRulesService;
import com.gongxm.services.BookListService;
import com.gongxm.services.BookService;
import com.gongxm.services.UserService;
import com.gongxm.utils.CollectUtils;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class MyTest {
	
	@Autowired
	@Qualifier("bookDao")
	protected BookDao bookDao;  
	
	
	
	@Autowired
	UserService userService;
	
	@Autowired
	BookListRulesService service;
	
	@Autowired
	BookService bookService;
	
	@Autowired
	@Qualifier("bookListService")
	BookListService bookListService;
	
	@Test
	@Transactional
	public void test9() {
	
	}
	

	
	

	public static void main(String[] args) throws IOException {

	}


	public static void demo3() {
		String startStr = "<div class=\"yd_text2\">";//<div class="yd_text2">
		String endStr = "<div class=\"yd_ad1\">";//<div class="yd_ad1">
		System.out.println("第三步启动");
		CollectUtils.collectBookChapter(null,new BookChapterContentRules());
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
		
		//<meta property="og:novel:book_name" content=".{1,20}">
		
		//<meta property="og:novel:author" content=".{1,10}">
		
		//<meta property="og:novel:category" content=".{2,8}">
		
		//<meta property="og:novel:status" content=".{2,5}">
		
		//<meta property="og:image" content=".{5,100}">
		
		//og:description" content=".{10,1500}
		
		//<li><a href="\d+\.(html){1}">
		
		//html">.{1,100}</a></li>
		
		String startStr="<div class=\"mulu\">";//<div class="mulu">
		String endStr="<div id=\"footer\">";//<div id="footer">
		String charset="gbk";
		String concatUrl="";
		boolean useBookLink=true;
		try {
			System.out.println("第二步启动");
		//	CollectUtils.collectBookInfo(bookListService,null,new BookInfoAndChapterListRules());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public static void demo1() {
		String book_source="www.88dushu.com";
		String baseUrl="http://www.88dushu.com/sort2/(*)/";
		String flag="(*)";
		int startIndex=1;
		int endIndex=53;
		String startStr="<div class=\"booklist\">";//<div class="booklist">
		String endStr="<div class=\"pagelink\" id=\"pagelink\">";//<div class="pagelink" id="pagelink">
		String regex="<span class=\"sm\"><a href=\"(/xiaoshuo/)[0-9/]*\">";//<span class="sm"><a href="(/xiaoshuo/)[0-9/]*">
		boolean repeat = true;
		String charset = "gbk";
		String concatUrl = "http://www.88dushu.com";
		System.out.println("第一步启动");
		//CollectUtils.collectBookList(new BookListRules());
	}
	
	//查询重复记录
	//select book_link ,count(*) as count  from book_list group by book_link having count>1; 

}
