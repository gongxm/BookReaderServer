package com.gongxm.services.impl;

import java.util.List;

import com.gongxm.bean.BookChapter;
import com.gongxm.dao.BookChapterDao;
import com.gongxm.dao.Dao;
import com.gongxm.services.BookChapterService;
import com.gongxm.utils.DaoUtils;

public class BookChapterServiceImpl extends BaseService<BookChapter> implements BookChapterService {
	private static final BookChapterDao dao = DaoUtils.getBookChapterDao();

	@Override
	public Dao<BookChapter> getDao() {
		return dao;
	}

	private static final BookChapterServiceImpl instance = new BookChapterServiceImpl();

	private BookChapterServiceImpl() {
	}

	public static BookChapterService getInstance() {
		return instance;
	}

	@Override
	public List<BookChapter> findByBookId(int bookid) {
		return dao.findByBookId(bookid);
	}

}
