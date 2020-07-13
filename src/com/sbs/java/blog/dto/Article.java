package com.sbs.java.blog.dto;

import java.util.Map;

public class Article extends Dto{
	private String updateDate;
	private String title;
	private String body;
	private int cateItemId;
	private int hit;

	public Article(Map<String, Object> row) {
		super(row);
		this.cateItemId = (int) row.get("cateItemId");
		this.updateDate = (String) row.get("updateDate");
		this.title = (String) row.get("title");
		this.body = (String) row.get("body");		
		this.hit = (int)row.get("hit");
	}
	
	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public int getCateItemId() {
		return cateItemId;
	}

	public void setCateItemId(int cateItemId) {
		this.cateItemId = cateItemId;
	}

	public String getUpdateDate() {
		return updateDate;
	}
	
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return String.format("%n게시번호 : %d 제목 : %s%n게시 날짜 : %s 업데이트 날짜 : %s%n내용 : %s%n%n 조회수 : %d%n%n"+super.toString(),getId(), getTitle(), getRegDate(),getUpdateDate(), body, hit);
	}


}