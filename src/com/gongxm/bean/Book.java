package com.gongxm.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "books")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String book_name;
	@Column
	private String author;
	@Column
	private String category;
	@Column
	private String cover;
	@Column
	private String status;
	@Column
	private String book_link;
	@Column
	@Type(type="text")
	private String shortIntroduce;

	@OneToMany(targetEntity = BookChapter.class, mappedBy = "book", cascade = CascadeType.ALL)
	private Set<BookChapter> chapters = new HashSet<BookChapter>();

	public Book() {
	}

	public Book(String book_name, String author, String category, String status, String cover, String shortIntroduce,
			String book_link) {
		super();
		this.book_name = book_name;
		this.category = category;
		this.author = author;
		this.cover = cover;
		this.status = status;
		this.shortIntroduce = shortIntroduce;
		this.book_link = book_link;
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getBook_link() {
		return book_link;
	}

	public void setBook_link(String book_link) {
		this.book_link = book_link;
	}

	public String getShortIntroduce() {
		return shortIntroduce;
	}

	public void setShortIntroduce(String shortIntroduce) {
		this.shortIntroduce = shortIntroduce;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<BookChapter> getChapters() {
		return chapters;
	}

	public void setChapters(Set<BookChapter> chapters) {
		this.chapters = chapters;
	}

	@Override
	public String toString() {
		return "Book [book_name=" + book_name + ", author=" + author + ", category=" + category + ", cover=" + cover
				+ ", status=" + status + ", book_link=" + book_link + ", shortIntroduce=" + shortIntroduce + "]";
	}

}
