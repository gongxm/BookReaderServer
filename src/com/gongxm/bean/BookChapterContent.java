package com.gongxm.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.Type;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "book_chapter_content")
public class BookChapterContent {
	@Id
	@GenericGenerator(strategy = "uuid", name = "myuuid")
	@GeneratedValue(generator = "myuuid")
	@Expose
	private String id;

	@Column
	@Type(type = "text")
	@Expose
	private String content;

	@Fetch(FetchMode.SELECT)
	@LazyToOne(LazyToOneOption.PROXY)
	@OneToOne(targetEntity = BookChapter.class, mappedBy = "chapterContent")
	private BookChapter chapter;

	public BookChapterContent(String content) {
		this.content = content;
	}

	public BookChapterContent() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public BookChapter getChapter() {
		return chapter;
	}

	public void setChapter(BookChapter chapter) {
		this.chapter = chapter;
	}

	@Override
	public String toString() {
		return "BookChapterContent [id=" + id + ", content=" + content + "]";
	}

}
