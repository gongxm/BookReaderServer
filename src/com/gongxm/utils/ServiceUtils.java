package com.gongxm.utils;

import com.gongxm.services.BookChapterService;
import com.gongxm.services.BookListService;
import com.gongxm.services.BookService;
import com.gongxm.services.UserService;
import com.gongxm.services.impl.BookChapterServiceImpl;
import com.gongxm.services.impl.BookListServiceImpl;
import com.gongxm.services.impl.BookServiceImpl;
import com.gongxm.services.impl.UserServiceImpl;

public class ServiceUtils {
	// 获取用户服务
	public static UserService getUserService() {
		return UserServiceImpl.getInstance();
	}

	// 获取书籍服务
	public static BookService getBookService() {
		return BookServiceImpl.getInstance();
	}

	// 获取书籍列表服务
	public static BookListService getBookListService() {
		return BookListServiceImpl.getInstance();
	}

	// 获取书籍章节服务
	public static BookChapterService getBookChapterService() {
		return BookChapterServiceImpl.getInstance();
	}
}
