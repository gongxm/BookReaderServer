package com.gongxm.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gongxm.bean.Book;
import com.gongxm.dao.BookDao;
import com.gongxm.dao.Dao;
import com.gongxm.services.BookService;
@Service("bookService")
@Transactional
public class BookServiceImpl extends BaseService<Book> implements BookService {
	
	private static final BookServiceImpl instance = new BookServiceImpl(); 
	@Autowired
	@Qualifier("bookDao")
	private BookDao bdao;
	
	private BookServiceImpl(){}

	public static BookService getInstance() {
		return instance;
	}

	@Override
	public Dao<Book> getDao() {
		return bdao;
	}

	@Override
	public List<String> getBookCategory() {
		return bdao.getBookCategory();
	}

	@Override
	public List<Book> getCategoryList(String category, int currentPage, int pageSize) {
		return bdao.getCategoryList(category,currentPage,pageSize);
	}

}
