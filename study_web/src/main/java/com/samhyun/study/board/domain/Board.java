package com.samhyun.study.board.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "board")
public class Board implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String type;
	private String title;
	private String content;
	private char delYn;
	private String lastModified;
	private String regDate;
	private String writor;
	private int count;
	
	public Board() {
		super();
	}
	public Board(String id, String type, String title, String content,
			char delYn, String lastModified, String regDate, String writor, int count) {
		super();
		this.id = id;
		this.type = type;
		this.title = title;
		this.content = content;
		this.delYn = delYn;
		this.lastModified = lastModified;
		this.regDate = regDate;
		this.writor = writor;
		this.count = count;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWritor() {
		return writor;
	}
	public void setWritor(String writor) {
		this.writor = writor;
	}
	public char getDelYn() {
		return delYn;
	}
	public void setDelYn(char delYn) {
		this.delYn = delYn;
	}
	public String getLastModified() {
		return lastModified;
	}
	public void setLastModified(String lastModified) {
		this.lastModified = lastModified;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
