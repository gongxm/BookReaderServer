package com.gongxm.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "book_chapters_list")
public class BookChapter {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String book_name;
	@Column
	private String book_link;
	@Column
	private String chapter_name;
	@Column
	private String chapter_link;
	@Column
	private Date update_time;

	public BookChapter() {
	}

	public BookChapter(int id, String chapter_name, String chapter_link) {
		super();
		this.id = id;
		this.chapter_name = chapter_name;
		this.chapter_link = chapter_link;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public String getBook_link() {
		return book_link;
	}

	public void setBook_link(String book_link) {
		this.book_link = book_link;
	}

	public String getChapter_name() {
		return chapter_name;
	}

	public void setChapter_name(String chapter_name) {
		this.chapter_name = chapter_name;
	}

	public String getChapter_link() {
		return chapter_link;
	}

	public void setChapter_link(String chapter_link) {
		this.chapter_link = chapter_link;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

}
