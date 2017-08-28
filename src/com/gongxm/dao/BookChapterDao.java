package com.gongxm.dao;

import java.util.List;

import com.gongxm.bean.BookChapter;

public interface BookChapterDao extends Dao<BookChapter> {

	List<BookChapter> findByBookId(int bookid);

}
