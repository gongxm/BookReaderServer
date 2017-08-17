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
	private String url; // �ɼ���ַ
	@Column
	private int startIndex; // ��ʼλ��
	@Column
	private int endIndex; // ����λ��
	@Column
	private String startStr; // �ɼ���ʼ����
	@Column
	private String endStr; // �ɼ���������
	@Column
	private String regex; // ��Ҫ�ɼ����ݵ�����
	@Column
	private boolean isRepeat; // �Ƿ�ѭ��ƥ��
	@Column
	private boolean isCurrent; // �Ƿ�ǰ��ַ���ǲɼ���ַ

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
