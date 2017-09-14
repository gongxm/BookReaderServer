package com.gongxm.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.context.WebApplicationContext;

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
	public static volatile boolean collecting;

	// 书籍列表
	public static void collectBookList(WebApplicationContext context, BookListService service,
			BookChapterService chapterService, BookListRules bookListRules, boolean collectAll) {
		collecting = true;
		threadCount = 0;
		new Thread() {
			public void run() {
				System.out.println("======开始采集目录=======");
				List<String> urls = new ArrayList<>();
				String baseUrl = bookListRules.getBaseUrl();
				threadCount++;
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
					BookListRunnable task = new BookListRunnable(context, bookListRules, url);
					ThreadPoolUtil.executeOnNewThread(task);
				}
				threadCount--;
			};
		}.start();

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
				collecting = false;
				if (collectAll) {
					try {
						collectBookInfo(context, bookListRules.getBook_source(), bookListRules, true);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

	}

	// 书籍信息和章节列表
	public static void collectBookInfo(WebApplicationContext context, String book_source, BookListRules bookListRules,
			boolean collectAll) throws IOException {
		collecting = true;
		new Thread() {
			public void run() {
				BookListService service = (BookListService) context.getBean("bookListService");
				threadCount = 1;
				BookInfoAndChapterListRules rules = bookListRules.getRules();
				if (rules == null) {
					System.out.println("缺少书籍信息规则!!!!!!!!!!!!!!");
					threadCount = 0;
					return;
				}
				System.out.println("============================开始采集书籍信息===============================");
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
							BookInfoRunnable task = new BookInfoRunnable(context, bookList, rules);
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
							BookInfoRunnable task = new BookInfoRunnable(context, bookList, rules);
							ThreadPoolUtil.executeOnNewThread(task);
						}
						currentPage++;
					}
				}
				threadCount--;
			};
		}.start();

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
				collecting = false;

				if (collectAll) {
					collectBookChapter(context, bookListRules.getContentRules());
				}
			}
		}).start();
	}

	// 书籍章节内容
	public static void collectBookChapter(WebApplicationContext context, BookChapterContentRules rules) {

		System.out.println("==============开始采集章节内容====================");
		collecting = true;

		new Thread() {
			public void run() {
				threadCount++;
				BookChapterService service = (BookChapterService) context.getBean("bookChapterService");
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
						BookChapterContentRunnable task = new BookChapterContentRunnable(chapter, rules, context);
						ThreadPoolUtil.executeOnNewThread(task);
					}
					currentPage++;
				}
				threadCount--;
			};
		}.start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (threadCount > 0) {
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("还在采集章节内容中.......");
				}
				collecting = false;
			}
		}).start();
	}

}
