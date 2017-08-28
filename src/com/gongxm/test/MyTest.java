package com.gongxm.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.gongxm.bean.Book;
import com.gongxm.bean.BookChapter;
import com.gongxm.bean.BookList;
import com.gongxm.bean.Rules;
import com.gongxm.bean.User;
import com.gongxm.runnable.BookInfoRunnable;
import com.gongxm.services.BookListService;
import com.gongxm.services.BookService;
import com.gongxm.services.UserService;
import com.gongxm.utils.CollectUtils;
import com.gongxm.utils.GsonUtils;
import com.gongxm.utils.ServiceUtils;
import com.gongxm.utils.ThreadPoolUtil;

public class MyTest {
	@Test
	public void test1() {
		User user = new User();
		user.setUsername("张三");
		user.setPassword("123");
		user.setPermissions("user");

		UserService us = ServiceUtils.getUserService();
		//us.addUser(user);

	}

	@Test
	public void test2() {
		Rules rules = new Rules();
		rules.setUrl("http://www.baidu.com");
		rules.setStartStr("abc");
		rules.setEndStr("cmd");
		rules.setStartIndex(2);
		rules.setEndIndex(20);
		rules.setRepeat(false);
		rules.setCurrent(true);
		//ServiceUtils.getRulesService().add(rules);
	}

	@Test
	public void test3() {
		BookService bookService = ServiceUtils.getBookService();

		List<String> categories = bookService.getBookCategory();
		
		System.out.println(categories);
	}
	
	@Test
	public void test4(){
		BookService bookService = ServiceUtils.getBookService();
		List<Book> list = bookService.getCategoryList("都市",2,5);
		System.out.println(list.size());
	}
	
	
	@Test
	public void test5() {
		/*BookChapter ch = new BookChapter();
		ch.setChapter_link("http://www.baidu.com");
		ch.setChapter_name("百度一下");
		
		List<BookChapter> list = new ArrayList<>();
		list.add(ch);
		String json = GsonUtils.toJson(list);
		System.out.println(json);*/
		
		BookService bookService = ServiceUtils.getBookService();
		Book book = bookService.findOne(3411);
		List<BookChapter> list = bookService.findBookChapterList(book.getBook_link());
		List<BookChapter> newList = new ArrayList<>();
		if (list != null) {
			for (BookChapter bookChapter : list) {
				newList.add(bookChapter);
			}
		}
		System.out.println(GsonUtils.toJson(newList));
	}
	
	
	
	
	@Test
	public void test6(){
		
	}
	
	public static void main(String[] args) throws IOException {
		String url="http://www.88dushu.com/xiaoshuo/85/85614/";
		String[] regexs={"<meta property=\"og:novel:book_name\" content=\".{1,20}\">",
				"<meta property=\"og:novel:author\" content=\".{1,10}\">",
				"<meta property=\"og:novel:category\" content=\".{2,8}\">",
				"<meta property=\"og:novel:status\" content=\".{2,5}\">",
				"<meta property=\"og:image\" content=\".{5,100}\">",
				"og:description\" content=\".{10,500}",
				"<li><a href=\"\\d+\\.(html){1}\">",
				">.{1,20}</a></li>"
				};
		String startStr="<div class=\"mulu\">";
		String endStr="<div id=\"footer\">";
		String charset="gbk";
		String concatUrl=url;
		BookInfoRunnable task = new BookInfoRunnable(url,regexs,startStr,endStr,concatUrl,charset);
		new Thread(task).start();
		
	}

	private static void demo1() {
		String book_source="www.88dushu.com";
		String baseUrl="http://www.88dushu.com/sort2/(*)/";
		String flag="(*)";
		int startIndex=1;
		int endIndex=53;
		String startStr="<div class=\"booklist\">";
		String endStr="<div class=\"pagelink\" id=\"pagelink\">";
		String regex="/xiaoshuo/[0-9/]*/";
		boolean repeat = true;
		String charset = "gbk";
		String concatUrl = "http://www.88dushu.com";
		
		CollectUtils.collectBookList(book_source,baseUrl, flag, startIndex, endIndex, startStr, endStr, regex, repeat,charset,concatUrl);
	}
	
	

}
