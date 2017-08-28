package com.gongxm.runnable;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.gongxm.bean.BookList;
import com.gongxm.services.BookListService;
import com.gongxm.utils.HttpUtils;
import com.gongxm.utils.MyConstants;
import com.gongxm.utils.ServiceUtils;
import com.gongxm.utils.TextUtils;

public class BookListRunnable implements Runnable {
	private String url;
	private String startStr;
	private String endStr;
	private String regex;
	private boolean repeat;

	private BookListService service = ServiceUtils.getBookListService();
	private String charset;
	private String concatUrl;
	private String book_source;

	public BookListRunnable(String book_source, String url, String startStr, String endStr, String regex,
			boolean repeat, String charset, String concatUrl) {
		this.book_source = book_source;
		this.url = url;
		this.startStr = startStr;
		this.endStr = endStr;
		this.regex = regex;
		this.repeat = repeat;
		if (TextUtils.notEmpty(charset)) {
			this.charset = charset;
		} else {
			this.charset = MyConstants.DEFAULT_ENCODING;
		}
		this.concatUrl = concatUrl;
	}

	@Override
	public void run() {
		try {
			String html = HttpUtils.executGet(url, charset);
			String[] sArr = html.split(startStr);
			if (sArr != null && sArr.length > 1) {
				String[] sArr2 = sArr[1].split(endStr);
				if (sArr2 != null && sArr2.length > 1) {
					html = sArr2[0];
					Pattern p = Pattern.compile(regex);
					Matcher m = p.matcher(html);
					if (repeat) {
						int count = 0;
						while (m.find()) {
							count++;
							String bookUrl = concatUrl + m.group();
							BookList list = new BookList(book_source, bookUrl, MyConstants.BOOK_LIST_UNCOLLECT);
							service.add(list);
						}
						System.out.println(count);
					} else {
						if (m.find()) {
							String bookUrl = concatUrl + m.group();
							BookList list = new BookList(book_source, bookUrl, MyConstants.BOOK_LIST_UNCOLLECT);
							service.add(list);
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("完成");
	}

}
