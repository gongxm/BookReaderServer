package com.gongxm.runnable;

import java.io.IOException;

import com.gongxm.bean.Book;
import com.gongxm.bean.BookChapter;
import com.gongxm.bean.BookChapterContent;
import com.gongxm.services.BookChapterService;
import com.gongxm.utils.HtmlParser;
import com.gongxm.utils.MyConstants;

public class BookChapterContentRunnable implements Runnable {

	private BookChapter chapter;
	private String startStr;
	private String endStr;
	private BookChapterService service;

	public BookChapterContentRunnable(BookChapter chapter,String startStr, String endStr, BookChapterService service) {
		this.chapter=chapter;
		this.startStr =startStr;
		this.endStr=endStr;
		this.service=service;
	}

	@Override
	public void run() {
		String url = chapter.getChapter_link();
		try {
			String text = HtmlParser.parseToText(url, startStr, endStr);
			BookChapterContent content = new BookChapterContent(text);
			chapter.setChapterContent(content);
			chapter.setStatus(MyConstants.BOOK_COLLECTED);
			synchronized (Book.class) {
				service.update(chapter);
//				System.out.println(chapter.getChapterContent().getContent());
			}
		} catch (IOException e) {
			e.printStackTrace();
			chapter.setStatus(MyConstants.BOOK_COLLECT_FAILURE);
			synchronized (Book.class) {
				service.update(chapter);
			}
		}
	}

}
