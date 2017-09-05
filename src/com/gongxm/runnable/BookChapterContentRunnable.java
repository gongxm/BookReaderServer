package com.gongxm.runnable;

import java.io.IOException;

import org.springframework.web.context.WebApplicationContext;

import com.gongxm.bean.Book;
import com.gongxm.bean.BookChapter;
import com.gongxm.bean.BookChapterContent;
import com.gongxm.bean.BookChapterContentRules;
import com.gongxm.services.BookChapterService;
import com.gongxm.utils.HtmlParser;
import com.gongxm.utils.MyConstants;

public class BookChapterContentRunnable implements Runnable {

	private BookChapter chapter;
	private String startStr;
	private String endStr;
	private BookChapterService service;

	public BookChapterContentRunnable(BookChapter chapter,BookChapterContentRules rules, WebApplicationContext context) {
		this.chapter=chapter;
		this.startStr =rules.getStartStr();
		this.endStr=rules.getEndStr();
		this.service=(BookChapterService) context.getBean("bookChapterService");
	}

	@Override
	public void run() {
		String url = chapter.getChapter_link();
		try {
			System.out.println("colleting:"+chapter.getId());
			String text = HtmlParser.parseToText(url, startStr, endStr);
			BookChapterContent content = new BookChapterContent(text);
			chapter.setChapterContent(content);
			chapter.setStatus(MyConstants.BOOK_COLLECTED);
			synchronized (Book.class) {
				service.update(chapter);
				System.out.println("collect success...");
			}
		} catch (IOException e) {
			System.out.println("collect error....");
			e.printStackTrace();
			chapter.setStatus(MyConstants.BOOK_COLLECT_FAILURE);
			synchronized (Book.class) {
				service.update(chapter);
			}
		}
	}

}
