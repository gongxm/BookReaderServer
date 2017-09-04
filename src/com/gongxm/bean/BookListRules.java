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

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "book_list_rules")
public class BookListRules implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private int id;
	@Expose
	private String rulesName;// 规则名称
	@Expose
	private String book_source;
	@Expose
	private String baseUrl;
	@Expose
	private String flag;
	@Expose
	private int startIndex;
	@Expose
	private int endIndex;
	@Expose
	private String startStr;
	@Expose
	private String endStr;
	@Expose
	private String regex;
	@Expose
	private boolean isRepeat;
	@Expose
	private String charset;
	@Expose
	private String concatUrl;

	@Fetch(FetchMode.SELECT)
	@LazyToOne(LazyToOneOption.PROXY)
	@OneToOne(targetEntity = BookInfoAndChapterListRules.class)
	@JoinColumn(name = "book_info_id")
	@Cascade({ CascadeType.SAVE_UPDATE, CascadeType.DELETE })
	private BookInfoAndChapterListRules rules;

	@Fetch(FetchMode.SELECT)
	@LazyToOne(LazyToOneOption.PROXY)
	@OneToOne(targetEntity = BookChapterContentRules.class)
	@JoinColumn(name = "book_content_rules_id")
	@Cascade({ CascadeType.SAVE_UPDATE, CascadeType.DELETE })
	private BookChapterContentRules contentRules;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRulesName() {
		return rulesName;
	}

	public void setRulesName(String rulesName) {
		this.rulesName = rulesName;
	}

	public String getBook_source() {
		return book_source;
	}

	public void setBook_source(String book_source) {
		this.book_source = book_source;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
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

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

	public boolean isRepeat() {
		return isRepeat;
	}

	public void setRepeat(boolean isRepeat) {
		this.isRepeat = isRepeat;
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

	public BookInfoAndChapterListRules getRules() {
		return rules;
	}

	public void setRules(BookInfoAndChapterListRules rules) {
		this.rules = rules;
	}

	public BookChapterContentRules getContentRules() {
		return contentRules;
	}

	public void setContentRules(BookChapterContentRules contentRules) {
		this.contentRules = contentRules;
	}

	@Override
	public String toString() {
		return "BookListRules [rulesName=" + rulesName + ", book_source=" + book_source + ", baseUrl=" + baseUrl
				+ ", flag=" + flag + ", startIndex=" + startIndex + ", endIndex=" + endIndex + ", startStr=" + startStr
				+ ", endStr=" + endStr + ", regex=" + regex + ", isRepeat=" + isRepeat + ", charset=" + charset
				+ ", concatUrl=" + concatUrl + ", rules=" + rules + "]";
	}

}