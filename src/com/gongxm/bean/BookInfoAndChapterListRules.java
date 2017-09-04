package com.gongxm.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "book_info_and_chapter_list_rules")
public class BookInfoAndChapterListRules implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private int id;
	// 1.标题 2.作者 3.类别 4.状态 5.封面 6.简介7.目录链接正则 8.目录标题正则
	@Expose
	private String titleRegex;
	@Expose
	private String authorRegex;
	@Expose
	private String categoryRegex;
	@Expose
	private String statusRegex;
	@Expose
	private String coverRegex;
	@Expose
	private String shortIntroduceRegex;
	@Expose
	private String listLinkRegex;
	@Expose
	private String listTitleRegex;
	@Expose
	private String startStr;
	@Expose
	private String endStr;
	@Expose
	private String charset;
	@Expose
	private String concatUrl; // 用于把获取到的链接拼接成完整链接
	@Expose
	private boolean useBookLink; // 是否使用书籍本身的链接进行拼接

	private int book_list_rules_id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getTitleRegex() {
		return titleRegex;
	}

	public void setTitleRegex(String titleRegex) {
		this.titleRegex = titleRegex;
	}

	public String getAuthorRegex() {
		return authorRegex;
	}

	public void setAuthorRegex(String authorRegex) {
		this.authorRegex = authorRegex;
	}

	public String getCategoryRegex() {
		return categoryRegex;
	}

	public void setCategoryRegex(String categoryRegex) {
		this.categoryRegex = categoryRegex;
	}

	public String getStatusRegex() {
		return statusRegex;
	}

	public void setStatusRegex(String statusRegex) {
		this.statusRegex = statusRegex;
	}

	public String getCoverRegex() {
		return coverRegex;
	}

	public void setCoverRegex(String coverRegex) {
		this.coverRegex = coverRegex;
	}

	public String getShortIntroduceRegex() {
		return shortIntroduceRegex;
	}

	public void setShortIntroduceRegex(String shortIntroduceRegex) {
		this.shortIntroduceRegex = shortIntroduceRegex;
	}

	public String getListLinkRegex() {
		return listLinkRegex;
	}

	public void setListLinkRegex(String listLinkRegex) {
		this.listLinkRegex = listLinkRegex;
	}

	public String getListTitleRegex() {
		return listTitleRegex;
	}

	public void setListTitleRegex(String listTitleRegex) {
		this.listTitleRegex = listTitleRegex;
	}

	public int getBook_list_rules_id() {
		return book_list_rules_id;
	}

	public void setBook_list_rules_id(int book_list_rules_id) {
		this.book_list_rules_id = book_list_rules_id;
	}

	@Override
	public String toString() {
		return "BookInfoAndChapterListRules [id=" + id + ", titleRegex=" + titleRegex + ", authorRegex=" + authorRegex
				+ ", categoryRegex=" + categoryRegex + ", statusRegex=" + statusRegex + ", coverRegex=" + coverRegex
				+ ", shortIntroduceRegex=" + shortIntroduceRegex + ", listLinkRegex=" + listLinkRegex
				+ ", listTitleRegex=" + listTitleRegex + ", startStr=" + startStr + ", endStr=" + endStr + ", charset="
				+ charset + ", concatUrl=" + concatUrl + ", useBookLink=" + useBookLink + "]";
	}

}
