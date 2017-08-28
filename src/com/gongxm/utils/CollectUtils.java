package com.gongxm.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.gongxm.bean.Book;
import com.gongxm.bean.BookChapter;
import com.gongxm.bean.BookList;
import com.gongxm.runnable.BookChapterContentRunnable;
import com.gongxm.runnable.BookInfoRunnable;
import com.gongxm.runnable.BookListRunnable;
import com.gongxm.services.BookListService;
import com.gongxm.services.BookService;

//数据采集工具
public class CollectUtils {

	// 书籍列表
	public static void collectBookList(String book_source, String baseUrl, String flag, int startIndex, int endIndex,
			String startStr, String endStr, String regex, boolean repeat, String charset, String concatUrl) {
		List<String> urls = new ArrayList<>();
		for (int i = startIndex; i <= endIndex; i++) {
			if (TextUtils.notEmpty(flag)) {
				String url = baseUrl.replace(flag, i + "");
				urls.add(url);
			} else {
				urls.add(baseUrl);
			}
		}
		for (String url : urls) {
			BookListRunnable task = new BookListRunnable(book_source, url, startStr, endStr, regex, repeat, charset,
					concatUrl);
			ThreadPoolUtil.executeOnNewThread(task);
		}

	}

	// 书籍信息和章节列表
	public static void collectBookInfo(String book_source, String[] regexs, String startStr, String endStr,
		String charset) throws IOException {
		BookListService service = ServiceUtils.getBookListService();
		int currentPage = 1;
		int pageSize = 20;

		if (TextUtils.notEmpty(book_source)) {
			long total = service.getUnCollectBookListCountBySource(book_source);
			long page = 0;
			if (total % pageSize == 0) {
				page = total / pageSize;
			} else {
				page = total / pageSize + 1;
			}

			while (currentPage <= page) {
				List<BookList> list = service.findUnCollectBookListBySource(book_source, currentPage, pageSize);
				for (BookList bookList : list) {
					BookInfoRunnable task = new BookInfoRunnable(bookList, regexs, startStr, endStr,
							bookList.getBook_link(), charset);
					ThreadPoolUtil.executeOnNewThread(task);
				}
				currentPage++;
			}

		} else {
			long total = service.getAllUnCollectBookListCount();
			long page = 0;
			if (total % pageSize == 0) {
				page = total / pageSize;
			} else {
				page = total / pageSize + 1;
			}
			while (currentPage <= page) {
				List<BookList> list = service.findAllUnCollectBookList(currentPage, pageSize);
				for (BookList bookList : list) {
					BookInfoRunnable task = new BookInfoRunnable(bookList, regexs, startStr, endStr,
							bookList.getBook_link(), charset);
					ThreadPoolUtil.executeOnNewThread(task);
				}
				currentPage++;
			}
		}
	}


	// 书籍章节内容
	public static void collectBookChapter(int bookid,String startStr, String endStr) {
		BookService bookService = ServiceUtils.getBookService();
		Book book = bookService.findOne(bookid);
		
		Set<BookChapter> chapters = book.getChapters();
		for (BookChapter chapter : chapters) {
			System.out.println(chapter);
			BookChapterContentRunnable task = new BookChapterContentRunnable(chapter,startStr,endStr);
			ThreadPoolUtil.executeOnNewThread(task);
			break;
		}
		
	}

}
