package com.gongxm.runnable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.web.context.WebApplicationContext;

import com.gongxm.bean.Book;
import com.gongxm.bean.BookChapter;
import com.gongxm.bean.BookInfoAndChapterListRules;
import com.gongxm.bean.BookList;
import com.gongxm.services.BookChapterService;
import com.gongxm.services.BookListService;
import com.gongxm.services.BookService;
import com.gongxm.utils.CollectUtils;
import com.gongxm.utils.HtmlParser;
import com.gongxm.utils.MyConstants;
import com.gongxm.utils.TextUtils;

public class BookInfoRunnable implements Runnable {

	private String endStr;
	private String startStr;
	private String concatUrl;
	private BookList bookList;
	BookService bookService;
	BookChapterService chapterService;
	BookListService service;
	
	// 标题
//	String titleRegex;
//	// 作者
//	String authorRegex;
//	// 类别
//	String categoryRegex;
//	// 状态
//	String statusRegex;
//	// 封面
//	String coverRegex;
//	// 简介
//	String shortIntroduceRegex;
	private String[] regexs;

	public BookInfoRunnable(WebApplicationContext context, BookList bookList, String concatUrl,BookInfoAndChapterListRules rules) {
		this.bookList = bookList;
		this.startStr = rules.getStartStr();
		this.endStr = rules.getEndStr();
		this.concatUrl=concatUrl;
		
		this.regexs = new String[] {
				rules.getTitleRegex(),
				rules.getAuthorRegex(),
				rules.getCategoryRegex(),
				rules.getStatusRegex(),
				rules.getCoverRegex(),
				rules.getShortIntroduceRegex(),
				rules.getListLinkRegex(),
				rules.getListTitleRegex()
		};
		
		this.bookService=(BookService) context.getBean("bookService");
		this.service = (BookListService) context.getBean("bookListService");
		this.chapterService = (BookChapterService) context.getBean("bookChapterService");
	}

	@Override
	public void run() {
		
		try {
			String url = bookList.getBook_link();
			System.out.println("正在采集:"+url);
			String html = HtmlParser.parseToHtml(url);

			List<String> list = new ArrayList<>();
			
			for(int i=0;i<6;i++){
				String regex = regexs[i];
				Pattern p = Pattern.compile(regex);
				Matcher m = p.matcher(html);
				if(m.find()){
					String text = m.group();
					text = TextUtils.dealWithText(text, regex);
					list.add(text);
				}else {
					list.add("暂无");
				}
			}
			Book book = new Book(list.get(0), list.get(1), list.get(2), list.get(3), list.get(4),list.get(5),url);
			bookService.add(book);
			System.out.println("采集中.."+book.getBook_name());
			
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
					}
					
					Pattern p2 = Pattern.compile(chapterTitleRegex);
					Matcher m2 = p2.matcher(html);
					while(m2.find()){
						String chapterTitle = m2.group();
						chapterTitleList.add(TextUtils.dealWithText(chapterTitle, chapterTitleRegex));
					}
					
					if(chapterLinkList.size()==chapterTitleList.size()) {
						System.out.println("..........采集章节目录中.........");
						for (int i = 0; i < chapterLinkList.size(); i++) {
							System.out.println("正在采集:"+chapterTitleList.get(i));
							BookChapter bookChapter = new BookChapter(chapterTitleList.get(i), chapterLinkList.get(i), i);
							book.getChapters().add(bookChapter);
						}
						bookService.update(book);
						bookList.setStatus(MyConstants.BOOK_COLLECTED);
						System.out.println(".........采集成功..........");
					}else {
						bookList.setStatus(MyConstants.BOOK_COLLECT_FAILURE);
						System.out.println("....目录数据不匹配..."+chapterLinkList.size()+"=="+chapterTitleList.size());
					}
					//更新书籍列表状态
					service.update(bookList);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		CollectUtils.threadCount--;
		System.out.println("======采集完成=====");
	}
	
	

}
