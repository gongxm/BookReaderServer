package com.gongxm.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gongxm.bean.BookList;
import com.gongxm.dao.BookListDao;
import com.gongxm.dao.Dao;
import com.gongxm.services.BookListService;
/**
 * 用户服务
 * @author gongxm
 *
 */
@Service("bookListService")
public class BookListServiceImpl extends BaseService<BookList> implements BookListService {
	private static final BookListServiceImpl instance = new BookListServiceImpl(); 
	@Autowired
	@Qualifier("bookListDao")
	private BookListDao dao;
	private BookListServiceImpl(){}

	public static BookListService getInstance() {
		return instance;
	}

	@Override
	public Dao<BookList> getDao() {
		return dao;
	}

	@Override
	public List<BookList> findUnCollectBookListBySource(String book_source, int currentPage, int pageSize) {
		return dao.findUnCollectBookListBySource(book_source,currentPage,pageSize);
	}

	@Override
	public List<BookList> findAllUnCollectBookList(int currentPage, int pageSize) {
		return dao.findAllUnCollectBookList(currentPage,pageSize);
	}

	@Override
	public long getUnCollectBookListCountBySource(String book_source) {
		return dao.getUnCollectBookListCountBySource(book_source);
	}

	@Override
	public long getAllUnCollectBookListCount() {
		return dao.getAllUnCollectBookListCount();
	}

	@Override
	public BookList findByBookLink(String bookUrl) {
		return dao.findByBookLink(bookUrl);
	}
	
}
