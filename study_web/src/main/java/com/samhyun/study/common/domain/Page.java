package com.samhyun.study.common.domain;

import java.util.List;


public class Page<E> {
	
	private int totalCnt;
	private int pageCnt;
	private int pageNum = 1; 
	private int pageSize = 15;
	private String keyword;	
	private String searchType = "all";
	private List<E> entities;

	public Page() {
		super();
	}
	
	public Page(int totalCnt, int pageCnt, int pageNum, int pageSize,
			String keyword, String searchType, List<E> entities) {
		super();
		this.totalCnt = totalCnt;
		this.pageCnt = pageCnt;
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.keyword = keyword;
		this.searchType = searchType;
		this.entities = entities;
	}

	public int getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}
	
	public int getPageCnt() {
		return pageCnt;
	}

	public void setPageCnt(int pageCnt) {
		this.pageCnt = pageCnt;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public List<E> getEntities() {
		return entities;
	}

	public void setEntities(List<E> entities) {
		this.entities = entities;
	}	
	
	public void setCountEntity(Page<E> page) {
		this.pageCnt = page.getPageCnt();
		this.totalCnt = page.getTotalCnt();
	}	
}
