package com.gongxm.test;

import java.io.IOException;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gongxm.bean.Book;
import com.gongxm.bean.BookChapter;
import com.gongxm.bean.BookChapterContent;
import com.gongxm.bean.BookChapterContentRules;
import com.gongxm.bean.BookInfoAndChapterListRules;
import com.gongxm.bean.BookListRules;
import com.gongxm.dao.BookChapterDao;
import com.gongxm.dao.BookDao;
import com.gongxm.services.BookListRulesService;
import com.gongxm.services.BookListService;
import com.gongxm.services.BookService;
import com.gongxm.services.UserService;
import com.gongxm.utils.CollectUtils;
import com.gongxm.utils.MyConstants;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class MyTest {
	
	@Autowired
	@Qualifier("bookChapterDao")
	BookChapterDao dao;
	
	@Autowired
	private JdbcTemplate jtl;
	
	@Autowired
	@Qualifier("bookDao")
	protected BookDao bookDao;  
	
	
	@Autowired
	UserService userService;
	
	@Autowired
	BookListRulesService service;
	
	@Autowired
	BookService bookService;
	
	@Autowired
	static BookListService bookListService;
	
	@Test
	public void test8() {
		
		Book book = new Book();
		book.setBook_name("好好学习");
		BookChapter bookChapter = new BookChapter();
		bookChapter.setChapter_name("java");
		bookChapter.setChapter_link("www.itheima.com");
		BookChapterContent chapterContent=new BookChapterContent();
		chapterContent.setText("好好学习好好学习好好学习好好学习好好学习好好学习好好学习好好学习好好学习好好学习好好学习好好学习");
		bookChapter.setChapterContent(chapterContent);
		book.getChapters().add(bookChapter);
		
		bookService.add(book);
		
		
//		Book book = bookService.findOne(1);
		/*BookChapter bookChapter = new BookChapter();
		bookChapter.setChapter_name("UI");
		bookChapter.setChapter_link("www.ith11eima.com");
		BookChapterContent chapterContent=new BookChapterContent();
		chapterContent.setText("222好好学习好好学习好好学习好好学习好好学习好好学习好好学习好好学习好好学习好好学习好好学习好好学习");
		bookChapter.setChapterContent(chapterContent);
		book.getChapters().add(bookChapter);
		bookService.update(book);*/
//		bookService.delete(book);
		
		
	}
	
	@Test
	public void test7() {
	/*	BookListRules bookListRules = new BookListRules();
		bookListRules.setBook_source("www.baidu.com");
		bookListRules.setRegex("[0-9]\\d+");
		
		BookInfoAndChapterListRules rules=new BookInfoAndChapterListRules();
		rules.setCharset("utf-8");
		String[] regexs= {"aaaaaa","bbbbbbbbbbb","CCCCCCCCCCCCCCCCc"};
		rules.setRegexs(regexs);
		BookChapterContentRules contentRules=new BookChapterContentRules();
		contentRules.setEndStr("end");
		contentRules.setStartStr("start");
		rules.setContentRules(contentRules);
		bookListRules.setRules(rules);
		service.add(bookListRules);*/
		
	/*	BookListRules bookListRules = service.findOne(1);
		service.delete(bookListRules);*/
		
	}
	
	
	@Test
	public void test6(){
		//https://31412947.qcloud.la/BookReaderServer/collect?id=1
		
		/*long count = dao.getUnCollectChapterCount();
		System.out.println("count="+count);*/
//		String sql = "select distinct category from books";
//		List<String> forList = jtl.queryForList(sql, String.class);
//		System.out.println("list = "+forList);
		
		String sql = "select count(*) from book_list where status = ?";
		Long count = jtl.queryForObject(sql,new Object[] {MyConstants.BOOK_COLLECTED}, Long.class);
		System.out.println("count="+count);
		
		
		/*List<Book> list =bookDao.getCategoryList("武侠修真", 2, 5);
		
		System.out.println("list.size="+list.size());*/
	}
	
	public static void main(String[] args) throws IOException {
//		demo1();
//		demo2();
//		demo3();
//		demo4();
		//id: 237, position: 0,
		/*ResponseResult result = new ResponseResult(MyConstants.SUCCESS, StringConstants.HTTP_REQUEST_SUCCESS);
		BookChapterService service = ServiceUtils.getBookChapterService();
		BookChapter chapter = service.findOne(1);
		if (chapter != null) {
			BookChapterContent content = chapter.getChapterContent();
			result.setResult(content);
			String json = GsonUtils.toJson(result);
			System.out.println(json);
		}*/
	}

	private static void demo4() {
	/*	BookChapterService service = ServiceUtils.getBookChapterService();
		BookChapter chapter = service.findOne(8140);
//		System.out.println(chapter.getChapterContent().getContent());
		String content = chapter.getChapterContent().getContent();
		for(int i=0;i<4;i++) {
			System.out.println(content.charAt(i));
		}*/
		
	}

	public static void demo3() {
		String startStr = "<div class=\"yd_text2\">";
		String endStr = "<div class=\"yd_ad1\">";
		System.out.println("第三步启动");
	//	CollectUtils.collectBookChapter(startStr,endStr);
	}

	public static void demo2() {
		String[] regexs={"<meta property=\"og:novel:book_name\" content=\".{1,20}\">",
				"<meta property=\"og:novel:author\" content=\".{1,10}\">",
				"<meta property=\"og:novel:category\" content=\".{2,8}\">",
				"<meta property=\"og:novel:status\" content=\".{2,5}\">",
				"<meta property=\"og:image\" content=\".{5,100}\">",
				"og:description\" content=\".{10,1500}",
				"<li><a href=\"\\d+\\.(html){1}\">",
				"html\">.{1,100}</a></li>"
				};
		String startStr="<div class=\"mulu\">";
		String endStr="<div id=\"footer\">";
		String charset="gbk";
		String concatUrl="";
		boolean useBookLink=true;
		try {
			System.out.println("第二步启动");
			CollectUtils.collectBookInfo(bookListService,null, regexs, startStr, endStr, charset,concatUrl,useBookLink);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public static void demo1() {
		String book_source="www.88dushu.com";
		String baseUrl="http://www.88dushu.com/sort2/(*)/";
		String flag="(*)";
		int startIndex=1;
		int endIndex=53;
		String startStr="<div class=\"booklist\">";
		String endStr="<div class=\"pagelink\" id=\"pagelink\">";
		String regex="<span class=\"sm\"><a href=\"(/xiaoshuo/)[0-9/]*\">";
		boolean repeat = true;
		String charset = "gbk";
		String concatUrl = "http://www.88dushu.com";
		System.out.println("第一步启动");
		CollectUtils.collectBookList(book_source,baseUrl, flag, startIndex, endIndex, startStr, endStr, regex, repeat,charset,concatUrl);
	}
	
	//查询重复记录
	//select book_link ,count(*) as count  from book_list group by book_link having count>1; 

}
