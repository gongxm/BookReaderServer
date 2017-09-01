package com.gongxm.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gongxm.bean.Book;
import com.gongxm.bean.BookChapter;
import com.gongxm.bean.BookChapterContent;
import com.gongxm.domain.request.IDParam;
import com.gongxm.domain.response.ResponseResult;
import com.gongxm.services.BookChapterService;
import com.gongxm.services.BookService;
import com.gongxm.utils.GsonUtils;
import com.gongxm.utils.MyConstants;
import com.gongxm.utils.StringConstants;
import com.gongxm.utils.TextUtils;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class BookAction extends BaseAction implements ModelDriven<IDParam> {
	private static final long serialVersionUID = 1L;
	IDParam param = new IDParam();
	@Autowired
	private BookService bookService;
	@Autowired
	BookChapterService chapterService;

	@Override
	public IDParam getModel() {
		return param;
	}

	// 获取书籍详情
	@Action("getBookDetail")
	public void getBookDetail() {
		ResponseResult result = new ResponseResult(MyConstants.FAILURE, StringConstants.HTTP_REQUEST_ERROR);
		int id = param.getId();
		if (id > 0) {
			Book book = bookService.findOne(id);
			if (book != null) {
				result.setErrcode(MyConstants.SUCCESS);
				result.setErrmsg(StringConstants.HTTP_REQUEST_SUCCESS);
				result.setResult(book);
			} else {
				result.setErrmsg(StringConstants.BOOK_NOT_FOUND);
			}
		} else {
			result.setErrmsg(StringConstants.BOOK_ID_ERROR);
		}
		String json = GsonUtils.parseToJson(result);
		
		write(json);
	}

	// 获取书籍章节内容
	@Action("getBookChapter")
	public void getBookChapter() {
		ResponseResult result = new ResponseResult(MyConstants.FAILURE, StringConstants.HTTP_REQUEST_ERROR);
		int id = param.getId();
		if (id > 0) {
			BookChapter chapter = chapterService.findOne(id);
			if (chapter != null) {
				if (chapter.getStatus() == MyConstants.BOOK_COLLECTED) {
					BookChapterContent content = chapter.getChapterContent();
					String text = content.getContent();
					if (TextUtils.isEmpty(text)) {
						text = "";
					}
					result.setResult(text);
					result.setErrcode(MyConstants.SUCCESS);
					result.setErrmsg(StringConstants.HTTP_REQUEST_SUCCESS);
				} else {
					result.setErrmsg(StringConstants.BOOK_CHAPTER_UNCOLLECT);
				}
			} else {
				result.setErrmsg(StringConstants.BOOK_CHAPTER_NOT_FOUND);
			}
		} else {
			result.setErrmsg(StringConstants.BOOK_ID_ERROR);
		}

		String json = GsonUtils.parseToJson(result);
		write(json);
	}

	// 获取章节列表
	@Action("getChapterList")
	public void getChapterList() {
		ResponseResult result = new ResponseResult(MyConstants.FAILURE, StringConstants.HTTP_REQUEST_ERROR);
		int id = param.getId();
		if (id > 0) {
			Book book = bookService.findOne(id);
			if (book != null) {
				Set<BookChapter> set = book.getChapters();
				if (set != null) {
					List<BookChapter> list = new ArrayList<BookChapter>();
					list.addAll(set);
					Collections.sort(list);
					result.setErrcode(MyConstants.SUCCESS);
					result.setErrmsg(StringConstants.HTTP_REQUEST_SUCCESS);
					result.setResult(list);
				} else {
					result.setErrmsg(StringConstants.BOOK_CHAPTER_NOT_FOUND);
				}
			} else {
				result.setErrmsg(StringConstants.BOOK_NOT_FOUND);
			}
		} else {
			result.setErrmsg(StringConstants.BOOK_ID_ERROR);
		}
		String json = GsonUtils.parseToJson(result);
		write(json);
	}
}
