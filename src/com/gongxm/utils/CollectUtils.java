package com.gongxm.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.gongxm.bean.BookChapter;
import com.gongxm.bean.BookList;
import com.gongxm.runnable.BookChapterContentRunnable;
import com.gongxm.runnable.BookInfoRunnable;
import com.gongxm.runnable.BookListRunnable;
import com.gongxm.services.BookChapterService;
import com.gongxm.services.BookListService;

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
	public static void collectBookInfo(BookListService service, String book_source, String[] regexs, String startStr,
			String endStr, String charset, String concatUrl, boolean useBookLink) throws IOException {
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
					String concatUrl2 = useBookLink ? bookList.getBook_link() : concatUrl;
					BookInfoRunnable task = new BookInfoRunnable(bookList, regexs, startStr, endStr, concatUrl2,
							charset);
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
	public static void collectBookChapter(BookChapterService service, String startStr, String endStr) {

		int currentPage = 1;
		int pageSize = 20;

		long total = service.getUnCollectChapterCount();

		long page = 0;
		if (total % pageSize == 0) {
			page = total / pageSize;
		} else {
			page = total / pageSize + 1;
		}

		while (currentPage <= page) {
			List<BookChapter> chapters = service.findUnCollectChapter(currentPage, pageSize);
			for (BookChapter chapter : chapters) {
				BookChapterContentRunnable task = new BookChapterContentRunnable(chapter, startStr, endStr, service);
				ThreadPoolUtil.executeOnNewThread(task);
			}
			currentPage++;
		}

	}

}
