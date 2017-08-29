package com.gongxm.services;

import java.util.List;

import com.gongxm.bean.Book;

public interface BookService extends Service<Book>{

	List<String> getBookCategory();

	List<Book> getCategoryList(String category, int currentPage, int pageSize);

}
