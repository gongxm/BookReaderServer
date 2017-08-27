package com.gongxm.services;

import java.util.List;

import com.gongxm.bean.Book;
import com.gongxm.bean.BookChapter;

public interface BookService extends Service<Book>{

	List<String> getBookCategory();

	List<Book> getCategoryList(String category, int currentPage, int pageSize);

	List<BookChapter> findBookChapterList(String book_link);

}
