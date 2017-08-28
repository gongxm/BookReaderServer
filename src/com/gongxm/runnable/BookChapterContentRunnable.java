package com.gongxm.runnable;

import java.io.IOException;

import com.gongxm.bean.BookChapter;
import com.gongxm.utils.HtmlParser;

public class BookChapterContentRunnable implements Runnable {

	private BookChapter chapter;
	private String startStr;
	private String endStr;

	public BookChapterContentRunnable(BookChapter chapter,String startStr, String endStr) {
		this.chapter=chapter;
		this.startStr =startStr;
		this.endStr=endStr;
	}

	@Override
	public void run() {
		String url = chapter.getChapter_link();
		try {
			String text = HtmlParser.parseToText(url, startStr, endStr);
			System.out.println(text);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
