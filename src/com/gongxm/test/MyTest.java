package com.gongxm.test;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gongxm.bean.User;
import com.gongxm.dao.BookChapterDao;
import com.gongxm.dao.BookDao;
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
	
	@Test
	public void test7() {
		System.out.println(userService==null);
		User user = userService.findOne(1);
		System.out.println(user);
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
		try {
			System.out.println("第二步启动");
			//CollectUtils.collectBookInfo(null, regexs, startStr, endStr, charset);
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
