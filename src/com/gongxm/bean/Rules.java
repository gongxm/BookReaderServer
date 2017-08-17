package com.gongxm.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rules")
public class Rules {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String url; // 采集地址
	@Column
	private int startIndex; // 开始位置
	@Column
	private int endIndex; // 结束位置
	@Column
	private String startStr; // 采集开始区域
	@Column
	private String endStr; // 采集结束区域
	@Column
	private String regex; // 需要采集内容的正则
	@Column
	private boolean isRepeat; // 是否循环匹配
	@Column
	private boolean isCurrent; // 是否当前地址就是采集地址

	public Rules() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public boolean isCurrent() {
		return isCurrent;
	}

	public void setCurrent(boolean isCurrent) {
		this.isCurrent = isCurrent;
	}

	@Override
	public String toString() {
		return "Rules [id=" + id + ", url=" + url + ", startIndex=" + startIndex + ", endIndex=" + endIndex
				+ ", startStr=" + startStr + ", endStr=" + endStr + ", regex=" + regex + ", isRepeat=" + isRepeat
				+ ", isCurrent=" + isCurrent + "]";
	}

}
