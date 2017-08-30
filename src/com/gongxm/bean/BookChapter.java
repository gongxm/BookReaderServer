package com.gongxm.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "book_chapters")
public class BookChapter implements Comparable<BookChapter>{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private int id;
	@Column
	@Expose
	private int position;
	@Column
	@Expose
	private String chapter_name;
	@Column
	@Expose
	private String chapter_link;
	
	@Fetch(FetchMode.SELECT)
	@LazyToOne(LazyToOneOption.PROXY)
	@ManyToOne(targetEntity = Book.class)
	@Cascade(value = CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "book_id")
	private Book book;
	
	@Column
	@Expose
	private int status;// 采集状态

	// 关联内容
	@Fetch(FetchMode.SELECT)
	@LazyToOne(LazyToOneOption.PROXY)
	@OneToOne(targetEntity = BookChapterContent.class)
	@JoinColumn(name = "content_id")
	@Cascade(CascadeType.SAVE_UPDATE)
	private BookChapterContent chapterContent;

	public BookChapter() {
	}

	public BookChapter(String chapter_name, String chapter_link, Book book, int position) {
		super();
		this.chapter_name = chapter_name;
		this.chapter_link = chapter_link;
		this.book = book;
		this.position = position;
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

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public BookChapterContent getChapterContent() {
		return chapterContent;
	}

	public void setChapterContent(BookChapterContent chapterContent) {
		this.chapterContent = chapterContent;
	}

	@Override
	public String toString() {
		return "BookChapter [id=" + id + ", position=" + position + ", chapter_name=" + chapter_name + "]";
	}

	@Override
	public int compareTo(BookChapter chapter) {
		return this.position-chapter.position;
	}

}
