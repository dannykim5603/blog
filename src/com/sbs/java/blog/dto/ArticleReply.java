package com.sbs.java.blog.dto;

import java.util.Map;

public class ArticleReply extends Dto{
	private String updateDate;
	private String body;
	private int articleId;

	public ArticleReply(Map<String, Object> row) {
		super(row);
		this.articleId = (int) row.get("articleId");
		this.updateDate = (String) row.get("updateDate");
		this.body = (String) row.get("body");		
	}

	public int getarticleId() {
		return articleId;
	}

	public void setarticleId(int articleId) {
		this.articleId = articleId;
	}

	public String getUpdateDate() {
		return updateDate;
	}
	
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}


	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return String.format("%n게시번호 : %d 게시 날짜 : %s 업데이트 날짜 : %s%n내용 : %s%n%n 조회수 : %d%n%n"+super.toString(),getId(), getRegDate(),getUpdateDate(), body);
	}


}