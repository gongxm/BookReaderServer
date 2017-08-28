package com.gongxm.runnable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.gongxm.bean.Book;
import com.gongxm.bean.BookChapter;
import com.gongxm.bean.BookList;
import com.gongxm.services.BookChapterService;
import com.gongxm.services.BookListService;
import com.gongxm.services.BookService;
import com.gongxm.utils.HttpUtils;
import com.gongxm.utils.MyConstants;
import com.gongxm.utils.ServiceUtils;
import com.gongxm.utils.TextUtils;

public class BookInfoRunnable implements Runnable {

	private String charset;
	private String endStr;
	private String startStr;
	private String[] regexs;
	private String concatUrl;
	private BookList bookList;

	public BookInfoRunnable(BookList bookList, String[] regexs, String startStr, String endStr, String concatUrl, String charset) {
		this.bookList = bookList;
		this.regexs = regexs;
		this.startStr = startStr;
		this.endStr = endStr;
		this.concatUrl=concatUrl;
		this.charset = charset;
	}

	@Override
	public void run() {
		if(regexs==null||regexs.length<7){
			return;
		}
		try {
			String url = bookList.getBook_link();
			String html = HttpUtils.executGet(url, charset);

			// 标题
//			String titleRegex = regexs[0];
			// 作者
//			String authorRegex = regexs[1];
			// 类别
//			String categoryRegex = regexs[2];
			// 状态
//			String statusRegex = regexs[3];
			// 封面
//			String coverRegex = regexs[4];
			// 简介
//			String shortIntroduceRegex = regexs[5];
			
			List<String> list = new ArrayList<>();
			
			for(int i=0;i<6;i++){
				String regex = regexs[i];
				Pattern p = Pattern.compile(regex);
				Matcher m = p.matcher(html);
				if(m.find()){
					String text = m.group();
					text = TextUtils.dealWithText(text, regex);
					list.add(text);
				}
			}
			
			BookService bookService = ServiceUtils.getBookService();
			
			BookChapterService chapterService = ServiceUtils.getBookChapterService();
			
			Book book = new Book(list.get(0), list.get(1), list.get(2), list.get(3), list.get(4),list.get(5),url);
			bookService.add(book);
			//目录链接正则
			String chapterLinkRegex = regexs[6];
			//目录标题正则
			String chapterTitleRegex = regexs[7];
			String[] sArr = html.split(startStr);
			if (sArr != null && sArr.length > 1) {
				String[] sArr2 = sArr[1].split(endStr);
				if (sArr2 != null && sArr2.length > 1) {
					List<String> chapterLinkList = new ArrayList<>();
					List<String> chapterTitleList = new ArrayList<>();
					html = sArr2[0];
					Pattern p = Pattern.compile(chapterLinkRegex);
					Matcher m = p.matcher(html);
					while(m.find()){
						String chapterLink = m.group();
						chapterLinkList.add(concatUrl+TextUtils.dealWithText(chapterLink, chapterLinkRegex));
//						System.out.println(concatUrl+TextUtils.dealWithText(chapterLink, chapterLinkRegex));
					}
					
					Pattern p2 = Pattern.compile(chapterTitleRegex);
					Matcher m2 = p2.matcher(html);
					while(m2.find()){
						String chapterTitle = m2.group();
						chapterTitleList.add(TextUtils.dealWithText(chapterTitle, chapterTitleRegex));
//						System.out.println(TextUtils.dealWithText(chapterTitle, chapterTitleRegex));
					}
					
					if(chapterLinkList.size()==chapterTitleList.size()) {
						for (int i = 0; i < chapterLinkList.size(); i++) {
							BookChapter bookChapter = new BookChapter(chapterTitleList.get(i), chapterLinkList.get(i), book,i);
							chapterService.add(bookChapter);
							BookListService service = ServiceUtils.getBookListService();
							bookList.setStatus(MyConstants.BOOK_LIST_COLLECTED);
							service.update(bookList);
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
