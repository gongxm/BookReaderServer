package com.gongxm.dao;

import java.util.List;

import com.gongxm.bean.Book;
import com.gongxm.bean.BookChapter;

public interface BookDao extends Dao<Book> {

	List<String> getBookCategory();

	List<Book> getCategoryList(String category, int currentPage, int pageSize);

	List<BookChapter> findBookChapterList(String book_link);

}
