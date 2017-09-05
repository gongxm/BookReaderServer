package com.gongxm.runnable;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.gongxm.bean.Book;
import com.gongxm.bean.BookList;
import com.gongxm.bean.BookListRules;
import com.gongxm.services.BookListService;
import com.gongxm.utils.CollectUtils;
import com.gongxm.utils.HttpUtils;
import com.gongxm.utils.MyConstants;
import com.gongxm.utils.TextUtils;

public class BookListRunnable implements Runnable {
	private String url;
	private String startStr;
	private String endStr;
	private String regex;
	private boolean repeat;
	private BookListService service;
	private String charset;
	private String concatUrl;
	private String book_source;

	public BookListRunnable(WebApplicationContext context, BookListRules bookListRules, String url) {
		this.book_source = bookListRules.getBook_source();
		this.url = url;
		this.startStr = bookListRules.getStartStr();
		this.endStr = bookListRules.getEndStr();
		this.regex = bookListRules.getRegex();
		this.repeat = bookListRules.isRepeat();
		String charset = bookListRules.getCharset();
		if (TextUtils.notEmpty(charset)) {
			this.charset = charset;
		} else {
			this.charset = MyConstants.DEFAULT_ENCODING;
		}
		this.concatUrl = bookListRules.getConcatUrl();
		
		this.service = (BookListService) context.getBean("bookListService");
	}

	@Override
	@Transactional
	public void run() {
		try {
			System.out.println("采集:"+url);
			String html = HttpUtils.executGet(url, charset);
			String[] sArr = html.split(startStr);
			if (sArr != null && sArr.length > 1) {
				String[] sArr2 = sArr[1].split(endStr);
				if (sArr2 != null && sArr2.length > 1) {
					html = sArr2[0];
					Pattern p = Pattern.compile(regex);
					Matcher m = p.matcher(html);
					if (repeat) {
						while (m.find()) {
							synchronized (Book.class) {
								String book_link = m.group();
								book_link = TextUtils.dealWithText(book_link, regex);
								System.out.println("concatUrl="+concatUrl);
								String bookUrl = concatUrl + book_link;
								BookList temp = service.findByBookLink(bookUrl);
								if (temp == null) {
									BookList list = new BookList(book_source, bookUrl, MyConstants.BOOK_UNCOLLECT);
									service.add(list);
								}
							}
						}
					} else {
						if (m.find()) {
							String book_link = m.group();
							book_link = TextUtils.dealWithText(book_link, regex);
							String bookUrl = concatUrl + book_link;
							BookList temp = service.findByBookLink(bookUrl);
							if (temp == null) {
								BookList list = new BookList(book_source, bookUrl, MyConstants.BOOK_UNCOLLECT);
								service.add(list);
							}
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		CollectUtils.threadCount--;
		System.out.println("----完成----");
	}

}
