package com.gongxm.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.gongxm.bean.Book;
import com.gongxm.bean.BookChapter;
import com.gongxm.bean.Rules;
import com.gongxm.bean.User;
import com.gongxm.services.BookService;
import com.gongxm.services.UserService;
import com.gongxm.utils.GsonUtils;
import com.gongxm.utils.MyConstants;
import com.gongxm.utils.ServiceUtils;
import com.gongxm.utils.StringConstants;

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

}
