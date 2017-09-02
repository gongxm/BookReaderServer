package com.gongxm.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

@Entity
@Table(name = "book_info_and_chapter_list_rules")
public class BookInfoAndChapterListRules implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String[] regexs;// 1.标题 2.作者 3.类别 4.状态 5.封面 6.简介7.目录链接正则 8.目录标题正则
	private String startStr;
	private String endStr;
	private String charset;
	private String concatUrl; // 用于把获取到的链接拼接成完整链接
	private boolean useBookLink; // 是否使用书籍本身的链接进行拼接

	@Fetch(FetchMode.SELECT)
	@LazyToOne(LazyToOneOption.PROXY)
	@OneToOne(targetEntity = BookChapterContentRules.class)
	@JoinColumn(name = "chapter_content_id")
	@Cascade({CascadeType.SAVE_UPDATE,CascadeType.DELETE})
	private BookChapterContentRules contentRules;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String[] getRegexs() {
		return regexs;
	}

	public void setRegexs(String[] regexs) {
		this.regexs = regexs;
	}

	public String getStartStr() {
		return startStr;
	}

	public void setStartStr(String startStr) {
		this.startStr = startStr;
	}

	public String getEndStr() {
		return endStr;
	}

	public void setEndStr(String endStr) {
		this.endStr = endStr;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public BookChapterContentRules getContentRules() {
		return contentRules;
	}

	public void setContentRules(BookChapterContentRules contentRules) {
		this.contentRules = contentRules;
	}

	public String getConcatUrl() {
		return concatUrl;
	}

	public void setConcatUrl(String concatUrl) {
		this.concatUrl = concatUrl;
	}

	public boolean isUseBookLink() {
		return useBookLink;
	}

	public void setUseBookLink(boolean useBookLink) {
		this.useBookLink = useBookLink;
	}

}
