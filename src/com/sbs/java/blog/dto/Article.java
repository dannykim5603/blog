package com.sbs.java.blog.dto;

import java.util.Map;

public class Article extends Dto{
	private String title;
	private String body;
	private int cateItemId;
	private int hit;
	private int memberId;

	public Article(Map<String, Object> row) {
		super(row);
		this.cateItemId = (int) row.get("cateItemId");
		this.memberId = (int) row.get("memberId");
		this.title = (String) row.get("title");
		this.body = (String) row.get("body");		
		this.hit = (int)row.get("hit");
	}
	
	public int getMemberId() {
		return memberId;
	}
	
	public void setMemberId(int memberId) {
		this.memberId = memberId;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}
	
	public String getBodyForXTemplate() {
		return body.replaceAll("(?i)script", "<!--REPLACE:script-->");
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return String.format(
				"Article [title=%s, body=%s, cateItemId=%s, hit=%s, memberId=%s, getId()=%s, getRegDate()=%s, getUpdateDate()=%s, getExtra()=%s]",
				title, body, cateItemId, hit, memberId, getId(), getRegDate(), getUpdateDate(), getExtra());
	}

	
}