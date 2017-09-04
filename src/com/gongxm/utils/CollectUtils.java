package com.gongxm.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.gongxm.bean.BookChapter;
import com.gongxm.bean.BookChapterContentRules;
import com.gongxm.bean.BookInfoAndChapterListRules;
import com.gongxm.bean.BookList;
import com.gongxm.bean.BookListRules;
import com.gongxm.runnable.BookChapterContentRunnable;
import com.gongxm.runnable.BookInfoRunnable;
import com.gongxm.runnable.BookListRunnable;
import com.gongxm.services.BookChapterService;
import com.gongxm.services.BookListService;

//数据采集工具
public class CollectUtils {

	public static volatile int threadCount;

	// 书籍列表
	public static void collectBookList(BookListService service,BookChapterService chapterService, BookListRules bookListRules) {
		System.out.println("======开始采集目录=======");
		threadCount = 0;
		List<String> urls = new ArrayList<>();
		String baseUrl = bookListRules.getBaseUrl();
		for (int i = bookListRules.getStartIndex(); i <= bookListRules.getEndIndex(); i++) {
			if (TextUtils.notEmpty(bookListRules.getFlag())) {
				String url = baseUrl.replace(bookListRules.getFlag(), i + "");
				urls.add(url);
			} else {
				urls.add(baseUrl);
			}
		}
		for (String url : urls) {
			threadCount++;
			BookListRunnable task = new BookListRunnable(bookListRules, url);
			ThreadPoolUtil.executeOnNewThread(task);
		}

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (threadCount > 0) {
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("还在采集目录中.......");
				}

				try {
					collectBookInfo(service,chapterService, bookListRules.getBook_source(), bookListRules);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();

	}

	// 书籍信息和章节列表
	public static void collectBookInfo(BookListService service, BookChapterService chapterService, String book_source,
			BookListRules bookListRules) throws IOException {
		threadCount=0;
		BookInfoAndChapterListRules rules = bookListRules.getRules();
		if (rules == null) {
			System.out.println("缺少书籍信息规则!!!!!!!!!!!!!!");
			return;
		}
		System.out.println("======开始书籍信息=======");
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
					threadCount++;
					String concatUrl2 = rules.isUseBookLink() ? bookList.getBook_link() : rules.getConcatUrl();
					BookInfoRunnable task = new BookInfoRunnable(bookList, concatUrl2, rules);
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
					threadCount++;
					String concatUrl2 = rules.isUseBookLink() ? bookList.getBook_link() : rules.getConcatUrl();
					BookInfoRunnable task = new BookInfoRunnable(bookList, concatUrl2, rules);
					ThreadPoolUtil.executeOnNewThread(task);
				}
				currentPage++;
			}
		}

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (threadCount > 0) {
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("还在采集书籍信息中.......");
				}

				collectBookChapter(chapterService, bookListRules.getContentRules());
			}
		}).start();
	}

	// 书籍章节内容
	public static void collectBookChapter(BookChapterService service, BookChapterContentRules rules) {
		
		System.out.println("==============开始采集章节内容====================");

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
				BookChapterContentRunnable task = new BookChapterContentRunnable(chapter, rules, service);
				ThreadPoolUtil.executeOnNewThread(task);
			}
			currentPage++;
		}

	}

}
