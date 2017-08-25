package com.gongxm.dao;

import java.util.List;

import com.gongxm.bean.Book;

public interface BookDao extends Dao<Book> {

	List<String> getBookCategory();

}
